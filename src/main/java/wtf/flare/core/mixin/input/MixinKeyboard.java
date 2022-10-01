package wtf.flare.core.mixin.input;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.event.io.EventKey;
import wtf.flare.util.io.InputUtils;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
        if (InputUtils.isHandle(window)) {
            FlareClient.BUS.post(new EventKey(key, scancode, action, modifiers));
        }
    }

}
