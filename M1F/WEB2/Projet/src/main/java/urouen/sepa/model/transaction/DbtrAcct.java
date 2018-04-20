package urouen.sepa.model.transaction;

import urouen.sepa.model.transaction.types.IBAN;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modélise le compte débiteur.
 *
 * Contient de facon exculsive l'information
 * sur IBAN du débiteur, ou un identifiant de prélèvement.
 */
@XmlRootElement
public class DbtrAcct {
    // ATTRIBUTS
    private IBAN iban;
    private PrvtId prvtId;

    // CONSTRUCTEURS
    public DbtrAcct(IBAN iban) {
        this.iban = iban;
    }
    public DbtrAcct(PrvtId prvtId) {
        this.prvtId = prvtId;
    }
    public DbtrAcct() {
        this(new IBAN("GB29NWBK60161331926819"));
    }

    // REQUETES
    public IBAN getIban() {
        return iban;
    }
    public PrvtId getPrvtId() {
        return prvtId;
    }

    // COMMANDES
    @XmlElement(name="IBAN")
    public void setIban(IBAN iban) {
        this.iban = iban;
        this.prvtId = null;
    }

    @XmlElement(name="PrvtId")
    public void setPrvtId(PrvtId prvtId) {
        this.iban = null;
        this.prvtId = prvtId;
    }
}
