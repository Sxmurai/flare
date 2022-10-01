package wtf.flare.impl.module;

import wtf.flare.core.FlareClient;
import wtf.flare.impl.property.PropertyContainer;
import wtf.flare.util.core.Labeled;
import wtf.flare.util.core.Printable;
import wtf.flare.util.core.Wrapper;

public class Module
        extends PropertyContainer
        implements Wrapper, Labeled, Printable {

    private final String name;
    private final String[] aliases;
    private final ModuleCategory category;

    public Module(String name, String[] aliases, ModuleCategory category) {
        this.name = name;
        this.aliases = aliases;
        this.category = category;
    }

    public Module(String name, String[] aliases) {
        this(name, aliases, ModuleCategory.ACTIVE);
        FlareClient.BUS.subscribe(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    public ModuleCategory getCategory() {
        return category;
    }
}
