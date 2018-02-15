package tp03.huffman

/**
 * Un objet permettant de coder et décoder des messages grâce à la méthode de
 *  Huffman.
 */
object Huffman {
  /**
   * Pour des raisons de simplicité (il s'agit d'un TP sur Scala, pas sur la
   *  compression), un bit sera représenté par un entier.
   * Ce n'est évidemment pas imaginable dans un contexte de compression réelle.
   */
  type Bit = Int

  /**
   * Un code de Huffman est un arbre binaire. 
   * Chaque feuille est associée à un caractère alphabétique et à son poids
   *  (généralement proportionnel à sa fréquence d'apparition dans le
   *  langage dans lequel sont écrit les messages).
   * Un noeud interne mémorise les caractères associés aux feuilles du
   *  sous-arbre dont il est la racine (dans l'ordre induit par un parcours en
   *  profondeur à main gauche préfixe de l'arbre) et la somme de leurs poids.
   */
  abstract class HuffmanTree
  case class Leaf(char: Char, weight: Int) extends HuffmanTree
  case class Node(left: HuffmanTree, right: HuffmanTree,
      chars: List[Char], weight: Int) extends HuffmanTree
  
  /**
   * Le poids d'un arbre est celui de sa racine.
   */
  def weight(tree: HuffmanTree): Int = tree match {
    case Leaf(_,w) => w
    case Node(_,_,_,w) => w
  }
  
  /**
   * La liste des caractères associés à la racine de "tree".
   */
  def chars(tree: HuffmanTree): List[Char] = tree match {
    case Leaf(c, _) => List(c)
    case Node(_,_,list,_) => list
  }
  
  /**
   * Renvoie un nouveau code de Huffman en faisant de "left" son sous-arbre
   *  gauche et de "right" son sous-arbre droit.
   */
  def buildTree(left: HuffmanTree, right: HuffmanTree): HuffmanTree = (left, right) match {
    case (Leaf(cl, wl), Leaf(cr, wr)) => Node(left, right, List(cl, cr), wl + wr)
    case (Leaf(cl, wl), Node(_, _, cr, wr)) => Node(left, right, cl::cr, wl + wr)
    case (Node(_, _, cl, wl), Leaf(cr, wr)) => Node(left, right, cr::cl, wl + wr)
    case (Node(_, _, cl, wl), Node(_, _, cr, wr)) => Node(left, right, cl ::: cr, wl + wr)
  }

  /**
   * Renvoie une liste de feuilles (codes de Huffman de taille 1), obtenue en
   *  calculant le nombre d'occurrences des caractères présents dans "chars".
   * Par exemple, si chars = (List('a', 'b', 'a')) le résultat sera :
   *  List(Leaf('a', 2), Leaf('b', 1))
   */
  def buildLeaves(chars: List[Char]): List[Leaf] = {
    // Récupération d'une map contenant les caractères et leur nombre d'occurrences
    val map = chars.groupBy(x => x).mapValues(x => x.length)
    // Transformation en liste sur laquelle on applique l'instanciation en Leaf
    map.toList.map{case (c, n) => Leaf(c, n)}
  }

  /**
   * On suppose que "trees" est ordonnée par poids croissants.
   * La fonction "insert" renvoie une liste équivalente à "trees"
   *  après y avoir inséré "t".
   * Cette fonction est pratique lorsqu'on réalise un tri (par
   *  insertion) d'une liste d'arbres.
   */
  def insert[T<:HuffmanTree](t: T, trees: List[T]): List[T] = {
    def auxInsert(t: T, trees: List[T], acc: List[T]): List[T] = trees match {
      case Nil => t::acc
      case a::ll => if (weight(t) > weight(a)) auxInsert(t, trees.tail, a::acc) else acc.reverse ::: t::a::ll
    }
    auxInsert(t, trees, List())
  }

  /**
   * Renvoie la liste "leaves" triée (par insertion) par poids croissants.
   */
  def orderLeaves(leaves: List[Leaf]): List[Leaf] = ???
  
  /**
   * Renvoie un code de Huffman à partir de "leaves".
   */
  def buildHuffmanTree(leaves: List[Leaf]): HuffmanTree = ???
  
  /**
   * Décode la liste de bits "bits" avec le code "tree".
   */
  def decode(tree: HuffmanTree, bits: List[Bit]): List[Char] = ???
  
  /**
   * Renvoie la liste de bits obtenue par encodage du texte "text" avec
   *  le code de Huffman "tree".
   */
  def encode(tree: HuffmanTree, text: List[Char]): List[Bit] = ???
  
  /**
   * Un outil plus efficace que l'arbre lui-même pour encoder un message :
   *  une table de codage qui associe son code à chaque caractère. 
   */
  type CodeTable = Map[Char, List[Bit]]

  /**
   * Convertit l'arbre en une table de codage équivalente.
   */
  def convert(tree: HuffmanTree): CodeTable = ???
  
  /**
   * Renvoie la liste de bits obtenue par encodage du texte "text" avec
   *  le code de Huffman "tree" (mais en utilisant en interne la table de
   *  codage correspondante).
   */
  def fastEncode(tree: HuffmanTree, text: List[Char]): List[Bit] = ???
}
