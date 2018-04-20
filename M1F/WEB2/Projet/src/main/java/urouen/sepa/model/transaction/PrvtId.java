package urouen.sepa.model.transaction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modélise un identifiant de prélèvement
 */
@XmlRootElement
public class PrvtId {
    // ATTRIBUT
    private Othr2 othr;

    // CONSTRUCTEUR
    public PrvtId(String prvtId) {
        this.othr = new Othr2(prvtId);
    }
    public PrvtId() {
        this("Identifier 46-58-832-1485");
    }

    // REQUETE
    public Othr2 getOthr() {
        return othr;
    }

    @XmlElement(name="Othr")
    public void setOthr(Othr2 othr) {
        this.othr = othr;
    }
}
