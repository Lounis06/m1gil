package urouen.sepa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by franck on 29/03/17.
 */
@XmlRootElement(name="MndtRtldInf")
public class MndtRltdInf {
    // ATTRIBUTS
    @XmlElement(name="MndtId")
    private String id;

    @XmlElement(name="DtOfSgntr")
    private Date date;

    // CONSTRUCTEUR
    public MndtRltdInf(String id, Date date) {
        this.id = id;
        this.date = date;
    }
    public MndtRltdInf() {}
}
