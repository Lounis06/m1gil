package urouen.sepa.model;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.Optional;

@XmlRootElement(name="DrctDbtTxInf")
public class SEPA {
    // ATTRIBUT
    @XmlElement(name="PmtId")
    private String transaction;

    @XmlElement(name="InstdAmt")
    private InstdAmt montant;

    @XmlElement(name="DrctDbtTx")
    private DrctDbtTx mandataire;

    @XmlElement(name="DbtrAgt")
    private DbtrAgt agentDebiteur;

    @XmlElement(name="Dbtr")
    private Dbtr debiteur;

    @XmlElement(name="DbtrAcct")
    private DbtrAcct compteDebiteur;

    @XmlElement(name="RmtInf")
    private String infos;

    // CONSTRUCTEURS
    public SEPA() {
        this.transaction = transaction;
        this.montant = new InstdAmt(2150.08, "EUR");
        this.mandataire = new DrctDbtTx(new MndtRltdInf("MANDAT NO 666666", new Date()));
        this.agentDebiteur = new DbtrAgt(new FinInstnId("BANKGBUL"));
        this.debiteur = new Dbtr("Mr Debiteur N2");
        this.compteDebiteur = new DbtrAcct(new IBAN("GB29NWBK60161331926819"));
        this.infos = "Facture reference ISO 654321";
    }
}
