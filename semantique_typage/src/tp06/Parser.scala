package tp06

import scala.util.parsing.combinator.RegexParsers

/**
 * t --> '('t')'
 *    |  '\z'
 *    |  false
 *    |  true
 *    |  if t then t else t
 *    |  0
 *    |  succ t
 *    |  pred t
 *    |  iszero t
 */
class Parser extends RegexParsers {
  def term : Parser[Term] = (parenTerm | eof | t | f | z | cond | succ | pred | isZero)
  def parenTerm = ("("~>term<~")")
  def eof = """\z""".r ^^ { _ => EOF }
  def t = "true".r ^^ { _ => True }
  def f = "false".r ^^ { _ => False }
  def z = "0".r ^^ { _ => Zero }
  def cond = ("if"~>term)~("then"~>term)~("else"~>term) ^^ { case c ~ t1 ~ t2 => If(c, t1, t2) }
  def succ = ("succ"~>term) ^^ { Succ(_) }
  def pred = ("pred"~>term) ^^ { Pred(_) }
  def isZero = ("iszero"~>term) ^^ { IsZero(_) }
}
