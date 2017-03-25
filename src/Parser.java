
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.AuthSHA;
import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return Sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\022\000\002\002\004\000\002\002\004\000\002\010" +
    "\005\000\002\007\005\000\002\006\004\000\002\006\002" +
    "\000\002\011\006\000\002\011\006\000\002\011\010\000" +
    "\002\011\011\000\002\011\012\000\002\005\003\000\002" +
    "\014\003\000\002\003\003\000\002\003\003\000\002\004" +
    "\003\000\002\004\003\000\002\013\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\046\000\004\013\004\001\002\000\004\006\047\001" +
    "\002\000\004\011\011\001\002\000\004\002\007\001\002" +
    "\000\004\002\001\001\002\000\004\002\000\001\002\000" +
    "\004\006\012\001\002\000\006\002\ufffc\017\ufffc\001\002" +
    "\000\006\002\ufffe\017\014\001\002\000\010\014\ufff6\015" +
    "\ufff6\016\ufff6\001\002\000\010\002\ufffd\011\ufffd\017\ufffd" +
    "\001\002\000\010\014\017\015\020\016\021\001\002\000" +
    "\004\017\022\001\002\000\004\017\022\001\002\000\004" +
    "\017\022\001\002\000\012\006\ufff5\007\ufff5\010\ufff5\012" +
    "\ufff5\001\002\000\010\007\024\010\026\012\027\001\002" +
    "\000\004\017\ufff4\001\002\000\004\017\032\001\002\000" +
    "\004\010\030\001\002\000\004\017\ufff3\001\002\000\004" +
    "\006\031\001\002\000\010\002\ufff9\011\ufff9\017\ufff9\001" +
    "\002\000\012\004\ufff0\005\ufff0\006\ufff0\010\ufff0\001\002" +
    "\000\010\004\035\005\034\010\037\001\002\000\004\017" +
    "\ufff2\001\002\000\004\017\ufff1\001\002\000\004\017\032" +
    "\001\002\000\004\006\040\001\002\000\010\002\ufff8\011" +
    "\ufff8\017\ufff8\001\002\000\004\006\042\001\002\000\010" +
    "\002\ufff7\011\ufff7\017\ufff7\001\002\000\004\006\044\001" +
    "\002\000\010\002\ufffa\011\ufffa\017\ufffa\001\002\000\004" +
    "\006\046\001\002\000\010\002\ufffb\011\ufffb\017\ufffb\001" +
    "\002\000\006\011\ufffc\017\ufffc\001\002\000\006\011\uffff" +
    "\017\014\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\046\000\006\002\005\010\004\001\001\000\002\001" +
    "\001\000\004\007\007\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\006" +
    "\012\001\001\000\006\005\015\011\014\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\014" +
    "\044\001\001\000\004\014\042\001\001\000\004\014\022" +
    "\001\001\000\002\001\001\000\004\003\024\001\001\000" +
    "\002\001\001\000\004\013\032\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\004\035\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\013\040\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\006\047\001\001\000\006\005\015" +
    "\011\014\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    public Parser(Lexer lex) {
        super(lex);
    }

    /* Prints the parsed routers and switches info ou stdout. */
    public static void main(String[] args) {
        try {
            Lexer l = new Lexer(new BufferedReader(new FileReader(args[0])));
            Parser p = new Parser(l);
            java_cup.runtime.Symbol parseTree = p.parse();
            ConfigFile configFile = (ConfigFile) parseTree.value;
            System.out.println(configFile);
        } catch (Exception e) {
            // Sloppy error management, but good enough in our case
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /* Converts a string representation of host name or IP address (and
     * optional postfixed port number) into a SNMP4J UDP address. */
    public static UdpAddress parseHost(String s) throws UnknownHostException {
        String[] parts = s.split(":");
        InetAddress addr = InetAddress.getByName(parts[0]);
        int port = (parts.length == 2) ? Integer.parseInt(parts[1]) : 161;
        return new UdpAddress(addr, port);
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= configFile EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		ConfigFile start_val = (ConfigFile)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // configFile ::= switches routers 
            {
              ConfigFile RESULT =null;
		int ssleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int ssright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<SnmpAgent> ss = (List<SnmpAgent>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int rsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int rsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<SnmpAgent> rs = (List<SnmpAgent>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new ConfigFile(ss, rs); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("configFile",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // switches ::= SWITCHES EOL snmpEntries 
            {
              List<SnmpAgent> RESULT =null;
		int esleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int esright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<SnmpAgent> es = (List<SnmpAgent>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = es; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("switches",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // routers ::= ROUTERS EOL snmpEntries 
            {
              List<SnmpAgent> RESULT =null;
		int esleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int esright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<SnmpAgent> es = (List<SnmpAgent>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = es; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("routers",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // snmpEntries ::= snmpEntries snmpEntry 
            {
              List<SnmpAgent> RESULT =null;
		int esleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int esright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<SnmpAgent> es = (List<SnmpAgent>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		SnmpAgent e = (SnmpAgent)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 es.add(e); RESULT = es; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntries",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // snmpEntries ::= 
            {
              List<SnmpAgent> RESULT =null;
		 RESULT = new ArrayList<SnmpAgent>(); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntries",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // snmpEntry ::= host V1 secName EOL 
            {
              SnmpAgent RESULT =null;
		int hleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int hright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		UdpAddress h = (UdpAddress)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		OctetString c = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new SnmpAgent(h, 1, c); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntry",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // snmpEntry ::= host V2C secName EOL 
            {
              SnmpAgent RESULT =null;
		int hleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int hright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		UdpAddress h = (UdpAddress)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		OctetString c = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new SnmpAgent(h, 2, c); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntry",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // snmpEntry ::= host V3 secName NONE NONE EOL 
            {
              SnmpAgent RESULT =null;
		int hleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int hright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		UdpAddress h = (UdpAddress)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		OctetString name = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		 RESULT = new SnmpAgent(h, SecurityLevel.noAuthNoPriv,
                                  new UsmUser(name, null, null, null, null)); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntry",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // snmpEntry ::= host V3 secName authProto pass NONE EOL 
            {
              SnmpAgent RESULT =null;
		int hleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).left;
		int hright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).right;
		UdpAddress h = (UdpAddress)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-6)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		OctetString name = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int aidleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int aidright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		OID aid = (OID)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int apleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int apright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		OctetString ap = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		 RESULT = new SnmpAgent(h, SecurityLevel.authNoPriv,
                                  new UsmUser(name, aid, ap, null, null)); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntry",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // snmpEntry ::= host V3 secName authProto pass privProto pass EOL 
            {
              SnmpAgent RESULT =null;
		int hleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).left;
		int hright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).right;
		UdpAddress h = (UdpAddress)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-7)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		OctetString name = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int aidleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int aidright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		OID aid = (OID)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int apleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int apright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		OctetString ap = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int pidleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int pidright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		OID pid = (OID)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int ppleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int ppright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		OctetString pp = (OctetString)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new SnmpAgent(h, SecurityLevel.authPriv,
                                  new UsmUser(name, aid, ap, pid, pp)); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("snmpEntry",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // host ::= STRING 
            {
              UdpAddress RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String s = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = Parser.parseHost(s); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("host",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // secName ::= STRING 
            {
              OctetString RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String s = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new OctetString(s); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("secName",10, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // authProto ::= MD5 
            {
              OID RESULT =null;
		 RESULT = AuthMD5.ID; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("authProto",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // authProto ::= SHA 
            {
              OID RESULT =null;
		 RESULT = AuthSHA.ID; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("authProto",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // privProto ::= DES 
            {
              OID RESULT =null;
		 RESULT = PrivDES.ID; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("privProto",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // privProto ::= AES 
            {
              OID RESULT =null;
		 RESULT = PrivAES128.ID; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("privProto",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // pass ::= STRING 
            {
              OctetString RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String s = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new OctetString(s); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pass",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
