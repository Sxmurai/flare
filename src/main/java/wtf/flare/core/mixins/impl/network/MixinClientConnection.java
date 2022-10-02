package wtf.flare.core.mixins.impl.network;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.event.network.EventPacket;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(
            method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/ClientConnection;handlePacket(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;)V",
                    shift = At.Shift.BEFORE),
            cancellable = true)
    private void channelRead0$preHandlePacket(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        if (FlareClient.BUS.post(new EventPacket(EventPacket.Direction.SERVER, packet))) {
            info.cancel();
        }
    }

    @Inject(
            method = "send(Lnet/minecraft/network/Packet;Lnet/minecraft/network/PacketCallbacks;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/ClientConnection;sendImmediately(Lnet/minecraft/network/Packet;Lnet/minecraft/network/PacketCallbacks;)V",
                    shift = At.Shift.BEFORE),
            cancellable = true)
    private void send$preSendImmediately(Packet<?> packet, PacketCallbacks callbacks, CallbackInfo info) {
        if (FlareClient.BUS.post(new EventPacket(EventPacket.Direction.CLIENT, packet))) {
            info.cancel();
        }
    }
}
