package wtf.flare.util.core;

import net.minecraft.client.MinecraftClient;

public interface Wrapper {

    MinecraftClient mc = MinecraftClient.getInstance();

    default boolean isNull() {
        return mc.world == null || mc.player == null;
    }
}
