package tp05.mvc

import tp05.util.Publisher
import java.awt.Color;

/**
 * Ce modèle permet d'obtenir des couleurs.
 * On peut faire évoluer le modèle en lui commandant de changer de couleur,
 *  et consulter son état à tout moment.
 * @inv <pre>
 *     getColor() != null </pre>
 */
trait RainbowModel extends Publisher{
  // REQUETES
    
  /**
   * La couleur courante du modèle.
   */
  def getColor : Color;

  // COMMANDES
    
  /**
   * Modification de l'état interne.
   * Un appel à cette méthode oblige le modèle a changer de couleur.
   * @post <pre>
   *     !getColor().equals(old getColor())
   *     Un événement de type ColorChanged a été émis.</pre>
   */
  def changeColor
}
