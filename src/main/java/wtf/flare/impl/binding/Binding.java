package wtf.flare.impl.binding;

import org.lwjgl.glfw.GLFW;

public class Binding {

    /**
     * The key used to activate this bind
     * @see GLFW
     */
    private int key;

    /**
     * The device used to activate this bind
     */
    private BindingDeviceType deviceType;

    /**
     * If the bind should stay active while the key/button is not being held down
     * If set to false, the bind will de-activate once the key/button is let off
     */
    private boolean persistent = true;

    /**
     * This bind's state
     */
    private boolean state = false;

    /**
     * The bind's inhibitor
     */
    private Inhibitor inhibitor = null;

    public Binding(BindingDeviceType deviceType, int key) {
        this.deviceType = deviceType;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BindingDeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(BindingDeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        invoke();
    }

    public void setInhibitor(Inhibitor inhibitor) {
        this.inhibitor = inhibitor;
    }

    public void invoke() {
        if (inhibitor != null) {
            inhibitor.invoke(this);
        }
    }
}
