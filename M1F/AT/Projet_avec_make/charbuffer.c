#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include "charbuffer.h"


/* ---- Fonctions ---- */
charbuffer_t create_buffer(size_t capacity, size_t alphabet) {
  // Cohérence de la taille de l'alphabet
  if (alphabet > ALPHABET_MAX_SIZE) {
    return NULL;
  }
  
  // Allocation de la structure du buffer
  charbuffer_t buffer = malloc(sizeof(*buffer));
  if (buffer == NULL) {
    return NULL;
  }
  
  // Allocation de l'espace de stockage du contenu
  buffer->str = malloc(capacity + 1);
  if (buffer->str == NULL) {
    return NULL;
  }
  
  // Initialisation de la structure
  buffer->str[capacity] = '\0';
  buffer->capacity = capacity;
  buffer->alphabet = alphabet;
  buffer->size = 0;
  
  // Renvoi de la structure allouée
  return buffer;
}


/**
 * Modifie les new_size premiers octets du buffer, avec des valeurs octales
 * aléatoires. Dans le cas, où new_size serait égale à la capacité 
 * du buffer, l'intégralité du contenu sera modifié.
 * Dans tous les cas, on doit avoir new_size de valeur inférieure ou égale,
 * à la capacité du buffer.
 * 
 * Renvoie 0 en cas de succès, -1 en cas d'erreur.
 */
int randomize_buffer(charbuffer_t buffer, size_t new_size) {
  // Vérification des arguments
  if (buffer == NULL || buffer->capacity < new_size) {
    return -1;
  }
  
  // Initialisation
  int temp = 0;
  size_t n = sizeof(temp);
  
  // Modification du buffer
  for (size_t k = 0; k <= new_size / n; ++k) {
    temp = rand();
    
    for (size_t j = 0; j < n && k + j < new_size; ++j) {
      // On ajoute le caractère correspondant, issu de l'entier aléatoire
      buffer->str[n * k + j] = ((char *) &temp)[j] % buffer->alphabet;
      
      // On ajoute la valeur de la première lettre de l'alphabet
      buffer->str[n * k + j] += FIRST_LETTER;
    }
  }
  
  // Modification des valeurs de la structure
  buffer->size = new_size;
  buffer->str[new_size] = '\0';
  
  // Modification avec succès : fin de fonction
  return 0;
}


/**
 * Libère les ressources utilisées par le buffer et met à NULL, la valeur
 * du pointeur fourni
 */
void free_buffer(charbuffer_t *ptr_buffer) {
  // Vérification de l'existance du buffer
  if (ptr_buffer == NULL || *ptr_buffer == NULL) {
    return;
  }
  
  // Supression de la structure
  if ((*ptr_buffer)->str != NULL) {
    free((*ptr_buffer)->str);
  }
  free(*ptr_buffer);
  *ptr_buffer = NULL;
}
