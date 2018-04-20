# Constantes
CC = gcc
CFLAGS = -std=c11 -pedantic -Wall -Wextra
OBJS = algorithm.o main.o
EXEC = algorithm


# Règles de construction
all : $(OBJS)
	$(CC) $(CFLAGS) $(OBJS) -o $(EXEC)


# Création des fichiers objets
main.o : algorithm.h main.c
algorithm.o : algorithm.h algorithm.c


# Règle de nettoyage
clean : 
	$(RM) -vf $(OBJS) $(EXEC) *.h.gch
