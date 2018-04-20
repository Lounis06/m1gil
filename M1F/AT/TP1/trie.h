// ---- Structures ----
typedef struct _trie *Trie;

// ---- Prototypes ----
/**
 * Crée un nouveau trie de capacité maxNode (en termes de nombre
 * de noeuds).
 * 
 * Renvoie NULL en cas d'erreur
 */
Trie createTrie(int maxNode);

/**
 * Insère le mot w, dans le trie correspondant
 */
void insertInTrie(Trie trie, unsigned char *w);

/**
 * Indique si le mot w, est contenu à l'intérieur du trie donné.
 */
int isInTrie(Trie trie, unsigned char *w);
