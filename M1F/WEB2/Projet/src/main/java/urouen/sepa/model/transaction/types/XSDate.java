package urouen.sepa.model.transaction.types;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Modélise le texte utilisé pour représenter une date donnée.
 */
@XmlType(name="xs:dateTime")
public class XSDate {
    // CONSTANTE
    /** Le foramt utilisé pour décrire une date */
    public static final DateFormat PATTERN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


    // ATTRIBUT
    /** Le texte décrivant la devise utilisée */
    private String date;


    // CONSTRUCTEUR
    public XSDate(Date d) {
        affectDate(d);
    }
    public XSDate() { this(new Date()); }


    // REQUETE
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getDate();
    }


    // COMMANDE
    @XmlValue
    public void setDate(String date) {
        affectDate(date);
    }


    // OUTIL
    /**
     * Utilisé pour le contrôle des valeurs lors de l'affectation
     */
    private void affectDate(Date date) {
        this.date = PATTERN.format(date);
    }
    private void affectDate(String s) {
        try {
            PATTERN.parse(s);
        } catch (Exception e) {
            throw new AssertionError();
        }
        this.date = s;
    }
}
