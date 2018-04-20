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

/**
 * Prépare le tableau des antécédents pour l'exécution de l'algorithme
 * d'Aho-Corasick.
 */
void prepareAC(Trie trie);

/**
 * Modifie la valeur de l'état donné en paramètre en effectuant le déplacement
 * par la lettre fournie, en appliquant l'algorithme d'Aho-Corasick.
 * Renvoie un entier indiquant si l'état actuel correspond à la
 * détection d'un mot du trie.
 */
int executeAC(Trie trie, int *etat, unsigned char lettre);
