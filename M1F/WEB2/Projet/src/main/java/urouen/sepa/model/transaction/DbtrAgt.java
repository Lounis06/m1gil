package urouen.sepa.model.transaction;

import urouen.sepa.model.transaction.types.BIC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DbtrAgt")
public class DbtrAgt {
    // ATTRIBUT
    private BIC bic;
    private Othr othr;

    // CONSTRUCTEURS
    public DbtrAgt(BIC bic) {
        this.bic = bic;
    }
    public DbtrAgt(Othr othr) {
        this.othr = othr;
    }
    public DbtrAgt() {
        this(new BIC("BANKGBUL"));
    }

    // REQUETES
    public BIC getBic() {
        return bic;
    }

    public Othr getOthr() {
        return othr;
    }

    // COMMANDES
    @XmlElement(name="BIC")
    public void setBic(BIC bic) {
        this.bic = bic;
        this.othr = null;
    }

    @XmlElement(name="Othr")
    public void setOthr(Othr othr) {
        this.bic = null;
        this.othr = othr;
    }
}
