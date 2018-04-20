%{
	#include <stdio.h>
	#include <stdlib.h>
	
  enum {
    CHARACTER = 0, WORD = 1, LINE = 2, ALL = 3
  };
  
  int mode = ALL;
  int count[ALL];
%}

NOT_BLANK  [^ \t\n]

%%

{NOT_BLANK}+  count[CHARACTER] += yyleng; ++count[WORD];
" "|"\t"      ++count[CHARACTER];
"\n"          ++count[CHARACTER]; ++count[LINE];

%%

void select_mode(char *str) {
  // Comptage des caractères
  if (strcmp(str, "-m") == 0) {
    mode = CHARACTER;
    return;
  }
  
  // Comptage des mots
  if (strcmp(str, "-w") == 0) {
    mode = WORD;
    return;
  }
  
  // Comptage des lignes
  if (strcmp(str, "-l") == 0) {
    mode = LINE;
    return;
  }
  
  // Mode par défaut
  mode = ALL;
}

// Commande de lancement : ./exo7 "nom_de_fichier"? "option"
int main(int argc, char **argv) {
	// "Suppression" du nom de programme
  ++argv, --argc;

	// Lecture (depuis le fichier, si défini)
  if (argc > 0) {
    yyin = fopen(argv[0], "r");
  } else {
    yyin = stdin;
  }
  
  // Séléction du mode de comptage
  if (argc > 1) {
    select_mode(argv[argc - 1]);
  }
  
  // Lancement de l'analyse lexicale
	yylex();
  
  // Affichage des résultats
  for (int k = ALL - 1; k >= 0; --k) {
    if (mode == k || mode == ALL) {
      printf("%d ", count[k]);
    }
  }
  printf("\n");
}
