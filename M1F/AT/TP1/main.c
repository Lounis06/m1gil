#include <stdio.h>
#include <stdlib.h>
#include "trie.h"

#define WORD_NB 4
#define SEPARATOR "------------------------------\n"

int main() {
  // Test n°1 : Création d'un trie
  Trie t = createTrie(32);
  if (t == NULL) {
    printf("<1> Erreur\n");
    return EXIT_FAILURE;
  }
  printf("<1> Création du trie réussie\n%s", SEPARATOR);
  
  // Procédure d'insertion
  unsigned char words[WORD_NB][5] = {"aata", "att", "tata", "aaa"};
  for (int k = 0; k < WORD_NB; ++k) {
    insertInTrie(t, words[k]);
    printf("<2> Insertion de \"%s\" terminée\n", words[k]);
  }
  printf("%s", SEPARATOR);
  
  // Tests de lecture
  for (int k = 0; k < WORD_NB; ++k) {
    printf("<3> Présence de \"%s\" : ", words[k]);
    if (isInTrie(t, words[k])) {
      printf("Réussite\n");
    } else {
      printf("Echec\n");
    }
  }
  
  printf("<3> Absence de \"atata\" : ");
  if (!isInTrie(t, (unsigned char*) "atata")) {
    printf("Réussite\n");
  } else {
    printf("Echec\n");
  }
  
  printf("<3> Absence de \"at\" : ");
  if (!isInTrie(t, (unsigned char*) "at")) {
    printf("Réussite\n");
  } else {
    printf("Echec\n");
  }
  
  return EXIT_SUCCESS;
}
