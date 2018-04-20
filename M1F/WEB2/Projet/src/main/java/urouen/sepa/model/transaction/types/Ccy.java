package urouen.sepa.model.transaction.types;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Modélise le texte utilisé pour représenter une devise.
 */
@XmlType(name="CcyType")
public class Ccy {
    // CONSTANTE
    /** Le motif textuel correspondant à celui d'une devise */
    public static final String PATTERN = "^[A-Z]{3}$";


    // ATTRIBUT
    /** Le texte décrivant la devise utilisée */
    private String ccy;


    // CONSTRUCTEUR
    public Ccy(String ccy) {
        affectCcy(ccy);
    }
    public Ccy() { this("EUR"); }


    // REQUETE
    public String getCcy() {
        return ccy;
    }

    @Override
    public String toString() {
        return getCcy();
    }


    // COMMANDE
    @XmlValue
    public void setCcy(String ccy) {
        affectCcy(ccy);
    }


    // OUTIL
    /**
     * Utilisé pour le contrôle des valeurs lors de l'affectation
     */
    private void affectCcy(String ccy) {
        if (!ccy.matches(PATTERN)) {
            throw new AssertionError();
        }

        this.ccy = ccy;
    }
}
