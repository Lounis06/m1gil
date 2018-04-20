#include <stdlib.h>
#include <string.h>
#include "list.h"

// ---- Structure ----
typedef struct cell {
  char *str;
  struct cell *next;
} cell__s;

struct list {
  cell__s *first;
};

// ---- Fonctions ----
list *list_new() {
  list *p = malloc(sizeof(*p));
  p->first = NULL;
  
  return p;
}

void list_add(list *l, const char *s) {
  cell__s *p = malloc(sizeof(*p)); {
    size_t n = strlen(s);
    p->str = malloc(n + 1); {
      memcpy(p->str, s, n);
      p->str[n] = 0;
    }
    
    p->next = l->first;
  }
  l->first = p;
}

int list_isIn(list *l, const char *s) {
  cell__s *p = l->first;
  while (p != NULL && strcmp(p->str, s) != 0) {
    p = p->next;
  }
  
  return p != NULL;
}

void list_free(list **ptrl) {
  cell__s *p = NULL;
  while ((*ptrl)->first != NULL) {
    p = (*ptrl)->first;
    
    free(p->str);
    (*ptrl)->first = p->next;
    free(p);
  }
  
  free(*ptrl);
  *ptrl = NULL;
}
