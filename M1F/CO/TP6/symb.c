#include <stdlib.h>
#include "symb.h"

void add_symb(symb *ptrl, char sym, double val) {
  symb temp = malloc(sizeof(temp));
  temp->val = val;
  temp->sym = sym;
  temp->next = *ptrl;
  *ptrl = temp;
}

double get_symb(symb l, char sym) {
  while (l != NULL && l->sym != sym) {
    l = l->next;
  }
  
  return (l == NULL) ? 0 : l->val;
}

void free_symb_list(symb *ptrl) {
  symb temp = NULL;
  
  while (ptrl != NULL) {
    temp = *ptrl;
    *ptrl = (*ptrl)->next;
    free(temp);
  }
  
  *ptrl = NULL;
}
