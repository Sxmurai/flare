package wtf.flare.impl.event.mc;

import me.bush.eventbus.event.Event;

public class EventTick extends Event {

    @Override
    protected boolean isCancellable() {
        return false;
    }
}
