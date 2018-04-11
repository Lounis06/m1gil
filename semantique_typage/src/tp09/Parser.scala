package tp09

import scala.util.parsing.combinator.JavaTokenParsers
import Util._
import scala.collection.immutable.ListMap

/**
 * prog --> '\z'
 *   |  cmd ";;"
 * cmd --> seq
 *   |  val v '=' seq
 *   |  letrec f':' typ '=' fct 
 * seq --> t (';' seq)?
 * t --> open
 *   |  atom t?
 * open --> fct
 *   |  if seq then seq else t
 *   |  succ t
 *   |  pred t
 *   |  iszero t
 *   |  let x = seq in t
 * fct --> lambda x':' typ'.' seq
 * atom --> x
 *   |  '('seq')'
 *   |  true
 *   |  false
 *   |  0
 *   |  unit
 * typ --> atomTyp ("->" typ)?
 * atomTyp --> '('typ')'
 *   |  Bool
 *   |  Nat
 *   |  Unit
 */
class Parser extends JavaTokenParsers {
  protected override val whiteSpace = """(\s|#.*)+""".r
  override val ident = """[a-zA-Z][a-zA-Z0-9]*""".r
  def keywords = "lambda".r | "true".r | "false".r | "if".r | "then".r |
    "else".r | "val".r | "succ".r | "pred".r | "iszero".r | "unit".r |
    "Unit".r | "Bool".r | "Nat".r | "letrec".r
  
  def prog : Parser[Term] = ( eof | cmd<~";;" )
  /* cmd --> seq
 *   |  val v '=' seq
 *   |  letrec f':' typ '=' fct
 */
  def cmd: Parser[Term] = ( seq
      | ("val"~>variable)~("="~>seq) ^^ { case Var(x) ~ s => Val(x, s) }
  )
  def eof = """\z""".r ^^ { _ => EOF}
  def seq : Parser[Term] = term<~(";"~>seq.?)
  def term : Parser[Term] = open | atom.+ ~ term.? ^^ {
    case t ~ None => buildApp(t)
    case t ~ Some(t1) => App(buildApp(t), t1)
  }
  def open : Parser[Term] = (
      ("lambda"~>variable)~(":"~>typ)~("."~>term) ^^ { case Var(x)~ t ~ t1 => Abs(x, t, t1) }
      | ("if"~>term)~("then"~>term)~("else"~>term) ^^ { case t1 ~ t2 ~ t3 => Cond(t1,t2,t3) }
      | ("succ"~>term) ^^ { case t => Succ(t) }
      | ("pred"~>term) ^^ { case t => Pred(t) }
      | ("iszero"~>term) ^^ { case t => Iszero(t) }
      | ("let"~>variable)~("="~>seq)~("in"~>term) ^^ { case Var(x) ~ s ~ t => LetIn(x, s, t) }
  )
  def atom : Parser[Term] = ( variable
      | ("("~>term<~")")
      | "true".r ^^ { _ => True }
      | "false".r ^^ { _ => False }
      | "0".r ^^ { _ => Zero }
  )
  def typ : Parser[Typ] = atomTyp.+ ~ ("->"~>typ.?) ^^ {
    case t ~ None => buildFctType(t)
    case t ~ Some(t1) => ???
  }
  def atomTyp : Parser[Typ] = ( "("~>typ<~")"
      | "Bool".r ^^ { _ => Bool } 
      | "Nat".r ^^ { _ => Nat }
      | "Unit".r ^^ { _ => Unit }
  )
  def variable : Parser[Term] = (not(keywords)~>ident) ^^ { Var(_)}
}