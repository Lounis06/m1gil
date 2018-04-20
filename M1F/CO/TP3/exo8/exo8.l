%{
  #include <stdio.h>
  
  enum {
    COM_TOTAL_LG = 0, STR_COUNT = 1, STR_TOTAL_LG = 2, NB_OPTIONS = 3
  };
  
  int tab[NB_OPTIONS];
%}

COM_INS    ("*"*[^*/]"/"*)*
COM_BEGIN  "/*"
COM_END    "*/"

STR_FLAG  "\""
STR_QUOTE "\\\""
STR_CHAR  [^\"]

%%
{COM_BEGIN}"/"*{COM_INS}*"*"*{COM_END}  tab[COM_TOTAL_LG] += yyleng;
"//".*\n                        tab[COM_TOTAL_LG] += yyleng - 1;

{STR_FLAG}{STR_CHAR}*{STR_FLAG} tab[STR_COUNT]++; tab[STR_TOTAL_LG] += yyleng - 2; ECHO;

%%

// Commande de lancement : ./exo8 "nom_de_fichier"?
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
  printf("\n ---- Données ---- \n");
  printf("Longueur des commentaires supprimés : %d caractères\n", tab[COM_TOTAL_LG]);
  printf("Nombre de chaînes de caractères : %d\n", tab[STR_COUNT]);
  printf("Longueur totale des chaînes : %d caractères\n", tab[STR_TOTAL_LG]);
}

