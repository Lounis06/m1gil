package urouen.sepa.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


public class InstdAmt {
    // ATTRIBUTS
    @XmlValue
    private double montant;

    @XmlAttribute(name="Ccy")
    private String currency;


    // CONSTRUCTEURS
    public InstdAmt(double montant, String currency) {
        this.montant = montant;
        this.currency = currency;
    }
    public InstdAmt() {}
}