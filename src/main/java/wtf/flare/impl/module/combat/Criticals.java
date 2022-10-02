package wtf.flare.impl.module.combat;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import wtf.flare.core.mixins.duck.IVec3d;
import wtf.flare.core.mixins.impl.network.c2s.IPlayerInteractEntityC2SPacket;
import wtf.flare.impl.event.network.EventPacket;
import wtf.flare.impl.module.ModuleCategory;
import wtf.flare.impl.module.ToggableModule;
import wtf.flare.impl.property.Property;

public class Criticals extends ToggableModule {
    private final Property<Mode> mode = new Property<>(Mode.PACKET, "Mode", "m", "type");
    private final Property<Boolean> stopSprint = new Property<>(true, "Stop Sprint", "nosprint", "stopsprint");

    public Criticals() {
        super("Criticals", new String[]{"crits"}, ModuleCategory.COMBAT);
        offerProperties(mode, stopSprint);
    }

    @EventListener
    public void onEventPacket(EventPacket event) {
        if (event.isClientBound() && event.getPacket() instanceof PlayerInteractEntityC2SPacket) {
            PlayerInteractEntityC2SPacket packet = event.getPacket();
            if (((IPlayerInteractEntityC2SPacket) packet).getType().getType().equals(PlayerInteractEntityC2SPacket.InteractType.ATTACK)) {
                Entity entity = mc.world.getEntityById(((IPlayerInteractEntityC2SPacket) packet).getEntityId());
                if (!(entity instanceof LivingEntity)) {
                    return;
                }

                if (!mc.player.isOnGround() || mc.player.isSubmergedInWater() || mc.player.isInLava()) {
                    return;
                }

                boolean stopSprinting = stopSprint.getValue() && mc.player.isSprinting();
                if (stopSprinting) {
                    mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                }

                switch (mode.getValue()) {
                    case PACKET -> {
                        send(0.0, 0.11, 0.0);
                        send(0.0, 0.0, 0.0);
                    }

                    case NCP_UPDATED -> {
                        send(0.0, 0.11, 0.0);
                        send(0.0, 0.1100013579, 0.0);
                        send(0.0, 0.0000013579, 0.0);
                    }

                    case MOTION -> ((IVec3d) mc.player.getVelocity()).setY(0.11);
                    case JUMP -> mc.player.jump();
                }

                if (stopSprinting) {
                    mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_SPRINTING));
                }
            }
        }
    }

    private void send(double x, double y, double z) {
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                mc.player.getX() + x, mc.player.getY() + y, mc.player.getZ() + z, false));
    }

    public enum Mode {
        PACKET, NCP_UPDATED, JUMP, MOTION
    }
}
