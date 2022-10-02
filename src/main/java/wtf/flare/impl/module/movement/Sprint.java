package wtf.flare.impl.module.movement;

import me.bush.eventbus.annotation.EventListener;
import wtf.flare.impl.binding.BindingDeviceType;
import wtf.flare.impl.event.mc.EventTick;
import wtf.flare.impl.module.ModuleCategory;
import wtf.flare.impl.module.ToggableModule;
import wtf.flare.impl.property.Property;

public class Sprint extends ToggableModule {
    private final Property<Mode> mode = new Property<>(Mode.LEGIT, "Mode", "m", "type");

    public Sprint() {
        super("Sprint", new String[]{"autosprint"}, ModuleCategory.MOVEMENT);
        offerProperties(mode);

        getBinding().setDeviceType(BindingDeviceType.MOUSE);
        getBinding().setKey(4);
    }

    @Override
    protected void onDeactivated() {
        super.onDeactivated();

        if (isNull()) {
            return;
        }

        if (!mc.options.sprintKey.isPressed()) {
            mc.player.setSprinting(false);
        }
    }

    @EventListener
    public void onTick(EventTick event) {
        if (!mc.player.isSprinting()) {
            switch (mode.getValue()) {
                case RAGE -> mc.player.setSprinting(true);
                case LEGIT -> mc.player.setSprinting(!mc.player.isSneaking() &&
                        !mc.player.isUsingItem() &&
                        mc.player.getHungerManager().getFoodLevel() > 6 &&
                        mc.player.input.pressingForward);
            }
        }
    }

    public enum Mode {
        LEGIT, RAGE
    }
}
