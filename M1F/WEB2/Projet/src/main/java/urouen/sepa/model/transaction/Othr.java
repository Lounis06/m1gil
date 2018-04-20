package urouen.sepa.model.transaction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Othr {
    // ATTRIBUT
    private String id;

    // CONSTRUCTEURS
    public Othr(String id) {
        this.id = id;
    }
    public Othr() {}

    // REQUETE
    public String getId() {
        return id;
    }

    // COMMANDE
    @XmlElement(name="Id")
    public void setId(String id) {
        this.id = id;
    }
}
