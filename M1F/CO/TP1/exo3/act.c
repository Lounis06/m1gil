#include <stdio.h>
#include <stdlib.h>
#include "act.h"

// ---- Fonctions ----
list** init_lists() {
  list **tab = calloc(SIZE, sizeof(*tab));
  for (int k = 0; k <= SIZE; ++k) {
    tab[k] = list_new();
  }
  
  return tab;
}

void free_lists() {
  for (int k = 0; k <= SIZE; ++k) {
    list_free(&ptr_lists[k]);
  }
  free(ptr_lists);
}

void act(const char *word) {
  // Lecture du mot via l'un des dictionnaires
  if (mode == READ) {
    int k = 0;
    while (k < SIZE && !list_isIn(ptr_lists[k], word)) {
      ++k;
    }
    if (k == SIZE) {
      printf("%s : non reconnu\n", word);
    } else {
      printf("%s : %s\n", word, categories[k]);
    }

  // Ajout du mot dans l'un des dictionnaire
  } else if (0 <= mode && mode < SIZE) {
    list_add(ptr_lists[mode], word);
  }
}
