package urouen.sepa.model.transaction;

import urouen.sepa.model.transaction.types.Max35Text;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dbtr {
    // ATTRIBUT
    private Max35Text name;


    // CONSTRUCTEURS
    public Dbtr(Max35Text name) {
        this.name = name;
    }
    public Dbtr() {
        this(new Max35Text("Mr Debiteur N1"));
    }


    // REQUETE
    public Max35Text getName() {
        return name;
    }


    // COMMANDE
    @XmlElement(name="Nm")
    public void setName(Max35Text name) {
        this.name = name;
    }
}
