package tp06

object Util {
  /** t est-il une valeur ? */
  def isVal(t : Term) : Boolean = t match {
    case True => true
    case False => true
    case x if isNumVal(x) => true
    case _ => false
  }

  /** t est-il une valeur numérique ? */
  def isNumVal(t : Term) : Boolean = t match {
    case Zero => true
    case Succ(x) if isNumVal(x) => true
    case _ => false
  }
}