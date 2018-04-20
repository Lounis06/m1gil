%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lex.yy.h"
#include "yystype.h"

// ---- Fonctions C ----
// | SQRT '[' litteral ']' expr {$$ = new_root($2, $4);} 
void yyerror(const char *s);
void print_exit(bloc_t *bloc);

// ---- Fonctions liées aux opérations de la grammaire
bloc_t *new_mfrac(bloc_t *num, bloc_t *denum);
bloc_t *new_row(bloc_t *content);
bloc_t *new_msup(bloc_t *value, bloc_t *exp);
bloc_t *new_msub(bloc_t *value, bloc_t *index);
bloc_t *new_sqrt(bloc_t *value);
bloc_t *new_root(bloc_t *index, bloc_t *value);
bloc_t *new_sum(bloc_t *min, bloc_t *max);
bloc_t *new_prod(bloc_t *min, bloc_t *max);

%}

%token VARIABLE
%token NUMBER
%token OPERATOR
%token PM
%token MP
%token CAP
%token CUP
%token SUBSET
%token SUPSET
%token SUBSETEQ
%token SUPSETEQ
%token FRAC
%token SQRT
%token ROOT
%token SUM
%token PROD

%right '^' '_' 
%left SQRT 
%left SUM
%left PROD
%left FRAC 
%left '{'

%%
expr : expr '^' expr {$$ = new_msup($1, $3); print_exit($$); }
    | expr '_' expr {$$ = new_msub($1, $3); print_exit($$);}
    | '{' expr '}' {$$ = new_row($2); print_exit($$);}
    | FRAC expr expr {$$ = new_mfrac($2, $3); print_exit($$);}
    | SQRT expr {$$ = new_sqrt($1); print_exit($$);}
    | SUM '_' expr '^' expr {$$ = new_sum($3, $5); print_exit($$);}
    | PROD '_' expr '^' expr {$$ = new_prod($3, $5); print_exit($$);}
    | terminal {print_exit($$);}
    ;
   
terminal : value
		| value operator terminal {$$ = empty_bloc(); add_bloc($1, $$); add_bloc($2, $$); add_bloc($3, $$); }
		;
   
value : NUMBER | VARIABLE

operator : OPERATOR
    | PM
    | MP
    | CAP
    | CUP
    | SUBSET
    | SUPSET
    | SUBSETEQ
    | SUPSETEQ
    ;
    

%%
// -- Définition des fonctions utilisées
void yyerror(const char *s) {
	fprintf(stderr, "line %d: %s\n", yylineno, s);
}

void print_exit(bloc_t *bloc) {
    bloc_t *temp = new_bloc("math", NULL); 
    add_bloc(bloc, temp);
    char *s = consume_bloc(&temp);
    printf("%s", s);
    free(s);
}

bloc_t *new_mfrac(bloc_t *num, bloc_t *denum) {
    bloc_t *frac = new_bloc("mfrac", NULL);
    add_bloc(num, frac);
    add_bloc(denum, frac);
    
    return frac;
}

bloc_t *new_row(bloc_t *content) {
		bloc_t *row = new_bloc("mrow", NULL);
		
		add_bloc(content, row);
		return row;
}

bloc_t *new_msup(bloc_t *value, bloc_t *exp) {
    bloc_t *sup = new_bloc("msup", NULL);
    add_bloc(value, sup);
    add_bloc(exp, sup);
    
    return sup;
}

bloc_t *new_msub(bloc_t *value, bloc_t *index) {
    bloc_t *sub = new_bloc("msub", NULL);
    add_bloc(value, sub);
    add_bloc(index, sub);
    
    return sub;
}

bloc_t *new_sqrt(bloc_t *value) {
    bloc_t *sqrt = new_bloc("sqrt", NULL);
    add_bloc(value, sqrt);
    
    return sqrt;
}

bloc_t *new_root(bloc_t *index, bloc_t *value) {
    bloc_t *root = new_bloc("root", NULL);
    add_bloc(index, root);
    add_bloc(value, root);
    
    return root;
}

bloc_t *new_sum(bloc_t *min, bloc_t *max) {
    bloc_t *sum = new_bloc("munderover", NULL);
    bloc_t *symb = new_bloc("mo", "&#x2211;");
    
    add_bloc(symb, sum);
    add_bloc(min, sum);
    add_bloc(max, sum);
    
    return sum;
}

bloc_t *new_prod(bloc_t *min, bloc_t *max) {
    bloc_t *prod = new_bloc("munderover", NULL);
    bloc_t *symb = new_bloc("mo", "&#x220F;");
    
    add_bloc(symb, prod);
    add_bloc(min, prod);
    add_bloc(max, prod);
    
    return prod;
}


// -- MAIN
int main(int argc, char **argv) {
  // Contrôle des arguments entrés
	if (argc != 2) {
		perror("usage: ./l2m \"formule\"");
		return EXIT_FAILURE;
	}
    
    // Récupération de l'argument lu en ligne de commande
	yy_scan_string(argv[1]);
    
    // Lancement
	yyparse();
    
	// Récupération et affichage du code MathML produit
	//print_exit(yylval);
    
    // Fin de programme
	return EXIT_SUCCESS;
}
