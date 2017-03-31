package src;

import static src.Utils.UNKNOWN;

/**
 * Simple data structure to store route's information.
 */
public class Route {
    public String ip = UNKNOWN;
    public String netMask = UNKNOWN;
    public String nextIp = UNKNOWN;
    public String nextDnsName = UNKNOWN;

    public String toString() {
        return String.format("route %s %s %s(%s)", ip, netMask, nextIp, nextDnsName);
    }
}
