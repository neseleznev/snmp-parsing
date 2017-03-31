package src;

import static src.Utils.UNKNOWN;

/**
 * Simple data structure to store interface's information.
 */
public class Iface {
    public String ip = UNKNOWN;
    public String dnsName = UNKNOWN;
    public String netMask = UNKNOWN;
    public String physicalAddress = UNKNOWN;

    public String toString() {
        return String.format("interface %s(%s) %s %s", ip, dnsName, netMask, physicalAddress);
    }
}
