package tp05.mvc

import tp05._
import tp05.util._
import scala.swing.Button
import java.awt.Dimension
import scala.swing.BorderPanel
import scala.swing.MainFrame
import scala.swing.FlowPanel
import scala.swing.Label
import scala.swing.TextField
import java.util.ArrayList


object Swelling {
  // Création du modèle
  private val model : SwellingModel = new StdSwellingModel(new Dimension(300, 100))
  
  // Création des composants graphiques
  private val buttons : List[Button] = List[Button]()
  private val factorText : TextField = new TextField("Facteur : ")
  private lazy val mainPane = mainFrame.contents(0)
        
  // Positionnement des composants graphiques
  private val mainFrame = new MainFrame {
    title = "Baudruche"
    contents = getContents 
    peer.setLocationRelativeTo(null)
  }
  private def getContents = new BorderPanel {
    val fP = new FlowPanel() {
      Commands.values.foreach(v => contents += new Button(v.toString()))
    }
    add(fP, BorderPanel.Position.South)
    add(new FlowPanel(factorText), BorderPanel.Position.South)
  }
  
  // Création et branchement des contrôleurs
  
  // Point d'entrée
  def top = mainFrame
  
  // Outils privés
  private object Commands extends Enum[Commands]
  private sealed trait Commands extends Commands.Value {
    def activate {
      getFactor match {
        case Some(fact) => if (isValidFactor(fact)) changeModelState(fact)
            else popupInvalidFactor("le nombre " + fact + " n'est pas valide")
        case None => // rien à faire
      }
    }
    protected def changeModelState(fact : Double)
    protected def isValidFactor = (_ : Double) => true // impl par défaut
    protected def getFactor = getModificationFactor // impl par défaut
  }
  private case object PLUS extends Commands {
    override def changeModelState(fact : Double) {model.inflate(fact)}
    override def isValidFactor = _ >= 0
    override def toString = "Plus"
  }
  private case object MOINS extends Commands {
    override def changeModelState(fact : Double) {model.deflate(fact)}
    override def isValidFactor = f => f >= 0 && f <= 1
    override def toString = "Moins"
  }
  private case object DOUBLE extends Commands {
    override def changeModelState(fact : Double) {model.inflate(fact)}
    override def toString = "Double"
    override def getFactor = Some(1)
  }
  private case object MOITIE extends Commands {
    override def changeModelState(fact : Double) {model.deflate(fact)}
    override def toString = "Moitié"
    override def getFactor= Some(.5)
  }
  /* Renvoie l'éventuel facteur inscrit dans le champ de texte */
  private def getModificationFactor : Option[Double] = ???
  
  /* Produit un pop-up mentionnant le message "msg" (utile quand le champ
   *  texte contient une chaine non transformable en un double)
   */
  private def popupInvalidFactor(msg : String) = ???
}