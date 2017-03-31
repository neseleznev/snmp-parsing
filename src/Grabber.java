package src;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import static src.SnmpConstants.SYS_NAME;

//import org.snmp4j;

/**
 * Main class providing grabbing functionality
 */
public class Grabber {

    private Snmp snmp;
    private String address;

    public Grabber(String address) {
        this.address = address;
    }

    private static ConfigFile parse(String filename) {
        try {
            Lexer l = new Lexer(new BufferedReader(new FileReader(filename)));
            Parser p = new Parser(l);
            java_cup.runtime.Symbol parseTree = p.parse();
            return (ConfigFile) parseTree.value;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file " + filename);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Simple method which takes a single OID and returns the response from the agent as a String.
     */
    public String getAsString(OID oid) throws IOException {
        ResponseEvent event = get(new OID[]{oid});
        return event.getResponse().get(0).getVariable().toString();
    }

    /**
     * This method is more generic and is capable of handling multiple OIDs.
     * In a real application with lots of agents you would probably implement this asynchronously
     * with a ResponseListener instead to prevent your thread pool from being exhausted.
     */
    public ResponseEvent get(OID oids[]) throws IOException {
        PDU pdu = new PDU();
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, getTarget(), null);
        if(event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    /**
     * Returns a Target, which contains information about where the data should be fetched and how.
     */
    private Target getTarget() {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

    public static void main(String[] args) throws IOException {
        ConfigFile configFile = parse(args.length > 0 ? args[0] : "");
        if (configFile == null)
            return;

        System.out.println(configFile);


        // Create TransportMapping and Listen
        TransportMapping transport = new DefaultUdpTransportMapping(new UdpAddress());
        transport.listen();

        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(transport);

        SnmpAgent agent = configFile.switches.get(0);

        String[] oids = new String[] {SYS_NAME, "1.3.6.1.2.1.1.4.0"};
        PDU response = agent.getResponse(snmp, oids);

        if (response != null)
        {
            int errorStatus = response.getErrorStatus();
            int errorIndex = response.getErrorIndex();
            String errorStatusText = response.getErrorStatusText();

            if (errorStatus == PDU.noError)
            {
                System.out.println("Snmp Get Response = " + response.getVariableBindings());
            }
            else
            {
                System.out.println("Error: Request Failed");
                System.out.println("Error Status = " + errorStatus);
                System.out.println("Error Index = " + errorIndex);
                System.out.println("Error Status Text = " + errorStatusText + " " + agent.givenHostName);
            }
        }
        else
        {
            System.out.println("Error: Response PDU is null");
        }

        Vector<VariableBinding> res = (Vector<VariableBinding>) response.getVariableBindings();
        response.getVariableBindings().get(0).getVariable();

        Switch sw = new Switch(agent);

        sw.discoverVlans(snmp);
        System.out.println(sw);







        snmp.close();
//        String address = "demo.snmplabs.com"; //"udp:127.0.0.1/161";
//        Grabber client = new Grabber(address);
//        client.start();
//        String sysName = client.getAsString(new OID(".1.3.6.1.2.1.1.5"));
//        System.out.println(sysName);
    }
}
