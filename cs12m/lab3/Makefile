# makefile for FileReverse.c

FileReverse: FileReverse.o
	gcc -o FileReverse FileReverse.o
FileReverse.o : FileReverse.c
	gcc -c –std=c99 -Wall FileReverse.c
clean :
	rm -f FileReverse FileReverse.o
submit: 
	submit cmps012b-pt.s15 lab3 README Makefile FileReverse.c
