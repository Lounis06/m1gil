package urouen.sepa.model.transaction;

import urouen.sepa.model.transaction.types.Max35Text;
import urouen.sepa.model.transaction.types.XSDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(name="DrctDbtTxType", propOrder={"id", "date"})
@XmlRootElement(name="DrctDbtTx")
public class DrctDbtTx {
    // ATTRIBUTS
    private Max35Text id;
    private XSDate date;

    // CONSTRUCTEUR
    public DrctDbtTx(Max35Text id, Date date) {
        this.id = id;
        this.date = new XSDate();
    }
    public DrctDbtTx() {
        this(new Max35Text("MANDAT NO 666666"), new Date());
    }

    // REQUETES
    public Max35Text getId() {
        return id;
    }

    public XSDate getDate() {
        return date;
    }

    // COMMANDES
    @XmlElement(name="MndtId")
    public void setId(Max35Text id) {
        this.id = id;
    }

    @XmlElement(name="DtOfSgntr")
    public void setDate(XSDate date) {
        this.date = date;
    }
}
