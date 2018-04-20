package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DbtrAgt")
public class DbtrAgt {
    // ATTRIBUT
    @XmlElement(name="FinInstnId")
    private FinInstnId agentDebiteur;

    // CONSTRUCTEURS
    public DbtrAgt(FinInstnId agentDebiteur) {
        this.agentDebiteur = agentDebiteur;
    }
    public DbtrAgt() {}
}
