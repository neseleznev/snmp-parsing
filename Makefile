CLSPATH=.:/usr/share/java/cup.jar:/opt/snmp4j-2.4.3/dist/snmp4j-2.4.3.jar

# We recompile everything each time, since we don't know about Java
# dependencies. Use something else like ant for better results.
.PHONY:	all
all:	clean Parser.class
	java -cp $(CLSPATH) Parser lab.cfg

Lexer.java:	Lexer.flex
	jflex $<

Parser.java:	Parser.cup
	cup -parser Parser -symbols Sym $<

Parser.class:	Parser.java Lexer.java
	javac -cp $(CLSPATH) $<

.PHONY:	clean
clean:
	rm -f *~ *.class Lexer.java Parser.java Sym.java
