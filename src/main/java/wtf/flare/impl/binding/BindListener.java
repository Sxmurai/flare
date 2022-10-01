package wtf.flare.impl.binding;

import me.bush.eventbus.annotation.EventListener;
import org.lwjgl.glfw.GLFW;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.event.io.EventKey;
import wtf.flare.impl.event.io.EventMouse;
import wtf.flare.util.core.Wrapper;

public class BindListener implements Wrapper {

    private final BindManager bindManager;

    public BindListener(BindManager bindManager) {
        this.bindManager = bindManager;
        FlareClient.LOGGER.info("Created binding listener");
    }

    @EventListener
    public void onEventKey(EventKey event) {
        if (isNull() || mc.currentScreen != null || event.getKey() == GLFW.GLFW_KEY_UNKNOWN) {
            return;
        }

        for (Binding binding : bindManager.getBindingList()) {

            if (!binding.getDeviceType().equals(BindingDeviceType.KEYBOARD)) {
                continue;
            }

            if (binding.getKey() != event.getKey()) {
                return;
            }

            int action = event.getAction();
            if (!binding.isPersistent() && action == GLFW.GLFW_RELEASE) {
                binding.setState(false);
                continue;
            }

            if (action == GLFW.GLFW_PRESS) {
                binding.setState(!binding.getState());
            }
        }
    }

    @EventListener
    public void onEventMousePress(EventMouse.Press event) {
        if (isNull() || mc.currentScreen != null) {
            return;
        }

        for (Binding binding : bindManager.getBindingList()) {

            if (!binding.getDeviceType().equals(BindingDeviceType.MOUSE)) {
                continue;
            }

            if (binding.getKey() != event.getButton()) {
                return;
            }

            int action = event.getAction();
            if (!binding.isPersistent() && action == GLFW.GLFW_RELEASE) {
                binding.setState(false);
                continue;
            }

            if (action == GLFW.GLFW_PRESS) {
                binding.setState(!binding.getState());
            }
        }
    }
}
