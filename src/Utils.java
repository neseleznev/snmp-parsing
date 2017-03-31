package src;

import org.snmp4j.smi.UdpAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Utilities class
 */
public class Utils {
    final static String UNKNOWN = "<UNKNOWN>";

    /**
     * Converts a string representation of host dnsName or IP address (and
     * optional postfixed port number) into a SNMP4J UDP address.
     */
    static UdpAddress parseHost(String s) {
        try {
            String[] parts = s.split(":");
            InetAddress addr = InetAddress.getByName(parts[0]);
            int port = (parts.length == 2) ? Integer.parseInt(parts[1]) : 161;
            return new UdpAddress(addr, port);
        } catch (UnknownHostException e) {
            System.err.println("Unable to resolve <" + s + "> hostname");
            return null;
        }
    }

    /**
     * @param oid String, for example "1.3.6.1.2.1.11.9.8.7"
     * @param prefix String, for ex # "1.3.6.1.2.1.1"
     * @return true, if "prefix" if prefix of oid, by after goes '.'
     */
    static boolean isProperPrefix(String oid, String prefix) {
        return oid.indexOf(prefix) == 0 && oid.charAt(prefix.length()) == '.';
    }
}
