package src;

/**
 * Simple data structure to store interface's information.
 */
public class Iface {
    private static final String unknown = "<UNKNOWN>";

    public String ip = unknown;
    public String dnsName = unknown;
    public String netMask = unknown;
    public String physicalAddress = unknown;

    public String toString() {
        return String.format("interface %s(%s) %s %s", ip, dnsName, netMask, physicalAddress);
    }
}
