package wtf.flare.impl.event.mc;

import me.bush.eventbus.event.Event;

public class EventSetDisplayTitle extends Event {
    private String title;

    public EventSetDisplayTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
