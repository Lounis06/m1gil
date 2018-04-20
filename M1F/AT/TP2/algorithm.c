#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include "algorithm.h"

// ---- Enumération et constantes ----
/**
 * Définit le comportement de la fonction d'affichage
 *    > INIT_ERROR, VOIDWORD_ERROR, END : sans paramètres, affiche leur messages
 *    spécifiques
 *    > ERROR : Affiche un message d'erreur spécifique. (char *)
 *    > START : Affiche le nom de l'algo. utilisé fourni en paramètre. (char *)
 *    > INDEX : Affiche l'indice de l'occurence, fourni en paramètre. (size_t)
 */
enum {
  INIT_ERROR, VOIDWORD_ERROR, ERROR, START, INDEX, END
};

/** Renvoie le nombre actuel d'occurences lors de la recherche en cours */
size_t occurences = 0;

// ---- Fonctions statiques ----
/* General */
static inline int max(int a, int b) { return (a < b) ? b : a; }

/**
 * Permet de généraliser l'affichage des différents algorithmes disponibles.
 */
void print(int mode, void *arg) {
  switch (mode) {
    case INIT_ERROR :
      perror("Erreur : Données non trouvées\n");
      break;
    case VOIDWORD_ERROR :
      perror("Erreur : Le mot à rechercher est vide\n");
      break;
    case ERROR :
      fprintf(stderr, "Erreur : %s\n", (char *) arg);
      break;
    case START :
      printf("---- %s ----\n", (char *) arg);
      occurences = 0;
      break;
    case INDEX :
      if (occurences == 0) {
        printf("Indices : ");
      } else {
        printf(", ");
      }
      printf("%lu", *((size_t *) arg));
      ++occurences;
      break;
    case END :
      printf("\nFin de l'algorithme (Total : %lu)\n", occurences);
      occurences = 0;
      break;
  }
}

/* MP/KMP */
/**
 * Calcule le bord sur le mot constitué des wordlen premiers caractères
 * du mot word.
 * 
 * Renvoie NULL, si le bord est le mot vide, un pointeur sur
 * le bord sinon (A désallouer ultérieurement)
 */
unsigned char *bord(const unsigned char *word, size_t wordlen) {
  unsigned char *temp = NULL;
  
  for (size_t n = wordlen - 1; n > 0 && temp == NULL; --n) {
    if (strncmp((const char *) word, (const char *) &word[wordlen - n], n) == 0) {
      temp = malloc(n + 1);
      memcpy(temp, word, n);
      temp[n] = '\0';
    }
  }
  
  return temp;
}


/**
 * Calcule le tableau bon_pref (selon l'algo de MP) associé au mot word,
 * de longueur wordlen.
 */
static int *bon_pref(const unsigned char *word, size_t wordlen) {
  // Initialisation
  int *tab = calloc(wordlen + 1, sizeof(*tab));
  tab[0] = -1;
  
  // Calcul des valeurs
  for (size_t k = 1; k <= wordlen; ++k){
    unsigned char *temp = bord(word, k);
    
    // On affecte la longueur du bord trouvé
    if (temp == NULL) {
      tab[k] = 0;
    } else {
      tab[k] = strlen((const char *)temp);
    }
    
    free(temp);
  }
  
  return tab;
}

/**
 * Calcule le tableau meil_pref (selon l'algo de KMP) associé au mot word,
 * de longueur wordlen.
 */
static int *meil_pref(const unsigned char *word, size_t wordlen) {
  // Initialisation
  int *tab = calloc(wordlen + 1, sizeof(*tab));
  tab[0] = -1;
  
  // Calcul des valeurs
  for (size_t k = 1; k <= wordlen; ++k){
    unsigned char *temp = bord(word, k);
    
    // On calcule la longueur du bord trouvé
    int n = temp == NULL ? 0 : strlen((const char *)temp);
    free(temp);
    
    // On met à jour le tableau meil-préf
    if (k == wordlen || word[n] != word[k]) {
      tab[k] = n;
    } else {
      tab[k] = tab[n];
    }
  }
  
  return tab;
}

/* Boyer-Moore */
/**
 * Indique si Cs(i, d) est vérifié pour le mot word de longueur m.
 * (Condition propre à l'algorithme de Boyer-Moore)
 */
