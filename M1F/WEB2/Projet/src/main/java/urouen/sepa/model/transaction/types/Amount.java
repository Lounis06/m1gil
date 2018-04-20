package urouen.sepa.model.transaction.types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.math.BigDecimal;

/**
 * Modélise un montant de transaction. Un tel montant est un nombre
 * réel tronqué à la deuxième décimale.
 */
@XmlType(name="Amount")
public class Amount {
    // ATTRIBUT
    private double amount;


    // CONSTRUCTEUR
    public Amount(double amount) { affectAmount(amount); }
    public Amount() { this(0); }


    // REQUETES
    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.valueOf(getAmount());
    }


    // COMMANDE
    @XmlValue
    public void setAmount(double amount) { affectAmount(amount); }


    // OUTIL
    /**
     * Utilisé pour le contrôle des valeurs lors de l'affectation
     */
    private void affectAmount(double amount) {
        // Formatage du nombre entré
        BigDecimal bd = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);

        // Stockage de la valeur
        this.amount = bd.doubleValue();
    }
}
