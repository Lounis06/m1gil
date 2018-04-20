#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "charbuffer.h"

/* ---- Constantes ---- */
/**
 * La capacité du buffer de recopie, par défaut
 */
#define BUFFER_DEFAULT_CAPACITY 2048

/**
 * Définit le type d'argument à traiter (pour les éventuelles vérifications)
 */
enum {
  TEXT_SIZE, ALPHABET_SIZE
};


/* ---- Fonctions ---- */
/**
 * Calcule le minimum de 2 valeurs entières
 */
static inline int min(int a, int b) { return a < b ? a : b; }

/**
 * Extrait la valeur de l'argument spécifié, puis vérifie sa cohérence
 */
size_t extract_arg(char *arg, int type) {
  // Vérification de l'argument
  if (arg == NULL) {
    perror("Chaîne entrée inexistante\n");
    exit(EXIT_FAILURE);
  }
  
  // Extraction de la valeur
  int temp = atoi(arg);
  
  // Vérifications
  switch (type) {
    case TEXT_SIZE :
      if (temp <= 0) {
        perror("Taille de texte invalide (Indication : taille > 0)\n");
        exit(EXIT_FAILURE);
      }
      break;
    case ALPHABET_SIZE :
      if (temp <= 0 || ALPHABET_MAX_SIZE < temp) {
        perror("Taille d'alphabet invalide (Indication : 0 < taille <= 256)\n");
        exit(EXIT_FAILURE);
      }
      break;
  }
  
  // Fin de la vérification, retour de la valeur :
  return (size_t) temp;
}


/* ---- MAIN ---- */
/**
 * Arguments d'entrée :
 *     argv[1] -> Longueur du texte
 *     argv[2] -> Taille de l'alphabet
 */
int main(int argc, char **argv) {
  // Vérification de la cohérence des arguments
  if (argc != 3) {
    perror("Nombre d'arguments incohérent\n");
    exit(EXIT_FAILURE);
  }
  
  // Initialisation de rand
  srand(time(NULL));
  
  // Récupération des valeurs
  size_t text_size = extract_arg(argv[1], TEXT_SIZE);
  size_t alphabet_size = extract_arg(argv[2], ALPHABET_SIZE);
  
  // Création du buffer de recopie
  size_t capacity = min(BUFFER_DEFAULT_CAPACITY, text_size);
  charbuffer_t buffer = create_buffer(capacity, alphabet_size);
  
  // Ecriture du texte
  for (size_t k = 0; k < text_size; k += buffer->capacity) {
    if (randomize_buffer(buffer, min(text_size - k,  buffer->capacity)) == -1) {
      perror("Erreur lors de la génération du texte\n");
      exit(EXIT_FAILURE);
    }
    
    printf("%s", buffer->str);
  }
  
  // Suppression du buffer
  free_buffer(&buffer);
  
  // Fin de fonction
  return EXIT_SUCCESS;
}

