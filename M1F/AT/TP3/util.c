#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include "util.h"


/* ---- Mécanisme de gestion de l'aléatoire ---- */
bool rand_initialized = false;

/**
 * Initialise la suite de nombres aléatoires. 
 * Pour obtenir une nouvelle valeur, il suffira d'effectuer un appel à rand().
 */
static void init_rand() {
  srand(time(NULL));
  rand_initialized = true;
}


/* ---- Fonctions ---- */
inline int min(int a, int b) {
  return (a < b) ? a : b;
}

int random() {
  if (!rand_initialized) {
    init_rand();
  }
  
  return rand();
}

/**
 * Renvoie un nombre entier aléatoire compris dans un intervalle donné
 */
extern int rand_int_between(int a, int b);
