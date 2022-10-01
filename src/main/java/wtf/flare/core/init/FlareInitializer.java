package wtf.flare.core.init;

import net.fabricmc.api.ClientModInitializer;
import wtf.flare.core.FlareClient;

public class FlareInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // initialize flare
        FlareClient.init();
    }
}
