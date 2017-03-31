package src;

import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import org.snmp4j;

/**
 * Main class providing grabbing functionality. Still work-in-progress
 */
public class Grabber {

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

//    /**
//     * Returns a Target, which contains information about where the data should be fetched and how.
//     */

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
//
//        if (true) {
//            SnmpAgent agent = configFile.routers.get(0);
//
//            Switch sw = new Switch(agent);
//            sw.discover(snmp);
//            System.out.println(sw);
//
////        Router rt = new Router(agent);
////        rt.discover(snmp);
////        System.out.println(rt);
//        }

        for (SnmpAgent agent: configFile.switches) {
            Switch sw = new Switch(agent);
            sw.discover(snmp);
            System.out.println(sw);
        }
        for (SnmpAgent agent: configFile.routers) {
            Router rt = new Router(agent);
            rt.discover(snmp);
            System.out.println(rt);
        }

        snmp.close();
    }
}
