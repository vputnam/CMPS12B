# A simple Makefile
FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse Manifest FileReverse.class 
	rm Manifest
	chmod +x FileReverse
FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java
clean:
	rm -f FileReverse FileReverse.class
submit: README Makefile FileReverse.java
	submit cmps012b-pt.s15 lab2 README Makefile FileReverse.java

