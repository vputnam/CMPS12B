# A simple Makefile for Search.java
Search: Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class 
	rm Manifest
	chmod +x Search
Search.class: Search.java
	javac -Xlint Search.java
clean:
	rm -f Search Search.class
submit: README Makefile Search.java
	submit cmps012b-pt.s15 pa2 README Makefile Search.java
