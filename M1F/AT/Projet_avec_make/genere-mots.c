#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "charbuffer.h"

/* ---- Constantes ---- */
/**
 * Définit le type d'argument à traiter (pour les éventuelles vérifications)
 */
enum {
  WORDS_NB = 0, 
  LENGTH_MIN = 1, 
  LENGTH_MAX = 2, 
  ALPHABET_SIZE = 3, 
  ARGS_NB = 4
};

/**
 * Le tableau stockant les valeurs des différents arguments
 */
size_t args[ARGS_NB];


/* ---- Fonctions ---- */
/**
 * Renvoie un nombre aléatoire compris entre a et b
 */
static inline size_t rand_int(size_t a, size_t b) { 
  return a + (rand() % (b - a + 1)); 
}

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
    case WORDS_NB :
      if (temp <= 0) {
        perror("Nombre de mots invalide (Indication : nombre > 0)\n");
        exit(EXIT_FAILURE);
      }
      break;
    case LENGTH_MIN :
      if (temp <= 0) {
        perror("Taille de mots min. invalide (Indication : min > 0)\n");
        exit(EXIT_FAILURE);
      }
      break;
    case LENGTH_MAX :
      if (temp <= 0 || temp < (int) args[LENGTH_MIN]) {
        perror("Taille de mots max. invalide (Indication : 0 < min <= max)\n");
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
  if (argc != ARGS_NB + 1) {
    perror("Nombre d'arguments incohérent\n");
    exit(EXIT_FAILURE);
  }
  
  // Initialisation de rand
  srand(time(NULL));
  
  // Récupération des valeurs
  for (int k = 0; k < ARGS_NB; ++k) {
    args[k] = extract_arg(argv[k + 1], k);
  }
  
  // Création du buffer de recopie
  charbuffer_t buffer = create_buffer(args[LENGTH_MAX], args[ALPHABET_SIZE]);
  
  // Génération des mots
  for (size_t k = 0; k < args[WORDS_NB]; ++k) {
    if (randomize_buffer(buffer, 
                         rand_int(args[LENGTH_MIN], args[LENGTH_MAX])) == -1) {
      perror("Erreur lors de la génération du texte\n");
      exit(EXIT_FAILURE);
    }
    
    printf("%s\n", buffer->str);
  }
  
  // Suppression du buffer
  free_buffer(&buffer);
  
  // Fin de fonction
  return EXIT_SUCCESS;
}
