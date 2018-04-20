#include <stdio.h>
#include <stdlib.h>
#include "bloc.h"

int main() {
  bloc_t *row = new_bloc("mathml", NULL);
  bloc_t *op0 = new_bloc("mn", "1");
  bloc_t *sop = new_bloc("a", "jdbzk:j");
  bloc_t *op1 = new_bloc("mo", "+");
  bloc_t *op2 = new_bloc("mn", "3");
  bloc_t *op3 = new_bloc("mo", "=");
  bloc_t *op4 = new_bloc("mn", "4");
  add_bloc(op0, row);
  add_bloc(sop, op0);
  add_bloc(op1, row);
  add_bloc(op2, row);
  add_bloc(op3, row);
  add_bloc(op4, row);
  
  char *s = consume_bloc(&row);
  printf("%s", s);
  free(s);
}
