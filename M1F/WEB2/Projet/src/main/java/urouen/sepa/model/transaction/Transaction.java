package urouen.sepa.model.transaction;

import urouen.sepa.model.transaction.types.DBID;
import javax.xml.bind.annotation.*;

@XmlType(name="DrctDbtTxInfType", propOrder={"DBID","pmtInf","instdAmt", "drctDbtTx", "dbtrAgt", "dbtr", "dbtrAcct", "rmtInf"})
@XmlRootElement(name="DrctDbtTxInf")
public class Transaction {
    // ATTRIBUT
    private DBID dbid;
    private String pmtInf;
    private InstdAmt instdAmt;
    private DrctDbtTx drctDbtTx;
    private DbtrAgt dbtrAgt;
    private Dbtr dbtr;
    private DbtrAcct dbtrAcct;
    private String rmtInf;


    // CONSTRUCTEUR
    public Transaction() {
        this.dbid = new DBID();
        this.pmtInf = "REF OPE test";
        this.instdAmt = new InstdAmt();
        this.drctDbtTx = new DrctDbtTx();
        this.dbtrAgt = new DbtrAgt();
        this.dbtr = new Dbtr();
        this.dbtrAcct = new DbtrAcct();
        this.rmtInf = "Facture reference ISO 654321";
    }


    // REQUETES
    public DBID getDBID() {
        return dbid;
    }

    public String getPmtInf() {
        return pmtInf;
    }

    public InstdAmt getInstdAmt() {
        return instdAmt;
    }

    public DrctDbtTx getDrctDbtTx() {
        return drctDbtTx;
    }

    public DbtrAgt getDbtrAgt() {
        return dbtrAgt;
    }

    public Dbtr getDbtr() {
        return dbtr;
    }

    public DbtrAcct getDbtrAcct() {
        return dbtrAcct;
    }

    public String getRmtInf() {
        return rmtInf;
    }


    // COMMANDES
    @XmlElement(name="DBID")
    public void setDBID(DBID dbid) {
        this.dbid = dbid;
    }

    @XmlElement(name="PmtInf")
    public void setPmtInf(String pmtInf) {
        this.pmtInf = pmtInf;
    }

    @XmlElement(name="InstdAmt")
    public void setInstdAmt(InstdAmt instdAmt) {
        this.instdAmt = instdAmt;
    }

    @XmlElement(name="DrctDbtTx")
    public void setDrctDbtTx(DrctDbtTx drctDbtTx) {
        this.drctDbtTx = drctDbtTx;
    }

    @XmlElement(name="DbtrAgt")
    public void setDbtrAgt(DbtrAgt dbtrAgt) {
        this.dbtrAgt = dbtrAgt;
    }

    @XmlElement(name="Dbtr")
    public void setDbtr(Dbtr dbtr) {
        this.dbtr = dbtr;
    }

    @XmlElement(name="DbtrAcct")
    public void setDbtrAcct(DbtrAcct dbtrAcct) {
        this.dbtrAcct = dbtrAcct;
    }

    @XmlElement(name="RmtInf")
    public void setRmtInf(String rmtInf) {
        this.rmtInf = rmtInf;
    }
}
