%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "yystype.h"
#include "calc.tab.h"
%}

NOMBRE       [0-9]*
SIGN         [+\-]?
INT          [1-9]{NOMBRE}|[0-9]
DECIMAL_INT  {NOMBRE}[1-9]
EXPOSANT     e{SIGN}{INT}
SEP          [,\.]
FLOAT        {INT}{EXPOSANT}?|{INT}{SEP}{DECIMAL_INT}{EXPOSANT}?|{SIGN}{SEP}{DECIMAL_INT}{EXPOSANT}?

SQRT         sqrt
COS          cos
SIN          sin
TAN          tan

%%
{FLOAT} {
  double l;
  char *end;
  printf("FLOAT : Analyse de %s=", yytext);
  l = strtod(yytext, &end);
  if ( *end != '\0' ) { // Erreur d'analyse syntaxique
    fprintf(stderr, "Erreur dans l'analyse de %s comme entier (%lf)\n", yytext,l);
    exit(EXIT_FAILURE);
  }
  if ((l==HUGE_VAL||l==-HUGE_VAL||l==0.) && errno==ERANGE ) { 
    // Depassement de capacité
    fprintf(stderr, "Dépassement de capacité dans l'analyse de %s "
	    "comme entier\n", yytext);
    exit(EXIT_FAILURE);
  }
  printf("%f\n", l);
  yylval = l;
  return FLOAT;
}

{SQRT} return SQRT;
{COS} return COS;
{SIN} return SIN;
{TAN} return TAN;
\+ return '+';
\- return '-';
\* return '*';
\/ return '/';
\% return '%';
\^ return '^';
\( return '(';
\) return ')';
\n return '\n';
. {}
%%

