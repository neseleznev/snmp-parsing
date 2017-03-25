CLSPATH=.:/usr/share/java/cup.jar:/opt/snmp4j-2.4.3/dist/snmp4j-2.4.3.jar:out/production/snmp-parsing

# We recompile everything each time, since we don't know about Java
# dependencies. Use something else like ant for better results.
.PHONY:	all
all:	clean Parser.class
	java -cp $(CLSPATH) src.Parser lab.cfg

src/Lexer.java:	jflex/Lexer.flex
	jflex -d src $<

src/Parser.java:	cup/Parser.cup
	java -jar tools/java-cup-11b.jar -destdir src -parser Parser -symbols Sym $<

Parser.class:	src/Parser.java src/Lexer.java
	javac -cp $(CLSPATH) -d out/production/snmp-parsing $<

.PHONY:	clean
clean:
	$(RM) -f *~ out/production/snmp-parsing/*/*.class src/Lexer.java src/Parser.java src/Sym.java


# directives without recompiling Jflex & Cup
all_a:	clean_a Parser.class
	java -cp $(CLSPATH) Parser lab.cfg

clean_a:
	$(RM) *~ out/production/snmp-parsing/*/*.class
