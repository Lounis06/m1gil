%{
	#include <stdio.h>
	#include <stdlib.h>
	
  enum {
    CHARACTER = 0, WORD = 1, LINE = 2, SIZE = 3
  };
  
  int count[SIZE];
  int total[SIZE];
  int args_nb;
  char **args;
%}

NOT_BLANK  [^ \t\n]

%%

{NOT_BLANK}+  count[CHARACTER] += yyleng; ++count[WORD];
" "|"\t"      ++count[CHARACTER];
"\n"          ++count[CHARACTER]; ++count[LINE];

%%
/**
 * Ouvre le prochain fichier valide, de la liste de fichiers à analyser.
 *
 * Si un tel fichier existe, yyin pointera sur lui. Sinon yyin vaudra NULL
 * et args_nb sera égal à 0.
 */
void open_next_file() {
  // Fermeture du fichier précédent (s'il existe)
  if (yyin != NULL) {
    fclose(yyin);
    yyin = NULL;
    ++args, --args_nb;
  }

  // Ouverture du prochain fichier valide
  while (yyin == NULL && args_nb > 0) {
    // Ouverture du fichier suivant
    yyin = fopen(*args, "r");
    
    // Cas d'ouverture impossible
    if (yyin == NULL) {
      printf("Problème d'ouverture du fichier \"%s\"\n", *args);
      ++args, --args_nb;
    }
  }
}

int yywrap() {
  // Affichage des résultats obtenus pour le fichier analysé
  for (int k = SIZE - 1; k >= 0; --k) {
    printf("\t%d", count[k]);
  }
  printf("\t%s\n", *args);
  
  // Actualisation des variables
  for (int k = SIZE - 1; k >= 0; --k) {
    total[k] += count[k];
    count[k] = 0;
  }
  
  // On tente d'ouvrir le prochain fichier
  open_next_file();
  
  // Si tous les fichiers ont été analysés, on affiche le total.
  if (args_nb == 0) {
    for (int k = SIZE - 1; k >= 0; --k) {
      printf("\t%d", total[k]);
    }
    printf("\tTotal\n");
    return 1;
  }
  
  // Sinon, on continue l'analyse
  return 0;
}

// Commande de lancement : ./exo10 "nom_de_fichier"+
int main(int argc, char **argv) {
	// "Suppression" du nom de programme
  ++argv, --argc;

	// Lecture
  if (argc > 0) {
    // Initialisation des variables globales
    args_nb = argc;
    args = argv;
    
    // Ouverture du prochain fichier
    yyin = NULL;
    open_next_file();
    if (yyin == NULL) {
      return EXIT_SUCCESS;
    }
  } else {
    perror("Fichier non spécifié\n");
    exit(EXIT_FAILURE);
  }
  
  // Lancement de l'analyse lexicale
	yylex();
  
  // Affichage des résultats
  printf("\n");
}
