package src;

import java.util.List;

/**
 * Simple data structure to store the result of parsing.
 */
public class ConfigFile {
    public List<SnmpAgent> routers;
    public List<SnmpAgent> switches;

    public ConfigFile(List<SnmpAgent> ss, List<SnmpAgent> rs) {
        switches = ss;
        routers = rs;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Switches:\n");
        for (SnmpAgent entry : switches) {
            builder.append(entry).append("\n");
        }
        builder.append("Routers:\n");
        for (SnmpAgent entry : routers) {
            builder.append(entry).append("\n");
        }
        return builder.toString();
    }
}
