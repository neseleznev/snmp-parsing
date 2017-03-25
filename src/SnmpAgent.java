package src;

import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;

/* Simple data structure to remember info about an SNMP agent. */
public class SnmpAgent {
    public Address host;
    public int v;
    public OctetString community;
    public SecurityLevel securityLevel;
    public UsmUser user;

    public SnmpAgent(Address host, int v, OctetString community) {
        assert (v == 1 || v == 2);
        this.host = host;
        this.v = v;
        this.community = community;
    }

    public SnmpAgent(Address host, SecurityLevel level, UsmUser user) {
        this.host = host;
        this.v = 3;
        this.securityLevel = level;
        this.user = user;
    }

    public String toString() {
        String s = host.toString() + " v" + v;
        if (v <= 2)
            s += " " + community;
        else
            s += " " + securityLevel + " " + user;
        return s;
    }
}
