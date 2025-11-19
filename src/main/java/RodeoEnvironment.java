package io.spicelabs.rodeocomponents;
import java.lang.Runtime.Version;

public class RodeoEnvironment {

    private static final Version _currentVersion = Version.parse("1.0.0.1");;
    public static Version currentVersion() {
        return _currentVersion;
    }
}
