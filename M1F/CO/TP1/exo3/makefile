# Constantes
FLEX = flex
LFLAGS=-D_POSIX_SOURCE -o $@ -DYY_NO_INPUT --nounput
FLFLAGS = -lfl
CC = gcc
CFLAGS = -std=c11 -pedantic -Wall -Wextra
OBJS = list.o act.o exo3.o
EXEC = a.out


# Règles de construction
all : $(OBJS)
	$(CC) $(CFLAGS) $(FLFLAGS) $(OBJS) -o $(EXEC)


# Création des fichiers objets
exo3.c : exo3.l
	$(FLEX) $(LFLAGS) $< 
exo3.o : act.h exo3.c
act.o : act.h list.h act.c
list.o : list.h list.c


# Règle de nettoyage
clean : 
	$(RM) -vf $(EXEC) $(OBJS) *.h.gch
