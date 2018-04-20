#ifndef ALGORITHM_H
#define ALGORITHM_H

#include <stdio.h>

// ---- Fonctions sur des textes sous forme de chaînes ----
/* Variantes de l'algorithme naïf */
/**
 * Utilise l'algorithme naïf avec le mot word, dans le texte text, 
 * afin de renvoyer le nombre d'occurences du mot recherché.
 * 
 * Affiche sur la sortie standard, les indices des différentes occurences
 * dans le texte.
 */
extern void search_naive(const unsigned char *text, 
    const unsigned char *word);

/**
 * Version améliorée de search_naive, avec l'emploi d'une boucle rapide
 */
extern void search_naive_ql(const unsigned char *text, 
    const unsigned char *word);
    
/**
 * Version améliorée de search_naive_ql, avec l'emploi d'une sentinelle
 */
extern void search_naive_ql_sn(const unsigned char *text, 
    const unsigned char *word);

/**
 * Version de search_naive, utilisant la fonction strncmp.
 */
extern void search_naive_ncmp(const unsigned char *text, 
    const unsigned char *word);

/**
 * Version de search_naive_ql, utilisant la fonction strncmp.
 */
extern void search_naive_ql_ncmp(const unsigned char *text, 
    const unsigned char *word);
    
/**
 * Version de search_naive_ql_sn, utilisant la fonction strncmp.
 */
extern void search_naive_ql_sn_ncmp(const unsigned char *text, 
    const unsigned char *word);


/* Algorithmes de MP et KMP */
/**
 * Utilise l'algorithme de Morris-Pratt avec le mot word, dans le texte text, 
 * afin de renvoyer le nombre d'occurences du mot recherché.
 * 
 * Affiche sur la sortie standard, les indices des différentes occurences
 * dans le texte.
 */
extern void search_mp(const unsigned char *text, const unsigned char *word);

/**
 * Utilise l'algorithme de Knuth-Morris-Pratt avec le mot word, dans le texte text, 
 * afin de renvoyer le nombre d'occurences du mot recherché.
 * 
 * Affiche sur la sortie standard, les indices des différentes occurences
 * dans le texte.
 */
extern void search_kmp(const unsigned char *text, const unsigned char *word);

/* Algorithme de Boyer-Moore */
/**
 * Utilise l'algorithme de Boyer-Moore avec le mot word, dans le texte text, 
 * afin de renvoyer le nombre d'occurences du mot recherché.
 * 
 * Affiche sur la sortie standard, les indices des différentes occurences
 * dans le texte.
 */
extern void search_bm(const unsigned char *text, const unsigned char *word);


#endif
