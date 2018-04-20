
%{
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include "lex.yy.h"
#include "yystype.h"

void yyerror(const char *s);

%}

%token FLOAT
%token SQRT
%token COS
%token SIN
%token TAN

%left '-' '+'
%left '*' '/' '%'
%nonassoc SQRT COS SIN TAN
%right '^'
%right MOINSU

%%
ligne   : ligne expr '\n' {printf("resultat : %f \n", $2);}
	   | ligne '\n'
	   |
	   ;
expr    : expr '+' expr {$$ = $1 + $3;}
	   | expr '-' expr {$$ = $1 - $3;}
     | expr '*' expr {$$ = $1 * $3;}
     | expr '/' expr {$$ = $1 / $3;}
     | expr '%' expr {$$ = fmod($1, $3);}
     | expr '^' expr {$$ = pow($1, $3);}
     | '(' expr ')' {$$ = $2;}
     | SQRT expr {$$ = sqrt($2);}
     | COS expr {$$ = cos($2);}
     | SIN expr {$$ = sin($2);}
     | TAN expr {$$ = tan($2);}
     | '-' expr %prec MOINSU {$$ = -$2;}
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
