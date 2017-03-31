package src;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import static src.SnmpConstants.SYS_NAME;

/* Simple data structure to remember info about an SNMP agent. */
public class SnmpAgent {
    public String givenHostName;
    public Address host;
    public int version;
    public OctetString community;
    public SecurityLevel securityLevel;
    public UsmUser user;
    public CommunityTarget target;

    public SnmpAgent(String host, int version, OctetString community) {
        this(host, version, community, null, null);
        assert (version == SnmpConstants.version1 || version == SnmpConstants.version2c);
    }

    public SnmpAgent(String host, SecurityLevel level, UsmUser user) {
        this(host, SnmpConstants.version3, null, level, user);
    }

    public SnmpAgent(String host, int version, OctetString community, SecurityLevel level, UsmUser user) {
        this.givenHostName = host;
        this.host = parseHost(host);
        this.version = version;
        this.community = community;
        this.securityLevel = level;
        this.user = user;

        // Create Target Address object
        target = new CommunityTarget();
        target.setAddress(this.host);
        target.setVersion(this.version);
        if (this.community != null) {
            target.setCommunity(this.community);
        }
        target.setRetries(2);
        target.setTimeout(1000);
    }

    String sysName = "<UNKNOWN>";

    /**
     * Asks agent's OID SnmpConstants.SYS_NAME and stores it.
     */
    public void discoverName(Snmp snmp) {
        Vector<VariableBinding> binding = getVariableBinding(snmp, new String[] {SYS_NAME});
        if (binding != null && binding.size() > 0) {
            sysName = binding.get(0).getVariable().toString();
        }
    }

    /**
     * Wrapper for SNMP query.
     */
    public Vector<VariableBinding> getVariableBinding(Snmp snmp, String[] oids) {
        PDU response = null;
        try {
            response = getResponse(snmp, oids);
        } catch (IOException ignored) {
        }

        if (response != null)
        {
            int errorStatus = response.getErrorStatus();
            int errorIndex = response.getErrorIndex();
            String errorStatusText = response.getErrorStatusText();

            if (errorStatus == PDU.noError)
            {
                return (Vector<VariableBinding>) response.getVariableBindings();
            }
            else
            {
                System.err.println("Error: Request Failed");
                System.err.println("Error Status = " + errorStatus);
                System.err.println("Error Index = " + errorIndex);
                System.err.println("Error Status Text = " + errorStatusText + " " + givenHostName);
            }
        }
        else
        {
            System.err.println("Error: Response PDU is null");
        }
        return null;
    }

    /**
     * Converts a string representation of host dnsName or IP address (and
     * optional postfixed port number) into a SNMP4J UDP address.
     */
    public static UdpAddress parseHost(String s) {
        try {
            String[] parts = s.split(":");
            InetAddress addr = InetAddress.getByName(parts[0]);
            int port = (parts.length == 2) ? Integer.parseInt(parts[1]) : 161;
            return new UdpAddress(addr, port);
        } catch (UnknownHostException e) {
            System.out.println("WARNING: Unable to resolve <" + s + "> hostname");
            return null;
        }
    }

    public PDU getResponse(Snmp snmp, String oid) throws IOException {
        return getResponse(snmp, new String[] {oid});
    }
    public PDU getResponse(Snmp snmp, String[] oids) throws IOException {
        // Create the PDU object
        PDU pdu;
        if (this.version == SnmpConstants.version3) {
            pdu = new ScopedPDU();
        } else {
            pdu = new PDU();
        }

        for (String oid: oids) {
            pdu.add(new VariableBinding(new OID(oid)));
        }
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));

        System.out.println("\tDEBUG: Sending Request to Agent...");
        ResponseEvent response = snmp.get(pdu, target);

        // Process Agent Response
        if (response != null) {
            System.out.println("\tDEBUG: Got Response from Agent");
            return response.getResponse();
        }
        return null;
    }

    public String toString() {
        String versionString = "1";
        if (version == SnmpConstants.version2c)
            versionString = "2c";
        else if (version == SnmpConstants.version3)
            versionString = "3";
        String s = (host != null ? host.toString() : "<UNKNOWN>") + " v" + versionString;

        if (version == SnmpConstants.version1 || version == SnmpConstants.version2c)
            s += " " + community;
        else
            s += " " + securityLevel + " " + user;
        return s;
    }
}
