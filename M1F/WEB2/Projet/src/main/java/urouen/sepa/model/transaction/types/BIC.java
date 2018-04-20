package urouen.sepa.model.transaction.types;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Modélise un BIC (Bank Identifier Code)
 *
 * Un BIC est composé en 3 parties :
 *      - Un code de banque composé de 4 lettres majuscules.
 *      - Un code pays composé de 2 lettres majuscules.
 *      - Un code de localisation composé de 2 lettres majuscules.
 *      - Un code de filiale composé de 3 lettres majuscules. (Optionnel)
 */
@XmlType(name="BICType")
public class BIC {
    // CONSTANTE
    /** Le motif de construction d'un BIC */
    public static String PATTERN = "[A-Z]{8}|[A-Z]{11}";


    // ATTRIBUT
    /** Le texte décrivant le BIC */
    private String bic;


    // CONSTRUCTEUR
    public BIC(String bic) {
        affectBic(bic);
    }
    public BIC() {this("CICFRLOL"); }


    // REQUETES
    public String getBic() {
        return bic;
    }

    @Override
    public String toString() {
        return getBic();
    }


    // COMMANDE
    @XmlValue
    public void setBic(String bic) {
        affectBic(bic);
    }


    // OUTIL
    /**
     * Utilisé pour le contrôle des valeurs lors de l'affectation
     */
    private void affectBic(String bic) {
        /** Vérification de la cohérence de l'argument */
        if (!bic.matches(PATTERN)) {
            throw new AssertionError("BIC mal formé");
        }

        this.bic = bic;
    }
}