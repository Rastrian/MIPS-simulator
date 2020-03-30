NAME=MIPSCONV-FINAL
JARFILE=bin/$(NAME).jar
BINARY-FOLDER=bin
MANIFEST=$$(find . -name "MANIFEST.MF")
CLS=src
all: install build-jar clean run
build-jar:
	javac -d $(CLS) $$(find $(CLS) -name "*.java")
	cd ./$(CLS)/ && ls && jar cvfm ../$(JARFILE) $(MANIFEST) $$(find . -name "*.class")
clean:
	rm -rf $$(find $(CLS) -name "*.class")
install:
	sudo apt install default-jre default-jdk -y
	mkdir -p $(BINARY-FOLDER)
	rm -rf $(BINARY-FOLDER)/*
run:
	java -jar $(JARFILE)
travis-build: install build-jar clean
