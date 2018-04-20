#ifndef UTIL_H
#define UTIL_H

#include <stdbool.h>

/* ---- Fonctions ---- */
/**
 * Calcule le minimum de 2 valeurs entières
 */
extern inline int min(int a, int b);

/**
 * Renvoie un nombre entier aléatoire
 */
extern int rand_int();

/**
 * Renvoie un nombre entier aléatoire compris dans un intervalle donné
 */
extern int rand_int_between(int a, int b);


#endif
