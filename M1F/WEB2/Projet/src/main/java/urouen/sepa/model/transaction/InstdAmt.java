package urouen.sepa.model.transaction;

import urouen.sepa.model.transaction.types.Amount;
import urouen.sepa.model.transaction.types.Ccy;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Décrit le montant de la transaction.
 */
@XmlRootElement
public class InstdAmt {
    // ATTRIBUTS
    /** La valeur du montant */
    private Amount amount;

    /** La devise de la monnaie utilisée */
    private Ccy currency;


    // CONSTRUCTEURS
    public InstdAmt(Amount amount, Ccy currency) {
        this.amount = amount;
        this.currency = currency;
    }
    public InstdAmt() {
        this(new Amount(2150.08), new Ccy("EUR"));
    }


    // REQUETE
    public Amount getAmount() {
        return amount;
    }
    public Ccy getCurrency() {
        return currency;
    }


    // COMMANDE
    @XmlElement(name="amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @XmlAttribute(name="Ccy")
    public void setCurrency(Ccy currency) {
        this.currency = currency;
    }
}