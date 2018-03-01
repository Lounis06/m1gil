package tp05.util

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

/**
 * Modélisation d'un publieur de changements de valeur d'une propriété.
 *  Une classe peut étendre ce trait si elle veut faire connaitre les
 *  changements de valeurs de ses propriétés aux objets réagissants qui
 *  l'écoutent.
 * Typiquement, on écrira :
 *  class C extends Publisher {
 *    ...
 *    // Définition de la propriété "property"
 *    // (dont les valeurs sont de type "typ")
 *    object property extends PropertyOfType[typ]
 *    ...
 *    // Méthode modifiant la valeur de property 
 *    def meth(...) {
 *      ...
 *      // Publication de la nouvelle valeur de property
 *      publish(property, value)
 *    }
 *  }
 * 
 * Un objet souhaitant réagir à un tel changement de valeur sera construit de
 *  la façon suivante :
 * (new Reactor()).listenTo(pub, prop, func) où :
 * - pub désigne l'objet publieur
 * - prop désigne la propriété écoutée
 * - func : typ -> Unit désigne la fonction implémentant la réaction souhaitée
 *    lors d'un changement de valeur de la propriété.
 */
trait Publisher {
  private val reactors =
      new mutable.HashMap[Property, ArrayBuffer[AbstractReactor]]

  /**
   * Publication de la nouvelle valeur value de la propriété prop (si elle est
   *  différente de l'ancienne).
   */
  def publish[E](prop : PropertyOfType[E], value : E) {
    if (prop.getValue != value) {
      prop.change(value)
      reactors.getOrElse(prop, Nil).foreach {_.react(prop, value)}
    }
  }
  
  private[util] def subscribe(prop : Property, reactor : AbstractReactor) {
    val buff = reactors.getOrElse(prop, new ArrayBuffer) += reactor
    reactors += (prop -> buff)
  }
  
  private[util] def unsubscribe(prop : Property, reactor : AbstractReactor) {
    reactors.get(prop) match {
      case Some(buff) => buff -= reactor
      case None =>
    }
  }
}
