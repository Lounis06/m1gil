package tp07

object Util {
  
  /** 
   * Construit une séquence d'applications de la forme t1 t2 ... tk
   *  à partir de terms = List(t1, t2, ..., tk).
   * Rappel : l'application est associative à gauche
   *  (i.e. t1 t2 t3 ~ (t1 t2) t3)
   */
  def buildApp(terms : List[Term]) : Term = terms.reduceLeft((t1, t2) => App(t1, t2))

  /** Remplace, dans t2, toutes les occurrences de x par t1. */
  def subst(x : String, t1 : Term, t2 : Term) : Term = t2 match {
    case Var(y) if x == y => t1
    case App(r1, r2) => App(subst(x, t1, r1), subst(x, t1, r2))
    case Abs(y, t) if x != y => Abs(y, subst(x, t1, t))
    case Val(y, t) if x != y => Val(y, subst(x, t1, t))
    case _ => t2
  }
  
  /** Type des éléments d'un contexte. */
  type Alias = (String, Term)
  
  /**
   *  Remplace dans t chaque alias du contexte ctx par le terme qui lui est
   *   associé.
   */
  def inject(ctx : List[Alias], t : Term) : Term = ctx.foldLeft(t)((t, al) => subst(al._1, al._2, t))
  
  /** Si optT définit un nouvel alias, l'ajouter en tête du contexte ctx. */
  def buildNewCtx(ctx : List[Alias], optT : Option[Term]) : List[Alias] = 
    optT match {
      case Some(Val(x, t)) => (x, t)::ctx
      case _ => ctx
    }
  
  /** t est-il une valeur ? */
  def isVal(t : Term) : Boolean = 
    t match {
      case Val(_, t1) => isVal(t1)
      case Abs(_, _) => true
      case _ => false
    }
  
  /** t est-il un terme clos ? */
  def isClosed(t : Term) : Boolean = {
    def isCl(t : Term, vars : List[String]) : Boolean = {
      t match {
        case Abs(x, r) => isCl(r, x::vars)
        case App(r1, r2) => isCl(r1, vars) && isCl(r2, vars)
        case Var(x) if vars.contains(x) => true
        case Val(x, r) => isCl(r, vars)
        case _ => false
      }
    }
    
    isCl(t, List[String]())
  }
}