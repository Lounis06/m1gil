package tp08

import scala.util.parsing.combinator.JavaTokenParsers

import tp08.Util.buildApp
import tp08.Util.buildFctType

/**
 * prog --> '\z'
 *   | cmd ";;"
 * cmd --> t
 *   |  val v '=' t
 * t --> open
 *   |  atom t?
 * open --> lambda x':' typ'.' t
 *   |  if t then t else t
 *   |  succ t
 *   |  pred t
 *   |  iszero t
 * atom --> x
 *   |  '('t')'
 *   |  true
 *   |  false
 *   |  0
 * typ --> atomTyp ("->" typ)?
 * atomTyp --> '('typ')'
 *   | Bool
 *   | Nat
 * Indication : le non-terminal "typ" engendre un atomTyp suivi d'une séquence
 *  optionnelle de ('->' atomTyp). On gère cette situation de manière similaire
 *  à la séquence d'applications traitée la séance précédente. Cette fois, on
 *  fera appel à la méthode "Util.buildFctType" pour construire le type
 *  fonctionnel en cascade à partir d'une liste de types. 
 */
class Parser extends JavaTokenParsers {
  protected override val whiteSpace = """(\s|#.*)+""".r
  override val ident = """[a-zA-Z][a-zA-Z0-9]*""".r  
  def keywords = "lambda".r | "true".r | "false".r | "if".r | "then".r |
    "else".r | "val".r | "succ".r | "pred".r | "iszero".r
  
  def prog : Parser[Term] = ( eof | cmd<~";;" )
  def cmd : Parser[Term] = ( term
      | ("val"~>variable)~("="~>term) ^^ { case Var(x)~t => Val(x, t) }
  )
  def eof = """\z""".r ^^ { _ => EOF}
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
  )
  def atom : Parser[Term] = ( variable
      | ("("~>term<~")") ^^ { case t => t }
      | "true".r ^^ { _ => True }
      | "false".r ^^ { _ => False }
      | "0".r ^^ { _ => Zero }
  )
  def typ : Parser[Typ] = atomTyp.+ ~ ("->"~>typ.?) ^^ {
    case t ~ None => buildFctType(t)
    case t ~ Some(t1) => ???
  }
  def atomTyp : Parser[Typ] = ( "("~>typ<~")" ^^ { case t => t } 
      | "Bool".r ^^ { _ => Bool } 
      | "Nat".r ^^ { _ => Nat }
  )
  def variable : Parser[Term] = (not(keywords)~>ident) ^^ { Var(_)}
}