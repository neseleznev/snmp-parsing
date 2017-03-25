import java_cup.runtime.Symbol;

/* There are so few keywords that we don't bother with an hash table.
 * Note that IP addresses and host names can contain a postfixed port number.
 * They are returned as string, and further processed in the parser. */

%%

%class Lexer
%unicode
%cupsym Sym
%cup
%line
%column

%{
private Symbol symbol(int type) { return new Symbol(type, yyline, yycolumn); }

private Symbol symbol(int type, Object val) {
    return new Symbol(type, yyline, yycolumn, val);
}
%}

EOL = \r|\n|\r\n
Whitespace = [ \t\f]
String = [^ \t\f\n\r]+
IPByte = 0?0?[0-9]|0?[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]
Port = :[0-9]+ // A self-checking regexp would be too long here
Address = {IPByte}\.{IPByte}\.{IPByte}\.{IPByte}{Port}?

%%

{Whitespace}  { /* Ignore */ }
{EOL}         { return symbol(Sym.EOL); }
"AES"         { return symbol(Sym.AES); }
"DES"         { return symbol(Sym.DES); }
"MD5"         { return symbol(Sym.MD5); }
"NONE"        { return symbol(Sym.NONE); }
"SHA"         { return symbol(Sym.SHA); }
"[Routers]"   { return symbol(Sym.ROUTERS); }
"[Switches]"  { return symbol(Sym.SWITCHES); }
"1"           { return symbol(Sym.V1); }
"2c"          { return symbol(Sym.V2C); }
"3"           { return symbol(Sym.V3); }
{Address}     { return symbol(Sym.STRING, yytext()); }
{String}      { return symbol(Sym.STRING, yytext()); }
<<EOF>>       { return symbol(Sym.EOF); }
