package wtf.flare.impl.module;

public enum ModuleCategory {
    COMBAT("Combat"),
    EXPLOITS("Exploits"),
    MISCELLANEOUS("Miscellaneous"),
    MOVEMENT("Movement"),
    WORLD("World"),
    ACTIVE("Active");

    private final String displayName;

    ModuleCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
