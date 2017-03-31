package src;

/**
 * Helper class, containing required OIDs for making queries.
 * Here you can find more information about OID
 * http://oid-info.com/get/1.3.6.1.2.1.17.7.1.4.2.1.3
 */
public final class SnmpConstants {
    // iso(1) identified-organization(3) dod(6) internet(1) mgmt(2) mib-2(1)
    private static final String SYSTEM = "1.3.6.1.2.1."; // system
    // .0 to get value in the ending
    private static final String ENDING = ".0";

    public static final String SYS_NAME = SYSTEM + "1.5" + ENDING;

    public static final String VLAN = SYSTEM + "17.7.1.4"; //.3.1";
    public static final String VLAN_ID = VLAN + ".2.1.3"; // dot1qVlanFdbId
    public static final String VLAN_NAME = VLAN + ".3.1.1"; // dot1qVlanStaticName
    public static final String VLAN_PORTS = VLAN + ".3.1.2"; // dot1qVlanStaticEgressPorts

    public static final String IFACE = SYSTEM + "2.2.1"; // ifEntry
    public static final String IFACE_ID = IFACE + ".1"; // ifIndex
    public static final String IFACE_PHYS_ADDR = IFACE + ".6"; // ifPhysAddress

    public static final String IP_ADDR = SYSTEM + "4.20.1"; // ipAddrEntry
    public static final String IP_ADDRESSES = IP_ADDR + ".2"; // ipAdEntIfIndex
    public static final String IP_MASK = IP_ADDR + ".3"; // ipAdEntNetMask

    public static final String IP_ROUTE = SYSTEM + "4.21.1"; // ipRouteEntry
    public static final String IP_ROUTE_DEST = IP_ROUTE + ".1"; // ipRouteDest
    public static final String IP_ROUTE_NEXT_HOP = IP_ROUTE + ".7"; // ipRouteNextHop
    public static final String IP_ROUTE_MASK = IP_ROUTE + ".11"; // ipRouteMask
}
