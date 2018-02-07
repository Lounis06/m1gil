package tp01.complex

import scala.math

/**
 * Revoir en s'inspirant des Rational
 */

/**
 * Une classe modélisant les nombres complexes
 * On fera le nécessaire pour définir un ordre sur les complexes basé sur
 * l'équivalence suivante : 
 *  (a + bi < a' + b'i) <==> (a < a' || (a == a' && b < b'))
 * On permettra la création de complexes via leurs coordonnées polaires.
 * rappel : a + bi = (cos(argument) * module) + (sin(argument) * module) * i
 * 
 */
class Complex(val real: Double, val imag: Double) {
  /**
   * Pour afficher élégamment les nombres complexes, y compris quand la partie
   *  réelle est nulle ou quand la partie imaginaire vaut -1, 0 ou 1 
   */
  override def toString = 
  real.toString + {if (imag >= 0) "+" + imag.toString else "-" + imag.toString} + "i"
  /**
   * Le module du nombre complexe
   * rappel : module(a + bi) = sqrt(a * a + b * b)
   */
  def mod = math.sqrt(real*real + imag*imag)
  /**
   * L'argument d'un nombre complexe
   * rappel : argument(c = a + bi) = cos^{-1}(a / module(c))
   */
  def arg = 1/math.cos(real/mod)
  /**
   * Le complexe obtenu en additionnant "this" et "that"
   */
  def +(that: Complex) = new Complex(real + that.real, imag + that.imag)
  /**
   * Le complexe obtenu en soustrayant "that" à "this"
   */
  def -(that: Complex) = new Complex(real - that.real, imag - that.imag)
  /**
   * Le complexe obtenu en multipliant "this" et "that"
   */
  def *(that: Complex) = new Complex(real * that.real - imag * that.imag, real * that.imag + imag * that.real)
  /**
   * Le complexe obtenu en divisant "this" par "that"
   */
  def /(that: Complex) = {
    val div = math.pow(that.real, 2) + math.pow(that.imag, 2)
    new Complex(
      (real * that.real + imag * that.imag) / div,
      (imag * that.real - real * that.imag) / div)}
      
      
  /**
   * Le complexe conjugué de "this"
   * rappel : conj(a + bi) = a - bi
   */
  def conj = new Complex(real, - imag)
}
