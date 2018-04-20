%{
#include <stdio.h>
#include <stdlib.h>
#include "act.h"
%}

VERBE     verbe
NOM       nom
PRONOM    pronom
ADJECTIF  adj

%%

^{VERBE}      mode = VERBE;
^{NOM}        mode = NOM;
^{PRONOM}     mode = PRONOM;
^{ADJECTIF}   mode = ADJECTIF;
[a-z]         act(yytext);

%%
int main(int argc, char **argv) {
  // "Suppression" du nom de programme
  ++argv, --argc;
  
  // Initialisation des listes
  ptr_lists = init_lists();
  
  // Lecture
  if (argc > 0) {
    yyin = fopen(argv[0], "r");
  } else {
    yyin = stdin;
  }
  
  // Libération des ressources
  free_lists();
  
  return EXIT_SUCCESS;
}

