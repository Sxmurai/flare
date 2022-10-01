package wtf.flare.impl.event.io;

import me.bush.eventbus.event.Event;

public class EventKey extends Event {

    private final int key, scancode, action, modifiers;

    public EventKey(int key, int scancode, int action, int modifiers) {
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.modifiers = modifiers;
    }

    public int getKey() {
        return key;
    }

    public int getScancode() {
        return scancode;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }

    @Override
    protected boolean isCancellable() {
        return false;
    }

}
