package wtf.flare.impl.module;

import wtf.flare.impl.module.combat.Criticals;
import wtf.flare.impl.module.movement.Sprint;
import wtf.flare.impl.module.movement.Velocity;

import java.util.*;

public class ModuleManager {

    private final Map<Class<? extends Module>, Module> moduleClassMap = new HashMap<>();
    private final Map<String, Module> moduleAliasMap = new HashMap<>();
    private final List<Module> moduleList = new ArrayList<>();

    public ModuleManager() {
        register(new Criticals());
        register(new Sprint());
        register(new Velocity());
    }

    private void register(Module module) {
        moduleClassMap.put(module.getClass(), module);
        moduleAliasMap.put(module.getName().toLowerCase(), module);
        Arrays.stream(module.getAliases()).forEach((alias) -> moduleAliasMap.put(alias.toLowerCase(), module));
        moduleList.add(module);
    }

    public <T extends Module> T get(String alias) {
        return (T) moduleAliasMap.getOrDefault(alias.toLowerCase(), null);
    }

    public <T extends Module> T get(Class<? extends Module> clazz) {
        return (T) moduleClassMap.getOrDefault(clazz, null);
    }

    public List<Module> getModuleList() {
        return moduleList;
    }
}
