package src;

import java.util.ArrayList;
import java.util.List;

import static src.Utils.UNKNOWN;

/**
 * Simple data structure to store vlan's information.
 */
public class Vlan {
    public String id = UNKNOWN;
    public String name = UNKNOWN;
    public List<Integer> ports = new ArrayList<>();

    public String toString() {
        return String.format("vlan %s %s { %s }",
                id,
                name.contains(" ") ? ("\"" + name + "\"") : name,
                ports.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " "));
    }
}
