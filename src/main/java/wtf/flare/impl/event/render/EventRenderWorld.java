package wtf.flare.impl.event.render;

import me.bush.eventbus.event.Event;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

public class EventRenderWorld extends Event {
    private final MatrixStack matrices;
    private final Matrix4f matrix4f;
    private final float tickDelta;

    public EventRenderWorld(MatrixStack matrices, Matrix4f matrix4f, float tickDelta) {
        this.matrices = matrices;
        this.matrix4f = matrix4f;
        this.tickDelta = tickDelta;
    }

    public MatrixStack getMatrices() {
        return matrices;
    }

    public Matrix4f getMatrix4f() {
        return matrix4f;
    }

    public float getTickDelta() {
        return tickDelta;
    }

    @Override
    protected boolean isCancellable() {
        return false;
    }
}
