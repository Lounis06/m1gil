package tp09

import java.io.File
import java.io.FileReader
import java.io.PrintStream

import scala.Console._
import scala.util.parsing.input.Reader
import scala.util.parsing.input.StreamReader

import Util._
import tp09.TypeChecker
import tp09.Evaluator
import tp09.Formatter
import tp09.Term
import tp09.Util


class Interpretor {
  val parser = new Parser
  val evaluator = new Evaluator
  val typeChecker = new TypeChecker
  val formatter = new Formatter
  
  def interpret(reader : Reader[Char]) : Unit = {
    Console.withOut(new PrintStream(new File("src/tp08/results.txt"))) {
      interpret(reader, List())
    }
  }

  /**
   * Interprète le flux fourni par reader dans le contexte ctx.
   * Les termes produits au fur et à mesure de l'analyse syntaxique sont :
   *  - vérifiés clos ;
   *  - typés ;
   *  - évalués, le résultat étant produit sur la sortie standard.
   * Si erreur survient au cours d'une des trois premières étapes elle est
   *  signalée sur la sortie standard (rq : aucune erreur ne peut survenir
   *  à l'évaluation, puisque le langage a été montré sûr en cours).  
   * Indication : pour alléger cette méthode, on fera appel à la méthode
   *  "interpret(t: Term)".
   */
  private def interpret(reader: Reader[Char], ctx: List[Alias]) : Unit = ???
  
  /**
   * Teste si t est un terme clos.
   * Si la réponse est négative, on formate le terme que l'on affiche sur la
   *  sortie standard et on signale qu'il est non clos.
   * Sinon, on essaie de le typer, puis de l'évaluer et de retourner la valeur
   *  obtenue avec la méthode "typingAndEval".
   */
  private def interpret(t: Term) = ???
  
  /**
   * Essaie de typer t.
   * En cas de succès, on évalue t et affiche la valeur obtenue après formatage
   *  ainsi que son type. La valeur est ensuite retournée.
   * Sinon, on signale l'erreur de typage.
   */
  private def typingAndEval(t: Term) = ???
}

object Main {
  def main(args : Array[String]) {
    val reader = StreamReader(new FileReader("src/tp08/examples.txt"))
    new Interpretor().interpret(reader)
  }
}