bool condition_cs(const unsigned char *word, size_t m, int i, int d) {
  if (d <= 0) {
    return false;
  }
  if (d <= i+1) {
    return strncmp((const char *) &word[i - d + 1], (const char *) &word[i + 1], m - i + 1) == 0;
  }
  
  return strncmp((const char *) word, (const char *) &word[d], m - d) == 0;
}

/**
 * Indique si Co(i, d) est vérifié pour le mot word de longueur wordlen.
 */
bool condition_co(const unsigned char *word, int i, int d) {
  if (i < d) {
    return true;
  } else if (d <= 0) {
    return false;
  }
  
  return word[i-d] != word[i];
}

/**
 * Calcule le tableau bon_suff (selon l'algo de Boyer-Moore) associé au mot word,
 * de longueur wordlen.
 */
static int *bon_suff(const unsigned char *word, size_t wordlen) {
  // Initialisation
  int *tab = calloc(wordlen, sizeof(*tab));
  
  // Calcul des valeurs
  for (size_t k = 0; k < wordlen; ++k) {
    int d = 1;
    while ((bool) d < wordlen 
        && (!condition_co(word, k, d) || !condition_cs(word, wordlen, k, d))) {
      ++d;
    }
    
    tab[k] = d;
  }
  
  return tab;
}

/**
 * Calcule la table dern_occ (selon l'algo de Boyer-Moore) associé au mot word,
 * de longueur wordlen.
 */
static int *dern_occ(const unsigned char *word, size_t wordlen) {
  // Initialisation
  int *tab = calloc(wordlen, sizeof(*tab));
  
  // Calcul des valeurs
  for (size_t k = 0; k < wordlen; ++k) {
    int d = 1;
    while ((bool) d < wordlen 
        && (!condition_co(word, k, d) || !condition_cs(word, wordlen, k, d))) {
      ++d;
    }
    
    tab[k] = d;
  }
  
  return tab;
}


// ---- Fonctions "chaînes" ----
void search_naive(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme naïf");
  for (size_t j = 0; j <= textlen - wordlen; ++j) {
    // On teste si la première lettre correspond
    if (text[j] == word[0]) {
      // Dans ce cas, on teste la présence du mot
      size_t k = 1;
      while (k < wordlen && text[j + k] == word[k]) {
        ++k;
      }
      // On affiche ensuite l'indice de l'occurence, si elle existe.
      if (k == wordlen) {
        print(INDEX, &j);
      }
    }
  }
  
  print(END, NULL);
}


void search_naive_ql(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme naïf avec boucle rapide");
  for (unsigned char *p = (unsigned char *) text; *p != '\0'; ++p) {
    // On teste si la première lettre correspond
    if (*p == word[0]) {
      // Dans ce cas, on teste la présence du mot
      size_t k = 1;
      while (k < wordlen && p[k] == word[k]) {
        ++k;
      }
      // On affiche ensuite l'indice de l'occurence, si elle existe.
      if (k == wordlen) {
        size_t index = p - text;
        print(INDEX, &index);
      }
    }
  }
  
  print(END, NULL);
}


void search_naive_ql_sn(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Ajout de la sentinelle
  unsigned char *sn_text = malloc(textlen + wordlen + 1);
  memcpy(sn_text, text, textlen);
  memcpy(&sn_text[textlen], word, wordlen);
  sn_text[textlen + wordlen] = '\0'; 
  
  // Déroulement de l'algo.
  unsigned char *p = sn_text;
  print(START, "Algorithme naïf avec boucle rapide et sentinelle");
  bool stop = false;
  while (!stop) {
    // On teste si la lettre lue correspond à la première lettre du mot
    if (*p == word[0]) {
      // Dans ce cas, on teste la présence du mot
      size_t k = 1;
      while (k < wordlen && p[k] == word[k]) {
        ++k;
      }
      // On affiche ensuite l'indice de l'occurence, si elle existe.
      if (p[k] == '\0') {
        stop = true;
      } else if (k == wordlen) {
        size_t index = p - sn_text;
        print(INDEX, &index);
      }
    }
    
    // Lecture de la lettre suivante
    ++p;
  }
  
  free(sn_text);
  print(END, NULL);
}


