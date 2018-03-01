package tp05.mvc

import tp05._
import java.awt.Color
import tp05.util.PropertyOfType
import StdRainbowModel._

class StdRainbowModel extends RainbowModel {
  // ATTRIBUTS
  private var currentColorIndex = 0

  // REQUETES
  def getColor = colors(currentColorIndex)

  // COMMANDES
  def changeColor = {
    currentColorIndex = (currentColorIndex + 1) % colors.length
    publish(currentColor, getColor)
  }
  
  // PROPRIETES
  object currentColor extends PropertyOfType[Color]
}

object StdRainbowModel{
  private final val colors = Array (
    Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
    Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
    Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
  )
}