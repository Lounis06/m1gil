# Constantes
FLEX = flex
LFLAGS=-D_POSIX_SOURCE -o $@ -DYY_NO_INPUT --nounput
FLFLAGS = -lfl
CC = gcc
CFLAGS = -std=c11 -pedantic -Wall -Wextra
OBJS = list.o act.o lex.yy.o
EXEC = a.out
FILE = exo3.l


# Règles de construction
all : exe flex
	
flex : 
	$(FLEX) $(LFLAGS) $(FILE)
	
exe : $(OBJS)
	$(CC) $(CFLAGS) $(FLFLAGS) $(OBJS) -o $(EXEC)


# Création des fichiers objets
lex.yy.o : act.h lex.yy.c
	
act.o : act.h list.h act.c

list.o : list.h list.c


# Règle de nettoyage
clean : 
	$(RM) -vf $(EXEC) $(OBJS) *.h.gch
