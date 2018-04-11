package tp07

sealed abstract trait Term
case object EOF extends Term
case class Var(name : String) extends Term
case class Val(x : String, t : Term) extends Term
case class Abs(x: String, t: Term) extends Term
case class App(t1: Term, t2: Term) extends Term

