#include <assert.h>
#include <limits.h>
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
  int *transitionRoot;
  List *transitionOthers;
  char *finite;
  int *supp;
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
  assert (t != NULL);
  
  // Initialisation
  t->maxNode = maxNode;
  t->nextNode = 1;
  
  // Initialisation du tableau de transition à la racine
  t->transitionRoot = calloc(UCHAR_MAX + 1, sizeof(*(t->transitionRoot)));
  assert(t->transitionRoot != NULL);
  
  // Initialisation de la liste des transitions
  t->transitionOthers = calloc(maxNode, sizeof(*(t->transitionOthers)));
  assert(t->transitionOthers != NULL);
  
  // Initialisation du tableau de suppléance
  t->supp = calloc(maxNode, sizeof(*(t->supp)));
  assert(t->supp != NULL);
  
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
  int targetNode = trie->transitionRoot[*w];
  
  /**
   * Parcours du plus grand préfixe commun
   */
  while (index < word_size && targetNode != 0) {
    ++index;
    currentNode = targetNode;
    
    if (index < word_size) {
      targetNode = find(trie->transitionOthers[currentNode], w[index]);
    }
  }
  
  /**
   * Phase d'insertion
   */
  // Cas où aucun préfixe commun n'existe : Insertion de la 1ère lettre.
  if (index == 0) {
    trie->transitionRoot[*w] = trie->nextNode;
    currentNode = trie->nextNode;
    ++trie->nextNode;
    ++index;
  }
  
  // Cas d'insertion du reste du mot dans le trie (si la capacité le permet)
  while (index < word_size && trie->nextNode < trie->maxNode) {
    // Indice du prochain noeud disponible
    int node_index = trie->nextNode;
    
    // Obtention de la liste dans laquelle insérer le nouveau noeud
    List *pl = &trie->transitionOthers[currentNode];
    
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
  int targetNode = trie->transitionRoot[*w];
  
  // Parcours
  while (index < word_size && targetNode != 0) {
    ++index;
    currentNode = targetNode;
    
    if (index < word_size) {
      targetNode = find(trie->transitionOthers[currentNode], w[index]);
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
		int dest = 0;
		if (k == 0) {
			dest = trie->transitionRoot[word[k]];
		} else {
			dest = find(trie->transitionOthers[currentNode], word[k]);
		}
		
		// Si dest vaut 0, il n'y a pas de transition...
		if (dest == 0) {
			printf("<4> Préfixe non trouvé : %s\n", word);
			return -1;
		}
    currentNode = dest;
  }
  
  // Retour du noeud trouvé
  printf("<4> Etat %d | Préfixe %s\n", currentNode, word);
  return currentNode;
}


/**
 * Réalise la fonction prepareAC de manière récursive. Cette fonction 
 * ne s'applique qu'aux noeuds présent dans des listes.
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
	printf("<4> Etat %d | ", q);
	trie->supp[q] = 0;
	for (size_t k = 1; k < size && supp == -1; ++k) {
		printf("Suff : %s | ", &word[k]);
		supp = prefixe(trie, &word[k]);
		
		// En cas d'existance, on affecte l'état associé en tant que suppléant.
		if (supp != -1) {
			trie->supp[q] = supp;
		}
	}
	printf("Suppléant %d\n", trie->supp[q]);

	// Application aux transistions suivantes
	List temp = trie->transitionOthers[q];
	while (temp != NULL) {
		if (temp->targetNode != 0) {
			s[size] = temp->letter;
			prepareRecursif(trie, temp->targetNode, s);
		}
		
		temp = temp->next;
	}
	
	// Libération des ressources
	free(s);
}

void prepareAC(Trie trie) {
	// Initialisation
	trie->supp[0] = 0;
	
	// Traitement des noeuds suivant la racine
	for (int k = 0; k <= UCHAR_MAX; ++k) {
		int dest = trie->transitionRoot[k];
		if (dest != 0) {
			// Définition du suppléant
			trie->supp[dest] = 0;
			
			// Appel à la fonction récursive
			unsigned char *s = malloc(2); {
				s[0] = k;
				s[1] = '\0';
			}
			prepareRecursif(trie, dest, s);
			free(s);
		}
	}
	prepareRecursif(trie, 0, (unsigned char *) "");
}


int executeAC(Trie trie, int *etat, unsigned char lettre) {
	// Calcul de l'état suivant
	int dest = 0;
	if (*etat == 0) {
		dest = trie->transitionRoot[lettre];
	} else {
		dest = find(trie->transitionOthers[*etat], lettre);
	}
	
	// Tant qu'aucune transition n'existe, on se réfère au descendant
	while (dest == 0 && etat != 0) {
		*etat = trie->supp[*etat];
		
		// Calcul de l'état suivant
		if (*etat == 0) {
			dest = trie->transitionRoot[lettre];
		} else {
			dest = find(trie->transitionOthers[*etat], lettre);
		}
	}
	
	// On applique la transition
	*etat = dest;
	
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
