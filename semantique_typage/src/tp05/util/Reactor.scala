package tp05.util

import scala.swing.event.Event
import scala.collection.mutable

/**
 * Modélise un objet réagissant aux changements de valeur d'une propriété.
 * Cf Publisher pour plus d'explications.
 */
class Reactor[E] extends AbstractReactor {
  private var actions = new mutable.HashMap[Property, E => Unit]
  
  private[util] override def react(prop: Property, value: Any) {
    actions.get(prop) match {
      case Some(action) => action(value.asInstanceOf[E])
      case None =>
    }
  }
  
  /**
   * Mise à l'écoute de la propriété prop, du publieur pub.
   * Et définition de la réaction aux changements de valeur de prop par la
   * fonction action;
   */
  def listenTo(pub: Publisher, prop: PropertyOfType[E], action: E => Unit) {
    actions += (prop -> action)
    pub.subscribe(prop, this)
  }
  
  /**
   * Le réacteur cesse d'écouter la propriété prop du publieur pub.
   */
  def deafTo(pub: Publisher, prop: PropertyOfType[E]) {
    actions -= prop
    pub.unsubscribe(prop, this)
  }
}
