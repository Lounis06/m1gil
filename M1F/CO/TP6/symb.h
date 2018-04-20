#ifndef SYMB_H
#define SYMB_H

// ---- Structure ----
struct symbole_seq_s {
  char sym;
  double val;
  struct symbole_seq_s *next;
};

typedef struct symbole_seq_s *symb;

// ---- Fonctions ----
/**
 * Ajoute la valeur val à la liste des variables sous le nom sym
 * (Si une telle variable existe déjà, la valeur sera mise-à-jour)
 */
void add_symb(symb *ptrl, char sym, double val);

/**
 * Récupère dans la liste de variables, 
 * la valeur associée au nom de variable sym.
 */
double get_symb(symb l, char sym);

/**
 * Libère les ressources allouées par cette liste de symbole
 */
void free_symb_list(symb *ptrl);

#endif
