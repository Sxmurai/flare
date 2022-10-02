package wtf.flare.core.mixins.impl.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import wtf.flare.core.FlareClient;
import wtf.flare.impl.event.render.EventRenderWorld;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(
            method = "renderWorld",
            at = @At(
                    value = "INVOKE_STRING",
                    target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V",
                    ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void renderWorld$preSwap(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo info) {
        FlareClient.BUS.post(new EventRenderWorld(matrices, matrices.peek().getPositionMatrix(), tickDelta));
        RenderSystem.applyModelViewMatrix();
    }

}
