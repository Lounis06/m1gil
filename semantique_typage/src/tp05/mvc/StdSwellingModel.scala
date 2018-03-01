package tp05.mvc

import java.awt.Dimension

class StdSwellingModel (val minimalDim : Dimension) extends SwellingModel {
  // ATTRIBUTS
  private var internalDimension : Dimension = new Dimension(minimalDim)

  // REQUETES
  override def getDimension = new Dimension(internalDimension)
  
  // COMMANDES
  override def inflate(factor : Double) {
    require(factor >= 0)
    computeNewDimension(factor)
  }
  override def deflate(factor : Double) {
    require(factor >= 0 && factor <= 1)
    computeNewDimension(-factor)
  }

  /**
   * Calcule, en fonction de factor, une nouvelle dimension et la place dans
   *  internalDimension.
   * Si factor est positif, cela constitue une augmentation de taille.
   * Si factor est négatif, cela constitue une diminution de taille.
   */
  private def computeNewDimension(factor : Double) {
    internalDimension.width = nearestValueBetween(
        minimalDim.width,
        maximalDim.width,
        (internalDimension.width * (1 + factor)).toInt
    )
    internalDimension.height = nearestValueBetween(
        minimalDim.height,
        maximalDim.height,
        (internalDimension.height * (1 + factor)).toInt
    )
  }
  /**
   * Renvoie l'entier, dans l'intervalle [min, max], le plus proche de val.
   * @pre <pre>
   *     min <= max </pre> 
   */
  private def nearestValueBetween(min : Int, max : Int, value : Int) = {
    require(min <= max)
    max.min(min.max(value))
  }
}
