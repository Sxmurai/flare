package wtf.flare.core.init;

import net.fabricmc.api.DedicatedServerModInitializer;

public class FlareServerInitializer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        System.out.println("ERROR: DO NOT RUN THIS MOD ON A SERVER");
    }
}
