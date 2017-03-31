package src;

/**
 * Created by nikita on 26/03/17.
 * http://oid-info.com/get/1.3.6.1.2.1.17.7.1.4.2.1.3
 */
public final class SnmpConstants {
    // iso(1) identified-organization(3) dod(6) internet(1) mgmt(2) mib-2(1)
    private static final String PREFIX = "1.3.6.1.2.1.";
    // .0 to get value in the ending
    private static final String ENDING = ".0";

    public static final String SYS_NAME = PREFIX + "1.5" + ENDING;

    public static final String VLAN = PREFIX + "17.7.1.4"; //.3.1";
    public static final String VLAN_ID = VLAN + ".2.1.3"; // dot1qVlanFdbId
    public static final String VLAN_NAME = VLAN + ".3.1.1"; // dot1qVlanStaticName
    public static final String VLAN_PORTS = VLAN + ".3.1.2"; // dot1qVlanStaticEgressPorts

    public static final String IFACE = PREFIX + "2.2.1"; // ifEntry
    public static final String IFACE_ID = IFACE + ".1"; // ifIndex
    public static final String IFACE_PHYS_ADDR = IFACE + ".6"; // ifPhysAddress

    public static final String IP_ADDR = PREFIX + "4.20.1"; // ipAddrEntry
    public static final String IP_ADDRESSES = IP_ADDR + ".2"; // ipAdEntIfIndex
    public static final String IP_MASK = IP_ADDR + ".3"; // ipAdEntNetMask



}
