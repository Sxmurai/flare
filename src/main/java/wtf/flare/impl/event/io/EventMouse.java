package wtf.flare.impl.event.io;

import me.bush.eventbus.event.Event;

public class EventMouse extends Event {

    public static class Press extends EventMouse {
        private final int button, action, modifiers;

        public Press(int button, int action, int modifiers) {
            this.button = button;
            this.action = action;
            this.modifiers = modifiers;
        }

        public int getButton() {
            return button;
        }

        public int getAction() {
            return action;
        }

        public int getModifiers() {
            return modifiers;
        }
    }

    public static class Scroll extends EventMouse {
        private final double vertical, horizontal;

        public Scroll(double vertical, double horizontal) {
            this.vertical = vertical;
            this.horizontal = horizontal;
        }

        public double getVertical() {
            return vertical;
        }

        public double getHorizontal() {
            return horizontal;
        }
    }

    @Override
    protected boolean isCancellable() {
        return false;
    }
}
