package wtf.flare.core.mixins.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.event.mc.EventSetDisplayTitle;
import wtf.flare.impl.event.mc.EventTick;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Shadow @Nullable public ClientWorld world;

    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(method = "getWindowTitle", at = @At("TAIL"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> info) {
        EventSetDisplayTitle event = new EventSetDisplayTitle(info.getReturnValue());
        if (FlareClient.BUS.post(event)) {
            info.setReturnValue(event.getTitle());
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        if (world != null && player != null) {
            FlareClient.BUS.post(new EventTick());
        }
    }
}
