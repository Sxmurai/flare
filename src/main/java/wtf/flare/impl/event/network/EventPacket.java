package wtf.flare.impl.event.network;

import me.bush.eventbus.event.Event;
import net.minecraft.network.Packet;

public class EventPacket extends Event {

    private final Direction direction;
    private final Packet<?> packet;

    public EventPacket(Direction direction, Packet<?> packet) {
        this.direction = direction;
        this.packet = packet;
    }

    // no bullying me stfu
    public boolean isServerBound() {
        return direction.equals(Direction.SERVER);
    }

    public boolean isClientBound() {
        return direction.equals(Direction.CLIENT);
    }

    public <T extends Packet<?>> T getPacket() {
        return (T) packet;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }

    public enum Direction {
        CLIENT, SERVER
    }
}
