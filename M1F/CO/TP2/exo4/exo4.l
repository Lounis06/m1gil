%{
	#include <stdio.h>
	#include <stdlib.h>
	
	enum {
		ACAT = 0, ATTA = 1, CGC = 2, CGCAA = 3, SIZE = 4
	};
	
	int tab[SIZE];
%}

%%
ACAT	 ++tab[ACAT];
ATTA   ++tab[ATTA];
CGC    ++tab[CGC];
CGCAA  ++tab[CGCAA]; REJECT;
%%

void print_occurences() {
	printf("\nCompteur d'occurences :\n");
	printf("> ACAT : %d\n", tab[ACAT]);
	printf("> ATTA : %d\n", tab[ATTA]);
	printf("> CGC : %d\n", tab[CGC]);
	printf("> CGCAA : %d\n", tab[CGCAA]);
}

int main(int argc, char **argv) {
	// "Suppression" du nom de programme
  ++argv, --argc;

	// Lecture (depuis le fichier, si défini)
  if (argc > 0) {
    yyin = fopen(argv[0], "r");
  } else {
    yyin = stdin;
  }
  
  // Lancement de l'analyse lexicale
	yylex();
	
	// Affichage des résultats
	print_occurences();
}
