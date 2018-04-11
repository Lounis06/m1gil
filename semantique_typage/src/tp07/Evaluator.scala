package tp07

import Util._

class Evaluator {
  /** Réalise un pas d'évaluation, i.e. produit t' tel que t --> t'. */
  def eval(t : Term) : Term = t match {
    case App(t1, t2) if !isVal(t1) => App(eval(t1), t2)
    case App(v1, t2) if !isVal(t2) => App(v1, eval(t2))
    case App(Abs(x, t1), v) => subst(x, v, t1)
    case Val(x, t) => Val(x, eval(t))
    case t => t
  }
  
  /** Evalue t jusqu'à obtenir un terme bloqué. */
  def evaluate(t : Term) : Term = {
    if (isVal(t)) t else evaluate(eval(t))
  }
}
