CLSPATH=.:lib/java-cup-11b-runtime.jar:lib/snmp4j-2.4.3.jar:out/production/snmp-parsing

# We recompile everything each time, since we don't know about Java
# dependencies. Use something else like ant for better results.
.PHONY:	all
all:	clean Parser.class classes executable

src/Lexer.java:	jflex/Lexer.flex
	jflex -d src $<

src/Parser.java:	cup/Parser.cup
	java -jar tools/java-cup-11b.jar -destdir src -parser Parser -symbols Sym $<

Parser.class:	src/Parser.java src/Lexer.java
	javac -cp $(CLSPATH) $<
	# -d out/production/snmp-parsing $<

classes:
	javac -cp $(CLSPATH) src/*.java
	# -d out/production/snmp-parsing src/*.java

executable:
	jar -cmf MANIFEST.MF netanalysis.jar src

.PHONY:	clean
clean:
	$(RM) -f *~ src/*.class src/Lexer.java src/Parser.java src/Sym.java netanalysis.jar
	# out/production/snmp-parsing/*/*.class
