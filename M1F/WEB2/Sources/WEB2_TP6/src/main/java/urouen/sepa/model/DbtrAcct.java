package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DbtrAcct")
public class DbtrAcct {
    // ATTRIBUT
    @XmlElement(name="Id")
    private IBAN id;

    // CONSTRUCTEURS
    public DbtrAcct(IBAN id) {
        this.id = id;
    }
    public DbtrAcct() {}
}
