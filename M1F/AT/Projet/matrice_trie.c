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
  int *supp;			  // Tableau d'états de la fonction de suppléance
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
  
  // Initialisation du tableau de suppléance
  t->supp = calloc(maxNode, sizeof(*(t->transition)));
  if (t->supp == NULL) {
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
  t->finite[0] = 0;
  
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
  
  // Parcours du trie, jusqu'au plus grand préfixe de w contenu dans le trie
  while (index < word_size && nextNode != 0) {
    ++index;
    currentNode = nextNode;
    
    if (index < word_size) {
      nextNode = trie->transition[currentNode][w[index]];
    }
  }
  
  // Si le préfixe parcouru ne correspond pas au mot entier, on ajoute le reste
  // à la suite du trie (si la capacité le permet)
  while (index < word_size && trie->nextNode < trie->maxNode) {
    trie->transition[currentNode][w[index]] = trie->nextNode;
    currentNode = trie->nextNode;
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


// ---- Algorithme d'Aho-Corasick ----
/**
 * Renvoie le noeud duquel mot, est le préfixe d'un mot du trie.
 * Renvoie -1, si le préfixe ne figure pas dans le trie.
 */
static int prefixe(Trie trie, unsigned char *word) {
	// Initialisation
	int currentNode = 0;
	size_t size = strlen((char *) word);
	
	// Parcours du trie pour trouver le prefixe
  for (size_t k = 0; k < size; ++k) {
		int dest = trie->transition[currentNode][word[k]];
		
		// Si dest vaut 0, il n'y a pas de transition...
		if (dest == 0) {
			return -1;
		}
    currentNode = dest;
  }
  
  // Retour du noeud trouvé
  return currentNode;
}


/**
 * Réalise la fonction prepareAC de manière récursive
 */
static void prepareRecursif(Trie trie, int q, unsigned char *word) {
	// Initialisation du mot suivant
	size_t size = strlen((char *) word);
	unsigned char *s = malloc(size + 2);
	memcpy(s, word, size);
	s[size + 1] = '\0';
	
	// Définition du suppléant : On recherche le plus long suffixe propre de word
	// qui soit un préfixe d'un mot du trie.
	int supp = -1;
	trie->supp[q] = 0;
	for (size_t k = 1; k < size && supp == -1; ++k) {
		supp = prefixe(trie, &word[k]);
		
		// En cas d'existance, on affecte l'état associé en tant que suppléant.
		if (supp != -1) {
			trie->supp[q] = supp;
		}
	}

	// Application au noeud suivant
	for (int k = 0; k <= UCHAR_MAX; ++k) {
		int dest = trie->transition[q][k];
		if (dest != 0) {
			unsigned char c = k;
			s[size] = c;
			prepareRecursif(trie, dest, s);
		}
	}
	
	// Libération des ressources
	free(s);
}

void prepareAC(Trie trie) {
	prepareRecursif(trie, 0, (unsigned char *) "");
}


int executeAC(Trie trie, int *etat, unsigned char lettre) {
	// Tant qu'aucune transition n'existe, on se réfère au descendant
	while (trie->transition[*etat][lettre] == 0 && *etat != 0) {
		*etat = trie->supp[*etat];
	}
	
	// On applique la transistion
	*etat = trie->transition[*etat][lettre];
	
	// On cherche le nombre de mots trouvés en visitant les différents suppléants
	int supp = *etat;
	int count = 0;
	while (supp != 0) {
		if (trie->finite[supp]) {
			count++;
		}
		
		supp = trie->supp[supp];
	}
	
	// On renvoie le nombre de mots validés.
	return count;
}
