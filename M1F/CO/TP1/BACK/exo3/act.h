#ifndef ACT_H
#define ACT_H

#include <stdlib.h>
#include "list.h"

// ---- Types ----
/* 
 * Enumération servant à définir des indices correspondant aux options, 
 * la valeur SIZE ne sert qu'à décrire le nombre de catégories de mot.
 */
enum {
  READ = -1, VERBE = 0, NOM = 1, PRONOM = 2, ADJECTIF = 3, SIZE = 4
};

// ---- Constantes ---- //
// Listes utilisables
list** ptr_lists = NULL;

// Messages de description correspondant aux catégories
const char *categories[SIZE] = {"verbe", "nom", "pronom", "adjectif"};

// Valeur décrivant le comportement de la fonction act
int mode = READ;


// ---- Fonctions ----
/**
 * Initialise les différentes listes employées
 */
extern list** init_lists();

/**
 * Gère la suppression des listes, en fin de programme
 */
extern void free_lists();


/**
 * Effectue l'action nécessaire à la lecture d'un mot ordinaire.
 */
extern void act(const char *word);

#endif

