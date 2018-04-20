package urouen.sepa.model.transaction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Othr2 {
    // ATTRIBUT
    private String id;
    private SchemNm schemNm;

    // CONSTRUCTEURS
    public Othr2(String id) {
        this.id = id;
        this.schemNm = new SchemNm();
    }
    public Othr2() {}

    // REQUETE
    public String getId() {
        return id;
    }
    public SchemNm getSchemNm() {
        return schemNm;
    }

    // COMMANDE
    @XmlElement(name="Id")
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="SchemNm")
    public void setSchemNm(SchemNm schemNm) {
        this.schemNm = schemNm;
    }
}
