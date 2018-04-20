# Constantes
# ---- Compilation
CC = gcc
CFLAGS = -std=c11 -pedantic -Wall -Wextra

# ---- Nom des fichiers objets requis
MATRICE_OBJS = matrice_trie.o main.o
LIST_OBJS = list_trie.o main.o
MIXTE_OBJS = mixte_trie.o main.o
OBJS = matrice_trie.o liste_trie.o mixte_trie.o main.o

# ---- Nom des exécutables
MATRICE_EXEC = matrice
LIST_EXEC = list
MIXTE_EXEC = mixte
EXECS = $(MATRICE_EXEC) $(LIST_EXEC) $(MIXTE_EXEC)


# Règles de construction
all : matrice list mixte
	
matrice : $(MATRICE_OBJS)
	$(CC) $(CFLAGS) $(MATRICE_OBJS) -o $(MATRICE_EXEC)

list : $(LIST_OBJS)
	$(CC) $(CFLAGS) $(LIST_OBJS) -o $(LIST_EXEC)
	
mixte : $(MIXTE_OBJS)
	$(CC) $(CFLAGS) $(MIXTE_OBJS) -o $(MIXTE_EXEC)


# Création des fichiers objets
main.o : trie.h main.c
matrice_trie.o : trie.h matrice_trie.c
list_trie.o : trie.h list_trie.c
mixte_trie.o : trie.h mixte_trie.c


# Règle de nettoyage
clean : 
	$(RM) -vf $(EXECS) *.h.gch *.o
