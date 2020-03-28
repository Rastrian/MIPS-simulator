NAME=MIPSCONV-FINAL
JARFILE=$(NAME).jar
all:
	sudo apt install unzip default-jre default-jdk maven -y
	sudo mvn clean package
	sudo chmod +x ./target/$(JARFILE)
	java -jar ./target/$(JARFILE)