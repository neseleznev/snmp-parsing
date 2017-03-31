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

/**
 * Switch entity, able to send queries and parse snmp response.
 */
public class Switch {
    SnmpAgent agent;

    public Switch(SnmpAgent agent) {
        this.agent = agent;
    }

    String sysName = "<UNKNOWN>";
    List<Vlan> vlans = new ArrayList<>();

    /**
     * Walks in agent's tree with prefix SnmpConstants.VLAN and figures out vlans with required properties
     */
    public void discoverVlans(Snmp snmp) {
        // Get MIB data.
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        java.util.List<TreeEvent> events = treeUtils.walk(agent.target, new OID[] {new OID(VLAN)});

        System.out.println(events.size());
        HashMap<String, Vlan> vlans = new HashMap<>();
        for (TreeEvent event: events) {
            if(event == null) {
                continue;
            }
            if (event.isError()) {
                System.err.println("oid [" + VLAN + "] " + event.getErrorMessage());
                continue;
            }

            VariableBinding[] variableBindings = event.getVariableBindings();
            if(variableBindings == null || variableBindings.length == 0){
                continue;
            }

            for (VariableBinding binding: variableBindings) {
                String oid = binding.getOid().toString();
                String vlanId = oid.substring(oid.lastIndexOf(".") + 1);
                // In branches of following if statement shouldn't be NullPointerException
                // except first initial
                Vlan vlan = vlans.get(vlanId);
                System.out.println(oid + " " + vlanId + " " + vlan + VLAN_ID);

                if (oid.startsWith(VLAN_ID)) {
                    // Initialize and put vlans
                    String newVlanId = binding.getVariable().toString();
                    Vlan newVlan = new Vlan();
                    newVlan.id = newVlanId;
                    vlans.put(newVlanId, newVlan);
                } else if (oid.startsWith(VLAN_NAME)) {
                    vlan.name = binding.getVariable().toString();
                } else if (oid.startsWith(VLAN_PORTS)) {
                    String[] hex = binding.getVariable().toString().split(":");
                    for (int i = 0; i < hex.length; ++i) {
                        int hexValue = Integer.parseInt(hex[i], 16);
                        for (int mask = 0x80, j = 0; mask > 0; mask >>= 1, ++j) { // binary 10000000 -> 01000000
                            if ((hexValue & mask) != 0) { // in current position set 1
                                vlan.ports.add(i*8 + j + 1); // 8 bits in every hex
                            }
                        }
                    }
                    System.out.println(binding.getVariable());
                }
            }
        }
        List<Vlan> vlans1 = new ArrayList<>();
        for (Vlan vlan: vlans.values()) {
            vlans1.add(vlan);
        }
        this.vlans = vlans1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("switch ");
        sb.append(sysName.contains(" ")
                ? ("\"" + sysName + "\"")
                : sysName);
        sb.append(" {\n");

        for (Vlan vlan: vlans) {
            sb.append("  ").append(vlan).append("\n");
        }

        sb.append("}\n");
        return sb.toString();
    }
}
