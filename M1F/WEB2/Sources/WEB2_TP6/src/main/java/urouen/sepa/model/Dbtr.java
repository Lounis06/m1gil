package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Dbtr")
public class Dbtr {
    // ATTRIBUT
    @XmlElement(name="Nm")
    private String name;

    // CONSTRUCTEURS
    public Dbtr(String name) {
        this.name = name;
    }
    public Dbtr() {}
}
