package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Id")
public class IBAN {
    // ATTRIBUT
    @XmlElement(name="IBAN")
    private String iban;

    // CONSTRUCTEURS
    public IBAN(String iban) {
        this.iban = iban;
    }
    public IBAN() {}
}