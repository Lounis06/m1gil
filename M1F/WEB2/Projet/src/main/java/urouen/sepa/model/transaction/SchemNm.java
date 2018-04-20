package urouen.sepa.model.transaction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SchemNm {
    @XmlElement(name="Prtry")
    private String priority;

    public SchemNm() {
        priority = "SEPA";
    }
}
