package wtf.flare.core.mixin.input;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.event.io.EventMouse;
import wtf.flare.util.io.InputUtils;

@Mixin(Mouse.class)
public class MixinMouse {

    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo info) {
        if (InputUtils.isHandle(window)) {
            FlareClient.BUS.post(new EventMouse.Press(button, action, mods));
        }
    }

    @Inject(method = "onMouseScroll", at = @At("HEAD"))
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo info) {
        if (InputUtils.isHandle(window)) {
            FlareClient.BUS.post(new EventMouse.Scroll(vertical, horizontal));
        }
    }
}
