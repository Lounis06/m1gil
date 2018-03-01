package tp05.mvc

import tp05._
import java.awt.Color
import scala.swing.Reactor
import scala.swing.BorderPanel
import scala.swing.FlowPanel
import scala.swing.SimpleSwingApplication
import scala.swing.Button
import scala.swing.MainFrame
import scala.swing.Dimension
import scala.swing.event.ButtonClicked

object Rainbow extends SimpleSwingApplication {
  // Création du modèle
  private val model = new StdRainbowModel
  
  // Création des composants graphiques
  private val changeColorButton = new Button("Modifier")
  private lazy val mainPane = mainFrame.contents(0) 

  // Positionnement des composants graphiques
  private val mainFrame = new MainFrame {
    title = "Arc-en-ciel"
    preferredSize = new Dimension(300,300)
    contents = getContents 
    peer.setLocationRelativeTo(null)
  }
  private def getContents = new BorderPanel {
    add(new FlowPanel(changeColorButton), BorderPanel.Position.North)
  }

  // Création et branchement des contrôleurs
  new Reactor {
    reactions += { 
      case ButtonClicked(b) => model.changeColor
    }
  }.listenTo(changeColorButton)
    
  private val action = (c : Color) => updateBackground(c)
  (new util.Reactor).listenTo(model, model.currentColor, action)
  
  // Point d'entrée
  def top = mainFrame
  
  // Initialisation de la vue
  updateBackground(model.getColor)
  
  // Outils privés
  private def updateBackground(c : Color) = mainPane.background_=(c)
}