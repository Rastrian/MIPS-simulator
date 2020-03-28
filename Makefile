NAME=MIPSCONV-FINAL
JARFILE=bin/$(NAME).jar
BINARY-FOLDER=bin
MANIFEST=src/META-INF/MANIFEST.MF
CLS=src
all:
	javac -d $(CLS) $$(find $(CLS) -name "*.java")
# TODO: Rework for .jar
#	jar cvfm $(JARFILE) $(MANIFEST) $$(find . -name "*.class")
#	java -jar $(JARFILE)
	cd ./$(CLS)/ && java mips.converter.Main
clean:
	mkdir -p $(BINARY-FOLDER)
	rm -rf $(BINARY-FOLDER)/*
	rm -rf $$(find $(CLS) -name "*.class")
install:
	sudo apt install default-jre default-jdk -y
	mkdir -p $(BINARY-FOLDER)
	rm -rf $(BINARY-FOLDER)/*


