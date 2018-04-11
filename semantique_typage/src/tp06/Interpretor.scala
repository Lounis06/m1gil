package tp06

import java.io.FileReader
import scala.util.parsing.input.StreamReader
import scala.util.parsing.input.Reader


class Interpretor {
  val parser = new Parser
  val evaluator = new Evaluator
  
  /**
   * Interprète le flux fourni par reader.
   * Une analyse syntaxique du flux est réalisée par le parser pour construire
   *  les termes associés, puis chaque terme est évalué par l'evaluator et le
   *  résultat affiché sur la sortie standard.
   * Un message d'erreur est produit en cas d'erreur syntaxique repérée par le
   *  parser.
   */
  def interpret(reader : Reader[Char]) : Unit = {
    val t = parser.parse(parser.term, reader)
    val term = t.get
    if(term == EOF) {
      return
    }
    println(evaluator.evaluate(term))
    interpret(t.next)
  }
}

object Main {
  def main(args : Array[String]) {
    val reader = StreamReader(new FileReader("src/tp06/examples.txt"))
    new Interpretor().interpret(reader)
  }
}