package wtf.flare.core;

import me.bush.eventbus.annotation.EventListener;
import me.bush.eventbus.bus.EventBus;
import me.bush.eventbus.handler.handlers.ASMHandler;
import me.bush.eventbus.handler.handlers.ReflectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtf.flare.core.init.version.Version;
import wtf.flare.impl.binding.BindManager;
import wtf.flare.impl.event.mc.EventSetDisplayTitle;
import wtf.flare.impl.module.ModuleManager;

public class FlareClient {

    /**
     * If the client has been initialized or not yet
     */
    private static boolean initialized = false;

    private static FlareClient instance;

    public static final Version VERSION = new Version(1, 0, 0);

    public static final Logger LOGGER = LoggerFactory.getLogger("Flare");
    public static final EventBus BUS = new EventBus(ReflectHandler.class, LOGGER::error);

    private ModuleManager moduleManager;
    private BindManager bindManager;

    private FlareClient() {
        if (initialized) {
            return;
        }

        instance = this;
        initialized = true;

        BUS.subscribe(new FlareClientListener());
        LOGGER.info("Loading Flare v{}", VERSION.getVersionString());

        // load managers
        LOGGER.info("Loading managers...");
        bindManager = new BindManager();
        moduleManager = new ModuleManager();
    }

    public static void init() {
        new FlareClient();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public BindManager getBindManager() {
        return bindManager;
    }

    /**
     * A basic inner class for all client related listeners
     */
    private static class FlareClientListener {
        @EventListener
        public void onSetDisplayTitle(EventSetDisplayTitle event) {

            event.setTitle("Flare v" + VERSION.getVersionString());
            event.setCancelled(true);
        }
    }

    public static FlareClient getInstance() {
        return instance;
    }
}
