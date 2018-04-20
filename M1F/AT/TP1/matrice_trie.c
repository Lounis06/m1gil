#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trie.h"

// ---- Structures ----
struct _trie {
  int maxNode;      // Nombre maximal de noeuds du trie
  int nextNode;     // Indice du prochain noeud disponible
  int **transition; // matrice de transition
  char *finite;     // etats terminaux
};


// ---- Fonctions ----
Trie createTrie(int maxNode) {
  // Allocation de la structure
  Trie t = malloc(sizeof(*t));
  if (t == NULL) {
    perror("createTrie : Echec d'allocation de mémoire");
    return NULL;
  }
  
  // Initialisation des valeurs de noeuds
  t->maxNode = maxNode;
  t->nextNode = 1;
  
  // Initialisation de la table des transitions
  t->transition = calloc(maxNode, sizeof(*(t->transition)));
  if (t->transition == NULL) {
    perror("createTrie : Echec d'allocation de mémoire");
    return NULL;
  }
  
  for (int k = 0; k < maxNode; ++k) {
     t->transition[k] = calloc(UCHAR_MAX + 1, sizeof(**(t->transition)));
     if (t->transition[k] == NULL) {
       perror("createTrie : Echec d'allocation de mémoire");
       return NULL;
     }
  }
  
  // Initialisation du tableau des terminaux
  t->finite = calloc(maxNode, sizeof(*(t->finite)));
  if (t->finite == NULL) {
    perror("createTrie : Echec d'allocation de mémoire");
    return NULL;
  }
  t->finite[0] = 1;
  
  // Retour du trie crée
  return t;
}


void insertInTrie(Trie trie, unsigned char *w) {
  // Cohérence des arguments
  if (trie == NULL || w == NULL) {
    perror("insertInTrie : Arguments entrés incorrects");
    return;
  }
  
  // Initialisation
  int word_size = strlen((const char *) w);
  int currentNode = 0;
  int index = 0;
  int nextNode = trie->transition[currentNode][w[index]];
  printf("> %d:%d", currentNode, nextNode);
  
  // Parcours du trie, jusqu'au plus grand préfixe de w contenu dans le trie
  while (index < word_size && nextNode != 0) {
    ++index;
    currentNode = nextNode;
    
    if (index < word_size) {
      nextNode = trie->transition[currentNode][w[index]];
      printf(" > %d:%d", currentNode, nextNode);
    }
  }
  printf(" | ");
  
  // Si le préfixe parcouru ne correspond pas au mot entier, on ajoute le reste
  // à la suite du trie (si la capacité le permet)
  while (index < word_size && trie->nextNode < trie->maxNode) {
    printf("> %d ", trie->nextNode);
    trie->transition[currentNode][w[index]] = trie->nextNode;
    currentNode = trie->nextNode;
    ++index;
    ++trie->nextNode;
  }
  printf(" | ");
  
  // On rend le dernier noeud terminal si le mot a correctement été lu ou ajouté
  if (index == word_size) {
    trie->finite[currentNode] = 1;
  }
}


int isInTrie(Trie trie, unsigned char *w) {
  // Cohérence des arguments
  if (trie == NULL || w == NULL) {
    perror("isInTrie : Arguments entrés incorrects");
    return 0;
  }
  
  // Initialisation
  int word_size = strlen((const char *) w);
  int currentNode = 0;
  int index = 0;
  int nextNode = trie->transition[currentNode][w[index]];
  
  // Parcours
  while (index < word_size && nextNode != 0) {
    ++index;
    currentNode = nextNode;
    
    if (index < word_size) {
      nextNode = trie->transition[currentNode][w[index]];
    }
  }
  
  // Renvoi de l'information souhaitée
  return index == word_size && trie->finite[currentNode];
}
