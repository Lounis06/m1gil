package tp02.anagrams

/**
 * Un objet fournissant des outils pour construire des anagrammes de mots
 *  et de phrases.
 */
object Anagrams {
  /**
   * Un mot est une String.
   * Pour simplifier, on supposera qu'un mot ne contient que des caractères
   *  alphabétiques ou des tirets.
   * Il n'y aura aucun caractère accentué ou porteur d'un tréma ou d'une cédille.
   */
  type Word = String
  type Sentence = List[Word]

  /** 
   * Une "Occurrences" est une liste ordonnée de couples (Char, Int) (selon
   *  l'ordre alphabétique, un caractère apparaissant au plus une fois dans la
   *  liste).
   * Elle permet d'associer à un mot ou une phrase, la liste de ses
   *  caractères avec leur fréquence dans le mot ou dans la phrase.

   * Remarque : si la fréquence d'un caractère est nulle alors il n'apparait
   *  pas dans la liste.
   */
  type Occurrences = List[(Char, Int)]

  val dictionary: List[Word] = {
    val resourceFile = new java.io.File("src/tp02/resources/dico.txt")
    val source = io.Source.fromFile(resourceFile)
    source.getLines.toList
  }

  /** 
   * Convertit un mot en la liste des fréquences de ses caractères.
   * Les éventuelles majuscules seront assimilées au caractère minuscule
   *  correspondant.
   */
  def wordOccurrences(w: Word): Occurrences = w.toLowerCase.groupBy(x => x).mapValues(x => x.length).toList.sorted

  /** 
   * Convertit une phrase en la liste des fréquences de ses caractères
   */
  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.mkString(""))

  /** 
   * Une association qui fait correspondre à une liste de
   *  fréquences de caractères, les mots du dictionnaires
   *  compatibles avec cette liste.
   * Par exemple, les occurrences de caractères du mot "tri" sont :
   *  List(('i', 1), ('r', 1), ('t', 1))
   * Ce sont les mêmes occurrences pour les mots "tir" et "rit".
   * On aura donc l'entrée suivante dans l'association
   *  "dictionaryByOccurrences" :
   *  List(('i', 1), ('r', 1), ('t', 1)) -> List("rit", "tir", "tri")
   *
   */
  val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary.groupBy(x => wordOccurrences(x))

  /**
   * Renvoie l'anagramme de "word"
   */
  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences.getOrElse(wordOccurrences(word), List())

  /**
   * Retourne la liste de tous les sous-ensembles d'une liste de fréquences.
   * Par exemple: les sous-ensembles de la liste List(('a', 2), ('b', 2)) sont :
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    def auxCombinations(occurrences: Occurrences, acc: List[Occurrences]): List[Occurrences] =
      occurrences match {
      case _ => List()
      }
    auxCombinations(occurrences, List())
  }
  
  /**
   * Renvoie la liste de fréquences obtenue en retirant "y" à "x".
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    def auxSubtract(x: Occurrences, y: Occurrences, acc: Occurrences): Occurrences =
      (x, y) match {
        case ((u, n)::xx, (v, m) :: yy) if u < v => auxSubtract(x.tail, y, acc++:List((u, n)))
        case ((u, n)::xx, (v, m) :: yy) if u == v =>
          if (n - m == 0)
            auxSubtract(x.tail, y.tail, acc)
          else
            auxSubtract(x.tail, y.tail, acc++:List((u, n - m)))
        case (x, Nil) => acc ::: x
        case _ => acc
      }
    auxSubtract(x, y, List())
  }
    
  
  /**
   * Renvoie la liste de toutes les phrases anagrammes de "sentence".
   * Par exemple, pour le paramètre List("a", "plus"), la méthode renvoie :
   *    List(
   *      List("su", "pal")
   *      List("us", "pal")
   *      List("pu", "las")
   *      List("lu", "pas")
   *      List("plus", "a")
   *      List("a", "plus")
   *      List("pas", "lu")
   *      List("las", "pu")
   *      List("pal", "su")
   *      List("pal", "us")
   *    )
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???
}