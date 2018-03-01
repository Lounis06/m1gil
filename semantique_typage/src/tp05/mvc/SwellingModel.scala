package tp05.mvc

import java.awt.Dimension
import java.awt.Toolkit

import tp05.util.Publisher

/**
 * Notre modèle consiste en une Dimension que l'on fait croître et décroître
 *  à volonté.
 * La Dimension ne peut évoluer en dehors de certaines limites.
 * @inv <pre>
 *     MINIMAL_DIM.getWidth() <= getDimension().getWidth()
 *     getDimension().getWidth() <= MAXIMAL_DIM.getWidth()
 *     MINIMAL_DIM.getHeight() <= getDimension().getHeight()
 *     getDimension().getHeight() <= MAXIMAL_DIM.getHeight() </pre>
 * @cons <pre>
 * $DESC$ un modèle de taille MINIMAL_DIM
 * $POST$ getDimension().equals(MINIMAL_DIM) </pre>
 */
trait SwellingModel extends Publisher {
  /* La dimension minimale du modèle. */
  val minimalDim : Dimension
  /* La dimension maximale du modèle. */
  val maximalDim : Dimension = Toolkit.getDefaultToolkit().getScreenSize();
  /* La dimension du modèle. */
  def getDimension : Dimension
  /**
   * Fait croître la dimension dans un rapport de <code>factor</code>.
   * @pre <pre>
   *     0 <= factor </pre>
   * @post <pre>
   *     getDimension().getWidth() == min(
   *         old getDimension().getWidth() * (1 + factor), 
   *         MAXIMAL_DIM.getWidth()
   *     )
   *     getDimension().getHeight() == min(
   *         old getDimension().getHeight() * (1 + factor), 
   *         MAXIMAL_DIM.getHeight()
   *     ) </pre>
   */
  def inflate(factor : Double)
  /**
   * Fait décroître la dimension dans un rapport de <code>factor</code>.
   * @pre <pre>
   *     0 <= factor <= 1 </pre>
   * @post <pre>
   *     getDimension().getWidth() == max(
   *         old getDimension().getWidth() * (1 - factor), 
   *         MINIMAL_DIM.getWidth()
   *     )
   *     getDimension().getHeight() == max(
   *         old getDimension().getHeight() * (1 - factor), 
   *         MINIMAL_DIM.getHeight()
   *     ) </pre>
   */
  def deflate(factor : Double)
}