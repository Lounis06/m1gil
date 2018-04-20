%{
	#include <stdio.h>
	#include <stdbool.h>
	
	bool comment_bloc = false;
	bool comment_line = false;
%}

%%
"/**"|"/*"  comment_bloc = true;   // Ouverture du bloc commentaire
[ \t]*"*/"|[ \t]*"**/"  comment_bloc = false;  // Fermeture du bloc commentaire
[ \t]*"*"  ;		// Passage à la ligne suivante du commentaire
"//"  if (comment_bloc) ECHO; comment_line = true;  // Commentaire de ligne
"\n"  if (comment_bloc || comment_line) printf("\n"); comment_line = false; // Fin de ligne
.     if (comment_bloc || comment_line) ECHO;  // Affichage du commentaire

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
	
	// Marqueur de fin de lecture
	printf("\n -- FIN DE LECTURE --\n");
}
