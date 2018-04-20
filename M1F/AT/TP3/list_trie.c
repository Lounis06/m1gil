#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trie.h"

// ---- Structures ----
typedef struct _list* List;

struct _list {
  int targetNode;       // cible de la transition
  unsigned char letter; // etiquette de la transition
  List next;   // maillon suivant
};

struct _trie {
  int maxNode;
  int nextNode;
  List *transition;
  char *finite;
};


// ---- Outils
/**
 * Parcours la liste de manière à donner sur le noeud cible, pour une 
 * lettre donnée.
 * 
 * Dans le cas, où la liste ne contient pas de noeud etiqueté par
 * cette lettre, renvoie 0
 */
static int find(List l, unsigned char letter) {
  if (l == NULL) {
    return 0;
  }
  if (l->letter == letter) {
    return l->targetNode;
  }
  return find(l->next, letter);
}


/**
 * Crée une nouvelle cellule à partir d'une lettre et d'un indice donné.
 */
List createList(unsigned char letter, int targetNode) {
  // Allocation de la cellule
  List temp = malloc(sizeof(*temp));
  assert(temp != NULL);
  
  // Définition
  temp->letter = letter;
  temp->targetNode = targetNode;
  
  return temp;
}


// ---- Fonctions ----
Trie createTrie(int maxNode) {
  // Allocation de la structure
  Trie t = malloc(sizeof(*t));
  assert(t != NULL);
  
  // Initialisation des valeurs de noeuds
  t->maxNode = maxNode;
  t->nextNode = 1;
  
  // Initialisation de la liste des transitions
  t->transition = calloc(maxNode, sizeof(*(t->transition)));
  assert(t->transition != NULL);
  
  // Initialisation du tableau des terminaux
  t->finite = calloc(maxNode, sizeof(*(t->finite)));
  assert(t->finite != NULL);
  t->finite[0] = 1;
  
  // Retour du trie crée
  return t;
}


void insertInTrie(Trie trie, unsigned char *w) {
  // Cohérence des arguments
  assert(trie != NULL && w != NULL);
  
  // Initialisation
  size_t word_size = strlen((const char *) w);
  size_t index = 0;
  int currentNode = 0;
  int targetNode = find(trie->transition[currentNode], w[index]);
  
  // Parcours du trie, jusqu'au plus grand préfixe de w contenu dans le trie
  while (index < word_size && targetNode != 0) {
    ++index;
    currentNode = targetNode;
    
    if (index < word_size) {
      targetNode = find(trie->transition[currentNode], w[index]);
    }
  }
  
  // Si le préfixe parcouru ne correspond pas au mot entier, on ajoute le reste
  // dans le trie (si la capacité le permet)
  while (index < word_size && trie->nextNode < trie->maxNode) {
    // Indice du prochain noeud disponible
    int node_index = trie->nextNode;
    
    // Obtention de la liste dans laquelle insérer le nouveau noeud
    List *pl = &trie->transition[currentNode];
    
    // Insertion du nouveau noeud en tête de la liste
    List temp = createList(w[index], node_index);
    if (*pl != NULL) {
      temp->next = *pl;
    }
    *pl = temp;
    
    
    // Mise-à-jour des variables d'itération
    currentNode = node_index;
    ++index;
    ++trie->nextNode;
  }
  
  // On rend le dernier noeud terminal si le mot a correctement été lu ou ajouté
  if (index == word_size) {
    trie->finite[currentNode] = 1;
  }
}


int isInTrie(Trie trie, unsigned char *w) {
  // Cohérence des arguments
  assert(trie != NULL && w != NULL);
  
  // Initialisation
  size_t word_size = strlen((const char *) w);
  size_t index = 0;
  int currentNode = 0;
  int targetNode = find(trie->transition[currentNode], w[index]);
  
  // Parcours
  while (index < word_size && targetNode != 0) {
    ++index;
    currentNode = targetNode;
    
    if (index < word_size) {
      targetNode = find(trie->transition[currentNode], w[index]);
    }
  }
  
  // Renvoi de l'information souhaitée
  return index == word_size && trie->finite[currentNode];
}