void search_naive_ncmp(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme naïf avec strcmp");
  for (size_t j = 0; j <= textlen - wordlen; ++j) {
    // On teste si la première lettre correspond
    if (text[j] == word[0]) {
      // Dans ce cas, on teste la présence du mot
      if (strncmp((const char *) &text[j + 1], 
                  (const char *) &word[1], wordlen - 1) == 0) {
        print(INDEX, &j);
      }
    }
  }
  
  print(END, NULL);
}


void search_naive_ql_ncmp(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme naïf avec boucle rapide et strncmp");
  for (unsigned char *p = (unsigned char *) text; *p != '\0'; ++p) {
    // On teste si la première lettre correspond
    if (*p == word[0]) {
      // On affiche l'indice de l'occurence, si elle existe.
      if (strncmp((const char *) p + 1, 
                  (const char *) &word[1], wordlen - 1) == 0) {
        size_t index = p - text;
        print(INDEX, &index);
      }
    }
  }
  
  print(END, NULL);
}


void search_naive_ql_sn_ncmp(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Ajout de la sentinelle
  unsigned char *sn_text = malloc(textlen + wordlen + 1);
  memcpy(sn_text, text, textlen);
  memcpy(&sn_text[textlen], word, wordlen);
  sn_text[textlen + wordlen] = '\0'; 
  
  // Déroulement de l'algo.
  unsigned char *p = sn_text;
  print(START, "Algorithme naïf avec boucle rapide et sentinelle");
  bool stop = false;
  while (!stop) {
    // On affiche ensuite l'indice de l'occurence, si elle existe.
    if (strncmp((const char*) p, (const char*) word, wordlen) == 0) {
      if (p[wordlen] == '\0') {
        stop = true;
      } else {
        size_t index = p - sn_text;
        print(INDEX, &index);
      }
    }
  
    // Lecture de la lettre suivante
    ++p;
  }
  
  free(sn_text);
  print(END, NULL);
}


void search_mp(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme de Morris et Pratt");
  int *tab = bon_pref(word, wordlen);
  int k = 0;
  
  
  for (size_t j = 0; j < textlen; ++j) {
    while (k >= 0 && word[k] != text[j]) {
      k = tab[k];
    }
    ++k;
    
    if (k == (int) wordlen) {
      size_t index = j - wordlen + 1;
      print(INDEX, &index);
      k = tab[k];
    }
  }
  
  // Fin de fonction
  free(tab);
  print(END, NULL);
}


void search_kmp(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme de Knuth-Morris-Pratt");
  int *tab = meil_pref(word, wordlen);
  int k = 0;
  
  
  for (size_t j = 0; j < textlen; ++j) {
    while (k >= 0 && word[k] != text[j]) {
      k = tab[k];
    }
    ++k;
    
    // Si une occurence est trouvée, on affiche l'indice associé
    if (k == (int) wordlen) {
      size_t index = j - wordlen + 1;
      print(INDEX, &index);
      k = tab[k];
    }
  }
  
  // Fin de fonction
  free(tab);
  print(END, NULL);
}


void search_bm(const unsigned char *text, const unsigned char *word) {
  // Cohérence des arguments
  if (text == NULL || word == NULL) {
    print(INIT_ERROR, NULL);
    return;
  }
  
  // Obtention de la longueur du mot word
  size_t textlen = strlen((const char *) text);
  size_t wordlen = strlen((const char *) word);
  if (wordlen == 0) {
    print(VOIDWORD_ERROR, NULL);
    return;
  }
  
  // Déroulement de l'algo.
  print(START, "Algorithme de Boyer-Moore");
  int *tab = bon_suff(word, wordlen);
  int *tab_occ = dern_occ();
  size_t j = wordlen - 1;
  int i = 0;
  
  while (j < textlen) {
    i = wordlen - 1;
    while (i >= 0 && word[i] == text[j - wordlen + i + 1]) {
      --i;
    }
    
    // Si une occurence est trouvée, on affiche l'indice associé
    if (i < 0) {
      size_t index = j;
      print(INDEX, &index);
      j += tab[0];
    } else {
      j += max(tab[i], tab_occ[text[j - wordlen + i + 1]] - wordlen + 1 + i);
    }
  }
  
  // Fin de fonction
  free(tab);
  free(tab_occ);
  print(END, NULL);
}
