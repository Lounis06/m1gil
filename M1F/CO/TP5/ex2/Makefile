
LEX=flex
YACC=bison
CC=gcc
CFLAGS=-std=c99 -pedantic -Wall
LDFLAGS=-lfl -lm
LFLAGS=-D_POSIX_SOURCE -DYY_NO_INPUT --nounput

calc: lex.yy.o calc.tab.o
	$(CC) $+ -o $@ $(LDFLAGS)

lex.yy.c: calc.lex calc.tab.h
	$(LEX) $(LFLAGS) $<

lex.yy.h: calc.lex
	$(LEX) --header-file=$@ $(LFLAGS) $<

calc.tab.c calc.tab.h: calc.y lex.yy.h
	$(YACC) $< -d

%.o: %.c
	$(CC) $(CFLAGS) $< -c

clean:
	-rm calc *.o lex.yy.* calc.tab.*
