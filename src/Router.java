package src;

import org.snmp4j.Snmp;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static src.SnmpConstants.*;
import static src.Utils.isProperPrefix;

/**
 * Router entity, able to send queries and parse snmp response.
 */
public class Router {
    SnmpAgent agent;

    public Router(SnmpAgent agent) {
        this.agent = agent;
    }

    List<Iface> interfaces = new ArrayList<>();
    List<Route> routes = new ArrayList<>();

    /**
     * Shortcut for discover procedures.
     */
    public void discover(Snmp snmp) {
        if (agent.host == null) {
            return;
        }
        agent.discoverName(snmp);
        discoverInterfaces(snmp);
        discoverRoutes(snmp);
    }

    /**
     * Walks in agent's tree with prefix SnmpConstants.INTERFACES
     * and figures out interfaces with required properties
     */
    public void discoverInterfaces(Snmp snmp) {
        // Get MIB data.
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.walk(agent.target, new OID[] {
                new OID(IFACE_ID), new OID(IFACE_PHYS_ADDR),
                new OID(IP_ADDRESSES), new OID(IP_MASK)});
        HashMap<String, Iface> ifaces = new HashMap<>();
        for (TreeEvent event: events) {
            if(event == null) {
                continue;
            }
            if (event.isError()) {
                System.err.println("oid [" + IFACE + "] " + event.getErrorMessage());
                continue;
            }

            VariableBinding[] variableBindings = event.getVariableBindings();
            if(variableBindings == null || variableBindings.length == 0){
                continue;
            }
            for (VariableBinding binding: variableBindings) {
                String oid = binding.getOid().toString();
                if (isProperPrefix(oid, IFACE_ID)) {
                    // Initialize and put ifaces
                    String newIfaceId = binding.getVariable().toString();
                    ifaces.put(newIfaceId, new Iface());
                }
            }
        }

        for (TreeEvent event: events) {
            if (event == null) {
                continue;
            }
            if (event.isError()) {
                System.err.println("oid [" + IFACE + "] " + event.getErrorMessage());
                continue;
            }

            VariableBinding[] variableBindings = event.getVariableBindings();
            if (variableBindings == null || variableBindings.length == 0){
                continue;
            }
            for (VariableBinding binding: variableBindings) {
                String oid = binding.getOid().toString();
                String ifaceId = oid.substring(oid.lastIndexOf(".") + 1);
                Iface iface = ifaces.get(ifaceId);

                if (isProperPrefix(oid, IFACE_PHYS_ADDR)) {
                    iface.physicalAddress = binding.getVariable().toString();
                } else if (isProperPrefix(oid, IP_ADDRESSES)) {
                    ifaceId = binding.getVariable().toString();
                    iface = ifaces.get(ifaceId);
                    String[] tokens = oid.split("\\d+\\.\\d+\\.\\d+\\.\\d+$");
                    if (tokens.length > 0) {
                        iface.ip = oid.substring(tokens[0].length());
                    }
                } else if (isProperPrefix(oid, IP_MASK)) {
                    String[] tokens = oid.split("\\d+\\.\\d+\\.\\d+\\.\\d+$");
                    if (tokens.length == 0) {
                        continue;
                    }
                    String ipAddress = oid.substring(tokens[0].length());
                    for (Iface interf: ifaces.values()) {
                        if (interf.ip.equals(ipAddress)) {
                            iface = interf;
                        }
                    }
                    iface.netMask = binding.getVariable().toString();
                }
                // TODO DNS resolve
            }
        }
        for (Iface iface: ifaces.values()) {
            this.interfaces.add(iface);
        }
    }

    /**
     * Walks in agent's tree with prefix SnmpConstants.IP_ROUTE
     * and figures out routes with required properties
     */
    public void discoverRoutes(Snmp snmp) {
        // Get MIB data.
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.walk(agent.target, new OID[] {
                new OID(IP_ROUTE_DEST), new OID(IP_ROUTE_NEXT_HOP),
                new OID(IP_ROUTE_MASK)});

        HashMap<String, Route> routes = new HashMap<>();
        for (TreeEvent event: events) {
            if (event == null) {
                continue;
            }
            if (event.isError()) {
                System.err.println("oid [" + IP_ROUTE + "] " + event.getErrorMessage());
                continue;
            }

            VariableBinding[] variableBindings = event.getVariableBindings();
            if (variableBindings == null || variableBindings.length == 0){
                continue;
            }
            for (VariableBinding binding: variableBindings) {
                String oid = binding.getOid().toString();
                String[] tokens = oid.split("\\d+\\.\\d+\\.\\d+\\.\\d+$");
                if (tokens.length == 0) {
                    continue;
                }
                String from = oid.substring(tokens[0].length());
                String to = binding.getVariable().toString();
                Route route = routes.get(from);
//                System.out.println(oid + " <" + from + "> <" + to + ">");
                if (isProperPrefix(oid, IP_ROUTE_DEST)) {
                    // Initialize and put routes
                    route = new Route();
                    route.ip = to;
//                    System.out.println("Created " + route.ip);
                } else if (isProperPrefix(oid, IP_ROUTE_NEXT_HOP)) {
                    route.nextIp = to;
//                    System.out.println("Changed nextIP" + route.ip + " " + route.nextIp);
                } else if (isProperPrefix(oid, IP_ROUTE_MASK)) {
                    route.netMask = to;
//                    System.out.println("Changed maskIP" + route.ip + " " + route.netMask);
                }
                // TODO DNS resolution
                routes.put(from, route);
            }
        }
        for (Route route: routes.values()) {
            this.routes.add(route);
        }
    }

    /**
     * <router>    ::= "router" SYS_NAME "{" { <interface> } { route } "}"
     * <interface> ::= "interface" <ip-dnsName> NETMASK PHYS_ADDRESS
     * <ip-dnsName>   ::= IP "(" DNS_NAME ")"
     * <route>     ::= "route" <dest> <next-hop>
     * <dest>      ::= IP NETMASK
     * <next-hop>  ::= <ip-dnsName>
     * where
     * SYS_NAME is the SNMP sysName object.
     * DNS_NAME is the dnsName corresponding to IP, as obtained through DNS resolution.
     *     Output <UNKNOWN> if DNS resolution fails.
     * <ip-dnsName> should be printed as one token, i.e. without space between
     *     the IP address and the corresponding DNS dnsName. If the IP is unknown,
     *     replace the whole <ip-dnsName> by a single <UNKNOWN>.
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

        for (Iface iface: interfaces) {
            sb.append("  ").append(iface).append("\n");
        }

        for (Route route: routes) {
            sb.append("  ").append(route).append("\n");
        }

        sb.append("}\n");
        return sb.toString();
    }
}
