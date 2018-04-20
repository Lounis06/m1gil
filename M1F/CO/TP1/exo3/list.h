#ifndef LIST_H
#define LIST_H

// ---- Structure ----
typedef struct list list;

// ---- Fonctions ----
/**
 * Alloue et renvoie une nouvelle liste, initialisée au vide.
 */
extern list *list_new();

/**
 * Ajoute une nouvelle chaîne en tête de la liste
 */
extern void list_add(list *l, const char *s);

/**
 * Indique si une chaîne est présent dans une liste donnée
 */
extern int list_isIn(list *l, const char *s);

/**
 * Supprime la liste et libère toutes les ressources précédemment utilisées
 */
extern void list_free(list **ptrl);

#endif
