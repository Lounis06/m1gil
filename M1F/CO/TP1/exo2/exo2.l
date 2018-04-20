%%
[A-Z][a-z]*  printf("IDENT : %s\n", yytext);
\'.*\'    printf("CHAINE : %s\n", yytext);
:=            printf("AFFECTATION\n");
\;            printf("SEPARATION\n");
.             /* ignorer le reste */
%%
