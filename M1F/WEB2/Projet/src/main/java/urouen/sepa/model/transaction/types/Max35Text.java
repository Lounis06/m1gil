package urouen.sepa.model.transaction.types;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Modélise un court texte, de longueur inférieure à 35 caractères.
 */
@XmlType(name="Max35Text")
public class Max35Text {
    // ATTRIBUT
    private String text;

    // CONSTRUCTEUR
    public Max35Text(String text) {
        if (text.length() > 35) {
            throw new AssertionError();
        }

        this.text = text;
    }
    public Max35Text() { this(""); }

    // REQUETE
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    // COMMANDE
    @XmlValue
    public void setText(String text) {
        this.text = text;
    }
}
