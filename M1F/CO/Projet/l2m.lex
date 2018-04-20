%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bloc.h"
#include "yystype.h"
#include "l2m.tab.h"
%}

VARIABLE    [a-zA-Z]
NUMBER      [0-9]+
OPERATOR		[+=%\-\/\*]
PM          \\pm
MP          \\mp
CAP         \\cap
CUP         \\cup
SUBSET      \\subset
SUPSET      \\supset
SUBSETEQ    \\subseteq
SUPSETEQ    \\supseteq
FRAC        \\frac
SQRT				\\sqrt
SUM         \\sum
PROD        \\prod

%%

{VARIABLE} { 
    yylval = new_bloc("mi", yytext);
    return VARIABLE;
}

{NUMBER} { 
		yylval = new_bloc("mn", yytext);
    return NUMBER;
}

{OPERATOR} {
		yylval = new_bloc("mo", yytext);
		return OPERATOR;
}

{PM} { 
	yylval = new_bloc("mo", "&plusmn;");
    return PM;
}

{MP} { 
    yylval = new_bloc("mo", "&#x2213;");
    return MP;
}

{CAP} { 
		yylval = new_bloc("mo", "&cap;");
    return CAP;
}

{CUP} { 
    yylval = new_bloc("mo", "&cup;");
    return CUP;
}

{SUBSET} { 
    yylval = new_bloc("mo", "&sub;");
    return SUBSET;
}

{SUPSET} { 
    yylval = new_bloc("mo", "&sup;");
    return SUPSET;
}

{SUBSETEQ} { 
    yylval = new_bloc("mo", "&sube;");
    return SUBSETEQ;
}

{SUPSETEQ} { 
    yylval = new_bloc("mo", "&supe;");
    return SUPSETEQ;
}

{FRAC} return FRAC;

\^ return '^';

\_ return '_';

\{ return '{';

\} return '}';

{SQRT} return SQRT;

{SUM} return SUM;

{PROD} return PROD;

\n return '\n';

. {}

%%
