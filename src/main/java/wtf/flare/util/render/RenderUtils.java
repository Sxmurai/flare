package wtf.flare.util.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import wtf.flare.util.core.Wrapper;

public class RenderUtils implements Wrapper {

    public static void renderBox(Box box, int color) {
        MatrixStack matrices = createMatrices();
        matrices.translate(box.minX, box.minY, box.minZ);
        renderBox(matrices, box, color);
    }

    public static void renderBox(MatrixStack matrices, Box box, int color) {
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();

        offsetCamera(matrices);
        Matrix4f model = matrices.peek().getPositionMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionShader);
        //RenderSystem.lineWidth(lineWidth);

        float alpha = (float) (color >> 24 & 0xFF) / 255.0f;
        float red = (float) (color >> 16 & 0xFF) / 255.0f;
        float green = (float) (color >> 8 & 0xFF) / 255.0f;
        float blue = (float) (color & 0xFF) / 255.0f;

        RenderSystem.setShaderColor(red, green, blue, alpha);

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);

        buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.maxZ).next();

        buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.minZ).next();

        buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.maxZ).next();

        buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.minZ).next();

        buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.maxZ).next();

        buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.maxZ).next();
        buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.minZ).next();
        buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.minZ).next();

        //tessellator.draw();

        BufferRenderer.drawWithShader(buffer.end());

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static MatrixStack createMatrices(MatrixStack matrices) {
        offsetCamera(matrices);
        return matrices;
    }

    public static MatrixStack createMatrices() {
        MatrixStack matrices = new MatrixStack();

        // GameRenderer.java
        Camera camera = mc.gameRenderer.getCamera();
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(camera.getPitch()));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(camera.getYaw() + 180.0f));
        offsetCamera(matrices);

        return matrices;
    }

    public static void offsetCamera(MatrixStack matrices) {
        Vec3d cameraPos = mc.gameRenderer.getCamera().getPos();
        matrices.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
    }
}
