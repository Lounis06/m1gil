#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include "bloc.h"

// ---- Codes d'erreurs
#define SUCCESS 0
#define INVALID_ARGUMENT -1
#define UNEXPECTED_ERROR -2


// ---- Fonctions internes ----
/** <recursive>
 * Renvoie la longueur de la chaîne représentant le code d'un bloc
 */
static size_t length_bloc(bloc_t *element) {
    // Taille de l'élément vide
    if (element == NULL) {
        return 0;
    }
    
    // Taille des éléments fixes
    size_t size = 0;
    if (element->name != NULL) {
				size = 2*strlen(element->name) + 5; // longueur de <name></name>
		}
    if (element->text != NULL) {
        size += strlen(element->text);
    }
    
    // Taille des blocs imbriqués
    bloc_list_t *temp = element->list;
    while (temp != NULL) {
        size += length_bloc(temp->bloc);
        temp = temp->next;
    }
    
    // Renvoi de la taille obtenue
    return size;
}

/**
 * Ajoute le code du nom de bloc au buffer actuel
 */
static void addTag(bloc_t *bloc, bool close, char *buffer, size_t *index) {
    // Ouverture
    buffer[*index] = '<'; ++(*index);
    if (close) {
        buffer[*index] = '/'; ++(*index);
    }
    
    // Ecriture du nom
    size_t name_size = strlen(bloc->name);
    memcpy(buffer + *index, bloc->name, name_size); 
    (*index) += name_size;
    
    // Fermeture
    buffer[*index] = '>'; ++(*index);
}

/**
 * Désallou la structure de liste donnée
 */
void free_list(bloc_list_t **ptrl) {
  bloc_list_t *temp = NULL;
  while ((*ptrl) != NULL) {
    temp = *ptrl;
    ptrl = &((*ptrl)->next);
    free(temp);
  }
}

// ---- Fonctions du module ----
bloc_t *new_bloc(const char *name, const char *text) {
    // Initialisation de la structure
    bloc_t *temp = malloc(sizeof(*temp));
    
    // Ajout du nom s'il existe (partie optionnelle)
    temp->name = NULL;
    if (name != NULL) {
        size_t name_size = strlen(name);
        
        // Copie du nom de bloc
				if (name_size > 0) {
						temp->name = malloc(name_size + 1);
						memcpy(temp->name, name, name_size);
						temp->name[name_size] = '\0';
				}
    }
    
    // Ajout du texte s'il existe (partie optionnelle)
    temp->text = NULL;
    if (text != NULL) {
        size_t text_size = strlen(text);
        
        // Copie du texte existant
        if (text_size > 0) {
            temp->text = malloc(text_size + 1);
            memcpy(temp->text, text, text_size);
            temp->text[text_size] = '\0';
        }
    }
    
    // Initialisation de la liste
    temp->list = NULL;
    
    // Renvoi de la structure créee
    return temp;
}

bloc_t *empty_bloc() {
		return new_bloc(NULL, NULL);
}


int add_bloc(bloc_t *element, bloc_t *container) {
    // Contrôle de la validité des arguments
    if (container == NULL || element == NULL) {
        return INVALID_ARGUMENT;
    }
    
    // Parcours jusqu'en fin de liste
    bloc_list_t **ptrl = &(container->list);
    while (*ptrl != NULL) {
        ptrl = &((*ptrl)->next);
    }
    
    // Ajout en fin de liste
    *ptrl = malloc(sizeof(**ptrl));
    (*ptrl)->bloc = element;
    (*ptrl)->next = NULL;
    
    // Fin de fonction
    return SUCCESS;
}


char *consume_bloc(bloc_t **ptrb) {
    // Contrôle de la validité du bloc
    if (ptrb == NULL || *ptrb == NULL) {
        return NULL;
    }
    
    // Définition de la chaîne résultante
    size_t alloc_size = length_bloc(*ptrb) + 1;
    char *str = malloc(alloc_size);
    
    // Construction de la chaine
    size_t index = 0;
    
    // Ouverture de la balise de nom
    if ((*ptrb)->name != NULL) {
				addTag(*ptrb, false, str, &index);
		}
    
    // Ajout du code
    bloc_list_t **ptrl = &((*ptrb)->list);
    while (*ptrl != NULL) {
        // On consume l'élément pour en récupérer la chaîne
        char *s = consume_bloc(&((*ptrl)->bloc));
        size_t size = strlen(s);
        memcpy(str + index, s, size);
        index += size;
        free(s);
        
        // Passage à l'élément suivant
        ptrl = &((*ptrl)->next);
    }
    
    // Ajout du texte
    if ((*ptrb)->text != NULL) {
      size_t text_size = strlen((*ptrb)->text);
      memcpy(str + index, (*ptrb)->text, text_size);
      index += text_size;
    }
    
    // Fermeture de la balise de nom
    if ((*ptrb)->name != NULL) {
				addTag(*ptrb, true, str, &index);
		}
    
    // Destruction du bloc
    if ((*ptrb)->name != NULL) {
				free((*ptrb)->name);
		}
		if ((*ptrb)->text != NULL) {
				free((*ptrb)->text);
		}
    //free_list(&((*ptrb)->list));
    free(*ptrb);
    *ptrb = NULL;
    
    // Fin de saisie de la chaîne, ajout du caractère terminal
    str[index] = '\0'; index++;
    return str;
}
