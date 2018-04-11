package tp06

import Util._

class Evaluator {
  /** Réalise un pas d'évaluation, i.e. produit t' tel que t --> t'. */
  def eval(t : Term) : Term = t match {
    case If(True, t1, t2) => t1
    case If(False, t1, t2) => t2
    case If(b, t1, t2) => If(eval(b), t1, t2)
    case Succ(t) => Succ(eval(t))
    case Pred(Zero) => Zero
    case Pred(Succ(t)) if isNumVal(t) => t
    case Pred(t) => Pred(eval(t))
    case IsZero(Zero) => True
    case IsZero(Succ(t)) if isNumVal(t) => False
    case IsZero(t) => IsZero(eval(t))
  }
  
  /** Evalue t jusqu'à obtenir un terme bloqué. */
  def evaluate(t : Term) : Term = {
    if (isVal(t)) t else evaluate(eval(t))
  }
}
