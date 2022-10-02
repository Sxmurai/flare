package wtf.flare.impl.module.movement;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import wtf.flare.core.mixins.impl.network.s2c.IEntityVelocityUpdateS2CPacket;
import wtf.flare.core.mixins.impl.network.s2c.IExplosionS2CPacket;
import wtf.flare.impl.event.network.EventPacket;
import wtf.flare.impl.module.ModuleCategory;
import wtf.flare.impl.module.ToggableModule;
import wtf.flare.impl.property.Property;

public class Velocity extends ToggableModule {
    private final Property<Boolean> knockback = new Property<>(true, "Knockback", "kb");
    private final Property<Boolean> explosions = new Property<>(true, "Explosions", "kabooms");

    public Velocity() {
        super("Velocity", new String[]{"antiknockback", "antikb", "vel"}, ModuleCategory.MOVEMENT);
        offerProperties(knockback, explosions);
    }

    @EventListener
    public void onEventPacket(EventPacket event) {
        if (event.isServerBound()) {

            if (knockback.getValue() && event.getPacket() instanceof EntityVelocityUpdateS2CPacket packet) {
                Entity entity = mc.world.getEntityById(packet.getId());
                if (entity == null || !entity.equals(mc.player)) {
                    return;
                }

                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX(0);
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityY(0);
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityZ(0);
            }

            if (explosions.getValue() && event.getPacket() instanceof ExplosionS2CPacket packet) {
                ((IExplosionS2CPacket) packet).setPlayerVelocityX(0.0f);
                ((IExplosionS2CPacket) packet).setPlayerVelocityY(0.0f);
                ((IExplosionS2CPacket) packet).setPlayerVelocityZ(0.0f);
            }
        }
    }
}
