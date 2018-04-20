# Constantes
# ---- Compilation
CC = gcc
CFLAGS = -std=c11 -pedantic -Wall -Wextra

# ---- Nom des fichiers objets requis
MATRICE_OBJS = matrice_trie.o main.o
LIST_OBJS = list_trie.o main.o
MIXTE_OBJS = mixte_trie.o main.o
GMOTS_OBJS = charbuffer.o genere-mots.o
GTEXTE_OBJS = charbuffer.o genere-texte.o

# ---- Nom des exécutables
MATRICE_EXEC = ac-matrice
LIST_EXEC = ac-liste
MIXTE_EXEC = ac-mixte
GMOTS_EXEC = genere-mots
GTEXTE_EXEC = genere-texte
EXECS = $(MATRICE_EXEC) $(LIST_EXEC) $(MIXTE_EXEC) $(GMOTS_EXEC) $(GTEXTE_EXEC)


# Règles de construction
all : ac-matrice ac-liste ac-mixte genere-mots genere-texte

ac-matrice : $(MATRICE_OBJS)
	$(CC) $(CFLAGS) $(MATRICE_OBJS) -o $(MATRICE_EXEC)

ac-liste : $(LIST_OBJS)
	$(CC) $(CFLAGS) $(LIST_OBJS) -o $(LIST_EXEC)
	
ac-mixte : $(MIXTE_OBJS)
	$(CC) $(CFLAGS) $(MIXTE_OBJS) -o $(MIXTE_EXEC)
	
genere-mots : $(GMOTS_OBJS)
	$(CC) $(CFLAGS) $(GMOTS_OBJS) -o $(GMOTS_EXEC)
	
genere-texte : $(GTEXTE_OBJS)
	$(CC) $(CFLAGS) $(GTEXTE_OBJS) -o $(GTEXTE_EXEC)


# Création des fichiers objets
main.o : trie.h main.c
matrice_trie.o : trie.h matrice_trie.c
list_trie.o : trie.h list_trie.c
mixte_trie.o : trie.h mixte_trie.c
genere-mots.o : charbuffer.h genere-mots.c
genere-texte.o : charbuffer.h genere-texte.c
charbuffer.o : charbuffer.h charbuffer.c


# Règle de nettoyage
clean : 
	$(RM) -vf $(EXECS) *.h.gch *.o
