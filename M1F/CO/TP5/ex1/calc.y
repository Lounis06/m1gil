
%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lex.yy.h"
#include "yystype.h"

void yyerror(const char *s);

%}

%token FLOAT

%%
ligne   : ligne expr '\n' {printf("resultat : %f \n", $2);}
	   | ligne '\n'
	   |
	   ;
expr    : expr '+' terme {$$ = $1 + $3;}
	   | expr '-' terme {$$ = $1 - $3;}
	   | terme
	   ;
terme   : terme '*' facteur {$$ = $1 * $3;}
	   | terme '/' facteur {$$ = $1 / $3;}
	   | facteur
	   ;
facteur : '-' facteur {$$ = -$2;}
	   | '(' expr ')' {$$ = $2;}
	   | FLOAT
	   ;
%%
void yyerror(const char *s) {
 fprintf(stderr,"%s\n", s);
}
int main(void) {
 yyparse();
 return EXIT_SUCCESS;
}
