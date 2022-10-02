package wtf.flare.impl.module;

import org.lwjgl.glfw.GLFW;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.binding.Binding;
import wtf.flare.impl.binding.BindingDeviceType;

public class ToggableModule extends Module {
    private final Binding binding;

    public ToggableModule(String name, String[] aliases, ModuleCategory category) {
        super(name, aliases, category);

        binding = new Binding(BindingDeviceType.KEYBOARD, GLFW.GLFW_KEY_UNKNOWN);
        binding.setInhibitor((b) -> {
            if (b.getState()) {
                FlareClient.BUS.subscribe(this);
                onActivated();
            } else {
                FlareClient.BUS.unsubscribe(this);
                onDeactivated();
            }
        });
        FlareClient.getInstance().getBindManager().addBinding(binding);
    }

    protected void onActivated() {

    }

    protected void onDeactivated() {

    }

    public Binding getBinding() {
        return binding;
    }
}
