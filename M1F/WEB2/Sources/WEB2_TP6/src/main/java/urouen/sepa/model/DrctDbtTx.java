package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DrctDbtTx")
public class DrctDbtTx {
    // ATTRIBUT
    @XmlElement(name="MndtRltdInf")
    private MndtRltdInf mandataire;

    // CONSTRUCTEURS
    public DrctDbtTx(MndtRltdInf mandataire) {
        this.mandataire = mandataire;
    }
    public DrctDbtTx() {}
}
