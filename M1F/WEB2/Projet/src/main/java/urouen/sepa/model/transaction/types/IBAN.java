package urouen.sepa.model.transaction.types;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Modélise un IBAN (International Bank Account Number)
 *
 * Un IBAN est composé en 3 parties :
 *      - Un code pays composé de 2 lettres.
 *      - Une clé de contrôle de 2 chiffres.
 *      - Une suite d'identification de 1 à 30 chiffres.
 */
@XmlType(name="IBANType")
public class IBAN {
    // CONSTANTE
    /** Le motif de construction d'un IBAN */
    public static String PATTERN = "[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}";


    // ATTRIBUT
    private String iban;


    // CONSTRUCTEUR
    public IBAN(String iban) {
        if (!iban.matches(PATTERN)) {
            throw new AssertionError("IBAN mal formé");
        }

        this.iban = iban;
    }


    // REQUETE
    public String getIban() { return iban; }

    @Override
    public String toString() { return getIban(); }

    // COMMANDE
    @XmlValue
    public void setIban(String iban) {
        affectIban(iban);
    }


    // OUTIL
    /**
     * Utilisé pour le contrôle des valeurs lors de l'affectation
     */
    private void affectIban(String iban) {
        /** Vérification de la cohérence de l'argument */
        if (!iban.matches(PATTERN)) {
            throw new AssertionError("BIC mal formé");
        }

        this.iban = iban;
    }
}