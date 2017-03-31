package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple data structure to store vlan's information.
 */
public class Vlan {
    private static final String unknown = "<UNKNOWN>";

    public String id = unknown;
    public String name = unknown;
    public List<Integer> ports = new ArrayList<>();

    public String toString() {
        return String.format("vlan %s %s { %s }",
                id,
                name.contains(" ") ? ("\"" + name + "\"") : name,
                ports.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " "));
    }
}
