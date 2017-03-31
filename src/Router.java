package src;

import org.snmp4j.Snmp;

import java.util.ArrayList;
import java.util.List;

/**
 * Router entity, able to send queries and parse snmp response.
 */
public class Router {
    SnmpAgent agent;

    public Router(SnmpAgent agent) {
        this.agent = agent;
    }

    List<Vlan> interfaces = new ArrayList<>();
    List<Vlan> routes = new ArrayList<>();

    /**
     * Shortcut for discover procedures.
     */
    public void discover(Snmp snmp) {
        agent.discoverName(snmp);
        discoverInterfaces(snmp);
        discoverRoutes(snmp);
    }

    /**
     * Walks in agent's tree with prefix SnmpConstants.INTERFACES
     * and figures out interfaces with required properties
     */
    public void discoverInterfaces(Snmp snmp) {

    }

    /**
     * Walks in agent's tree with prefix SnmpConstants.ROUTES
     * and figures out routes with required properties
     */
    public void discoverRoutes(Snmp snmp) {

    }

    /**
     * <router>    ::= "router" SYS_NAME "{" { <interface> } { route } "}"
     * <interface> ::= "interface" <ip-name> NETMASK PHYS_ADDRESS
     * <ip-name>   ::= IP "(" DNS_NAME ")"
     * <route>     ::= "route" <dest> <next-hop>
     * <dest>      ::= IP NETMASK
     * <next-hop>  ::= <ip-name>
     * where
     * SYS_NAME is the SNMP sysName object.
     * DNS_NAME is the name corresponding to IP, as obtained through DNS resolution.
     *     Output <UNKNOWN> if DNS resolution fails.
     * <ip-name> should be printed as one token, i.e. without space between
     *     the IP address and the corresponding DNS name. If the IP is unknown,
     *     replace the whole <ip-name> by a single <UNKNOWN>.
     * PHYS_ADDRESS is the link-layer address of the interface. If the interface
     *     is Ethernet, report it with colons (e.g. 01:23:45:67:89:ab).
     *
     * Here is a shortened example of switch output:
     * router eagle {
     *   interface 139.165.222.1(eagle.run...) 255.255.255.128 0:e0:18:0:6d:7e
     *   interface 193.190.228.30(labrun.gw.ulg...) 255.255.255.252 14:d6:4d:1a:ac:fe
     *   route 0.0.0.0 0.0.0.0 193.190.228.29(<UNKNOWN>)
     *   route 139.165.222.128 255.255.255.224 139.165.222.5(hawk.run...)
     * }
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("router ");
        sb.append(agent.sysName.contains(" ")
                ? ("\"" + agent.sysName + "\"")
                : agent.sysName);
        sb.append(" {\n");

        for (Vlan iface: interfaces) {
            sb.append("  ").append(iface).append("\n");
        }

        for (Vlan route: routes) {
            sb.append("  ").append(route).append("\n");
        }

        sb.append("}\n");
        return sb.toString();
    }
}
