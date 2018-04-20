#include <stdlib.h>
#include "algorithm.h"

int main() {
  // Initialisations
  unsigned char *text = (unsigned char *) "abcaeixbcahcilcayghaigbacbiaucaegi";
  unsigned char *word = (unsigned char *) "cae";
  
  // Tests
  // ---- Variantes de l'algorithme naïf ----
  search_naive(text, word);
  search_naive_ql(text, word);
  search_naive_ql_sn(text, word);
  search_naive_ncmp(text, word);
  search_naive_ql_ncmp(text, word);
  search_naive_ql_sn_ncmp(text, word);
  
  // ---- Algorithmes de MP et KMP ----
  search_mp(text, word);
  search_kmp(text, word);
  
  // ---- Algorithme de Boyer-Moore ----
  search_bm(text, word);
  
  return EXIT_SUCCESS;
}
