package tp08

import tp09.Zero

import tp09.Var

import tp09.Val

import tp09.Typ

import tp09.True

import tp09.Term

import tp09.Succ

import tp09.Pred

import tp09.Nat

import tp09.Iszero

import tp09.False

import tp09.Cond

import tp09.Bool

import tp09.App

import tp09.Abs

class Formatter {
  def format(t : Term) : String = {
    format(t, 0)
  }

  private def format(t : Term, shift : Int) : String = {
    t match {
      case Var(x) => x
      case Abs(x, typ, t1) => "\u03BB" + x + " : " + format(typ) + ".\r\n" +
          indent(shift + 2) + format(t1, shift + 2)
      case App((t1 @ Abs (_, _, _)), t2 @ Var(_)) =>	"(\r\n" +
          indent(shift + 2) + format(t1, shift + 2) + "\r\n" +
          indent (shift) + ") " + format(t2, shift)
      case App(t1, t2 @ Var(_)) => format(t1, shift) + " " + format(t2, shift)
      case App(t1 @ Abs(_, _, _), t2) => "(\r\n" +
          indent(shift + 2) + format(t1, shift + 2) + "\r\n" +
          indent(shift) + ") (\r\n" +
          indent(shift + 2) + format(t2, shift + 2) + "\r\n" +
          indent(shift) + ") "
      case App(t1, t2) =>	format(t1, shift) + " (\r\n" +
          indent(shift + 2) +	format(t2, shift + 2) + "\r\n" +
          indent(shift) + ")"
      case Val(x, t1) => x + " = " + format(t1, shift + 2)
      case True => "true"
      case False => "false"
      case Cond(t1, t2, t3) => "if " +	format(t1, shift + 2) +	" then\r\n" +
	        indent(shift + 2) + format(t2, shift + 2) + "\r\n" + indent (shift) +
	        "else\r\n" + indent(shift + 2) + format(t3, shift + 2)
      case Zero => "0"
      case Succ(t1) => "succ " + format(t1, shift + 2)
      case Pred(t1) => "pred " + format(t1, shift + 2)
      case Iszero(t1) => "iszero " + format(t1, shift + 2)
      case _ => t.toString()
    }
  }
  
  private def indent(shift : Int) : String = {
    if (shift == 0) "" else " " + indent(shift - 1)
  }
  
  /** Renvoie une chaîne de caractères représentant le type typ */
  def format(typ: Typ) : String = typ match {
    case Bool => "Bool"
    case Nat => "Nat"
  }
}
