# Constantes
# ---- Compilation
CC = gcc
CFLAGS = -std=c11 -pedantic -Wall -Wextra

# ---- Nom des fichiers objets requis
OBJS = charbuffer.o genere-mots.o

# ---- Nom des exécutables
EXEC = genere-mots


# Règles de construction
all : $(OBJS)
	$(CC) $(CFLAGS) $(OBJS) -o $(EXEC)


# Création des fichiers objets
genere-mots.o : charbuffer.h genere-mots.c
charbuffer.o : charbuffer.h charbuffer.c


# Règle de nettoyage
clean : 
	$(RM) -vf $(EXEC) *.h.gch *.o
