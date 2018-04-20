package urouen.sepa.model.transaction.types;

import urouen.sepa.model.dao.TransactionDAO;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.Map;

/**
 * Permet de fournir un numéro d'identification propre au SGBD
 * stockant les transactions manipulées par le service.
 */
@XmlType(name="DBIDType")
public class DBID {
    // CONSTANTE
    /** Le prefixe utilisé pour chaque identifiant */
    public static final String PREFIX = "CP";

    /** Le motif de construction d'un DBID */
    public static final String PATTERN = PREFIX + "[0-9]{4}";


    // ATTRIBUT
    /** Le texte décrivant l'identifiant */
    private String dbid;


    // CONSTRUCTEUR
    public DBID(String dbid) {
        affectDbid(dbid);
    }
    public DBID() { this(getNextId()); }


    // REQUETE
    public String getId() { return dbid; }

    @Override
    public String toString() {
        return dbid;
    }


    // COMMANDE
    @XmlValue
    public void setId(String dbid) {
        affectDbid(dbid);
    }


    // OUTIL
    /**
     * Utilisé pour le contrôle des valeurs lors de l'affectation
     */
    private void affectDbid(String dbid) {
        /** Vérification de la cohérence de l'argument */
        if (!dbid.matches(PATTERN)) {
            throw new AssertionError("BIC mal formé");
        }

        this.dbid = dbid;
    }


    // MECANISME DE GENERATION
    /** Le n° contenu dans le dernier identifiant généré */
    private static Integer lastId;

    /**
     * Renvoie la chaîne décrivant le prochain identifiant disponible
     *
     * @return La chaîne correspondante, i.e la concaténation du préfixe
     * d'identification avec le n° écrit en 4 chiffres.
     */
    private static String getNextId() {
        // Récupération du dernier n° utilisé, s'il n'est pas déjà défini
        if (lastId == null) {
            // On recherche le dernier id utilisé par le DAO.
            Map<String, String> stats = TransactionDAO.INSTANCE.stats();
            if (stats != null) {
                lastId = Integer.valueOf(stats.get("lastId").substring(2));
            } else {
                lastId = 0;
            }
        }

        // Renvoi d'un n° d'identification correct
        ++lastId;
        return PREFIX + String.format("%04d", lastId);
    }
}


