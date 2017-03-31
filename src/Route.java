package src;

/**
 * Simple data structure to store route's information.
 */
public class Route {
    private static final String unknown = "<UNKNOWN>";

    public String ip = unknown;
    public String netMask = unknown;
    public String nextIp = unknown;
    public String nextDnsName = unknown;

    public String toString() {
        return String.format("route %s %s %s(%s)", ip, netMask, nextIp, nextDnsName);
    }
}
