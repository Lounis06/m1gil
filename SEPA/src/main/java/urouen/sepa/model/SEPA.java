package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SEPA")
public class SEPA {
    String transaction;
    int montant;

    public SEPA() {
        this.transaction = "Undefined";
        this.montant = 0;
    }

    public SEPA(String transaction, int montant) {
        this.transaction = transaction;
        this. montant = montant;
    }

    public String getTransaction() {
        return transaction;
    }

    @XmlElement
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public int montant() {
        return montant;
    }

    @XmlElement
    public void setMontant (int montant) {
        this. montant = montant;
    }
}
