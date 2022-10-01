package wtf.flare.core.init.version;

public enum ClientEnvironment {
    RELEASE(null),
    RELEASE_CANDIDATE("rc"),
    BETA("beta"),
    DEVELOPMENT("dev");

    public final String tag;

    ClientEnvironment(String tag) {
        this.tag = tag;
    }

    public boolean hasTag() {
        return tag != null;
    }
}