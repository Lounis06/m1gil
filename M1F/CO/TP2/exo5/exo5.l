%{
	#include <stdio.h>
	
	/**
	*  Ici, j'utilise la définition suivante :
	*  s est une chaîne ssi :
	*    1) s = "x", avec x un mot quelconque
	*    2) s = "x\w, avec x un mot quelconque, et w une chaîne. 
	*/
%}

QUOTE  "\""

%%

{QUOTE}[^\"]*{QUOTE}   printf("|"); ECHO; printf("|\n"); if (yytext[yyleng - 2] == '\\') { 
                     yyless(2); yymore(); // Suppression du / inutile, et concaténation à la partie suivante
                   } else { 
                     yytext[yyleng - 1] = '\0'; ++yytext; ECHO; // Affichage de la chaîne sans quotes
                   }
. 						     ; // On ignore les caractères hors chaîne

%%

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
}

