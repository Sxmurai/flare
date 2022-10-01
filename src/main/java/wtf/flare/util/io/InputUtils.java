package wtf.flare.util.io;

import wtf.flare.util.core.Wrapper;

public class InputUtils implements Wrapper {

    public static boolean isHandle(long in) {
        return in == getWindowHandle();
    }

    public static long getWindowHandle() {
        return mc.getWindow().getHandle();
    }
}
