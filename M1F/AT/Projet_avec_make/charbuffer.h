#ifndef CHARBUFFER_H
#define CHARBUFFER_H

#include <limits.h>
#include <stdlib.h>

/* ---- Constantes ---- */
#define FIRST_LETTER 'a'
#define ALPHABET_MAX_SIZE UCHAR_MAX + 1


/* ---- Structure ---- */
typedef struct charbuffer__s* charbuffer_t;

struct charbuffer__s {
  unsigned char *str;  // Le contenu du buffer
  size_t size;         // La quantité utilisable du buffer (str[0 .. size-1])
  size_t capacity;     // La capacité maximale de ce buffer (size <= capacity)
  size_t alphabet;     // La taille de l'alphabet utilisé
};


/* ---- Fonctions ---- */
/**
 * Construit un nouveau buffer de caractères, avec une capacité initiale 
 * spécifiée par capacity. Chaque caractère correspondra à une lettre d'un
 * certain alphabet, dont la taille est spécifiée par la valeur alphabet.
 * 
 * Renvoie le buffer en cas de succès, ou NULL en cas d'échec
 * lors de la construction
 */
charbuffer_t create_buffer(size_t capacity, size_t alphabet);


/**
 * Modifie les new_size premiers octets du buffer, avec des valeurs octales
 * aléatoires. Dans le cas, où new_size serait égale à la capacité 
 * du buffer, l'intégralité du contenu sera modifié.
 * Dans tous les cas, on doit avoir new_size de valeur inférieure ou égale,
 * à la capacité du buffer.
 * 
 * Renvoie 0 en cas de succès, -1 en cas d'erreur.
 */
int randomize_buffer(charbuffer_t buffer, size_t new_size);


/**
 * Libère les ressources utilisées par le buffer et met à NULL, la valeur
 * du pointeur fourni
 */
void free_buffer(charbuffer_t *ptr_buffer);

#endif
