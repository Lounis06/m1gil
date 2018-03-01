package tp05.util

import scala.collection.mutable

/**
 * Représente une propriété d'un publisher.
 * Le publisher peut publier les changement de valeur de cette propriété.
 * Un reactor peut écouter ces publications et réagir en conséquence.
 * Cf Publisher pour plus d'explications.
 */
abstract class PropertyOfType[E] extends Property {
  private var oldValue, newValue : Option[E] = None
  
  def getOldValue = oldValue
  
  def getValue = newValue
  
  def change(value : E) {
    oldValue = newValue
    newValue = Some(value)
  }
}
