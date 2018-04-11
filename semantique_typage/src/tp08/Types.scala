package tp08

sealed abstract trait Term
case object EOF extends Term
case object True extends Term
case object False extends Term
case object Zero extends Term
case class Cond(cond:Term, t1:Term, t2:Term) extends Term
case class Succ(t:Term) extends Term
case class Pred(t:Term) extends Term
case class Iszero(t:Term) extends Term
case class Var(name : String) extends Term
case class Val(x : String, t : Term) extends Term
case class Abs(x: String, typ: Typ, t: Term) extends Term
case class App(t1: Term, t2: Term) extends Term

sealed abstract trait Typ
case object Bool extends Typ
case object Nat extends Typ