/**
 * Modélise une bloc MathML.
 * Le but est de pouvoir facilement créer une imbrication de blocs
 * pour structurer la construction et pouvoir créer plus facilement la sortie MathML
 */

#ifndef BLOC_H
#define BLOC_H

// ---- Types et Structures ----
/*
 * Crée une nouvelle liste de bloc
 */
typedef struct bloc_list__s {
	struct bloc__s *bloc;
	struct bloc_list__s *next;
} bloc_list_t;

/*
 * Crée la structure d'une bloc : Une bloc contient un nom,
 * un texte et une liste de sous-blocs qui lui appartiennent.
 *
 * Les attributs représentent :
 * 	> name : Le nom de la bloc : <name> ... </name>
 *  > text : Le contenu de fin de bloc : <name> ... text </name>
 *  > list : La liste des blocs imbriquées dont le contenu sera
 *  rajouté à cette bloc : <name> <bloc1> ... </bloc1> ... text </name>
 */
typedef struct bloc__s {
	char *name;
	char *text;
	struct bloc_list__s *list;
} bloc_t;


// ---- Fonctions ----
/* <malloc>
 * Initialise un nouveau bloc à partir d'un nom et d'un texte donné
 *
 * Renvoie un pointeur sur la structure crée en cas de succès, 
 * NULL en cas d'échec.
 */
extern bloc_t *new_bloc(const char *name, const char *text);


/* <malloc>
 * Initialise un nouveau bloc vide.
 * Le bloc vide permet de contenir des balises sans affichage d'informations
 * supplémentaires, elle permet de simuler des successions de balises.
 *
 * Renvoie un pointeur sur la structure crée en cas de succès, 
 * NULL en cas d'échec.
 */
extern bloc_t *empty_bloc();


/*
 * Ajoute une nouveau bloc dans la liste des sous-blocs associé
 * au bloc spécifié.
 *
 * Renvoie 0 en cas de succès, une valeur non nulle en cas d'échec.
 */
extern int add_bloc(bloc_t *element, bloc_t *container);


/* <malloc>, <free>, <recursive>
 * Consume l'arborescence établie par la structure de ce bloc pour en produire
 * le code correspondant. Le code sera contenu dans une chaîne allouée 
 * dynamiquement, qui devra être desallouée à la fin de son utilisation.
 *
 * Renvoie un pointeur sur la chaîne correspondant au code en cas de succès, 
 * NULL en cas d'échec.
 */
extern char *consume_bloc(bloc_t **ptrb);


#endif
