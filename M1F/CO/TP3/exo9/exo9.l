%{
	#include <stdio.h>
	#include <stdlib.h>
	
	int a = 0, b = 0, height = 0, factors = 0;
	
	void reset();
	void print_results();
	void raise_error();
%}

%%
a				  ++a; if (a - b > height) height = a - b;
b				  ++b; if (a < b) raise_error("Un préfixe possède plus de b que de a"); if (a == b) factors++;
.					raise_error("Caractère inconnu");
.$				{ if (yytext[0] != 'b') raise_error("Un mot de Dyck doit forcément terminer par b"); 
						++b; if (a != b) raise_error("|a| != |b|"); ++factors;
						print_results(); reset(); }

%%
// Stoppe le processus en cas d'erreur
void raise_error(const char *str) {
	fprintf(stderr, "Mot mal formé : %s\n", str); 
	exit(EXIT_FAILURE);
}

// Réinitialise les variables d'analyse
void reset() {
	a = 0;
	b = 0;
	height = 0;
	factors = 0;
}

// Affiche les résultats à l'écran
void print_results() {
	printf(">> Résultats :\n");
	printf("Longueur du mot : %d\n", a + b);
	printf("Hauteur du mot : %d\n", height);
	printf("Nombre de facteurs propres : %d\n", factors);
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
}
