package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FinInstnId")
public class FinInstnId {
    // ATTRIBUT
    @XmlElement(name="BIC")
    private String bic;

    // CONSTRUCTEURS
    public FinInstnId(String bic) {
        this.bic = bic;
    }
    public FinInstnId() {}
}
