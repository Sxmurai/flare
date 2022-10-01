package wtf.flare.core.init.version;

/**
 * <a href="https://semver.org/">Semver</a>
 */
public class Version {
    public final int major, minor, patch;
    public final ClientEnvironment env;

    private int rcId = -1;

    public Version(int major, int minor, int patch) {
        this(major, minor, patch, ClientEnvironment.RELEASE);
    }

    public Version(int major, int minor, int patch, ClientEnvironment env) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.env = env;
    }

    public Version setRcId(int rcId) {
        this.rcId = rcId;
        return this;
    }

    public String getVersionString() {
        String ver = major + "." + minor + "." + patch;
        if (env.hasTag()) {
            ver += "-" + env.tag;

            if (env.equals(ClientEnvironment.RELEASE_CANDIDATE) && rcId != -1) {
                ver += "." + rcId;
            }
        }

        return ver;
    }

    public ClientEnvironment getEnv() {
        return env;
    }

    // lol
    public boolean isDev() {
        return env.equals(ClientEnvironment.DEVELOPMENT);
    }

    @Override
    public String toString() {
        return getVersionString();
    }
}