package urouen.sepa.model.dao;

import com.sun.corba.se.spi.activation.Server;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.MetaModelException;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.query.CompiledQuery;
import org.apache.metamodel.query.FunctionType;
import org.apache.metamodel.query.Query;
import org.apache.metamodel.query.SelectItem;
import org.apache.metamodel.query.builder.InitFromBuilder;
import org.apache.metamodel.schema.Column;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.apache.metamodel.xml.XmlSaxDataContext;
import org.apache.metamodel.xml.XmlSaxTableDef;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;
import urouen.sepa.model.transaction.*;
import urouen.sepa.model.transaction.types.*;
import util.Resources;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Définit un DAO en réalisant la persistance au moyen
 * d'un fichier XML.
 */
class TransactionDAOImpl implements TransactionDAO {
    // CONSTANTE
    /** Le chemin du fichier .xml utilisé pour stocker les transactions */
    public static final String FILEPATH = Resources.DIRECTORY + "sepa.xml";

    // REQUETE
    @Override
    public Map<String, String> stats() {
        // Chargement de la table
        DataContext context = getContext();
        Table table = context.getDefaultSchema().getTable(0);

        // Requête d'obtention de la transaction
        DataSet result = context.query()
                .from(table)
                .select(FunctionType.MAX, "/DBID")
                .select(FunctionType.SUM, "/InstdAmt/amount")
                .selectCount()
                .execute();

        // Récupération de l'unique ligne associée.
        Row row = result.toRows().get(0);
        if (row == null) {
            return null;
        }

        // Complétion du tableau des statistiques
        Map<String, String> stats = new HashMap<>(); {
            stats.put("lastId", (String) row.getValue(0));
            stats.put("total", String.valueOf(row.getValue(1)));
            stats.put("count", String.valueOf(row.getValue(2)));
        }
        return stats;
    }

    @Override
    public Transaction get(String id) {
        // Chargement de la table
        DataContext context = getContext();
        Table table = context.getDefaultSchema().getTable(0);

        // Requête d'obtention de la transaction
        DataSet result = context.query()
                .from(table)
                .selectAll()
                .where("/DBID").eq(id)
                .execute();

        // Récupération de l'unique transaction associé.
        List<Row> transactionList = result.toRows();
        if (transactionList.size() == 1) {
            return toTransaction(table, transactionList.get(0));
        }

        // Dans le cas contraire (récupération echouée), on renvoie null.
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        // Chargement de la table
        DataContext context = getContext();
        Table table = context.getDefaultSchema().getTable(0);

        // Requête d'obtention de la transaction
        DataSet result = context.query()
                .from(table)
                .selectAll()
                .execute();

        // Récupération des transactions associées.
        List<Transaction> transactionList = new ArrayList<>();
        for (Row row : result.toRows()) {
            transactionList.add(toTransaction(table, row));
        }

        return transactionList;
    }

    // COMMANDE
    @Override
    public void add(Transaction transaction) {
        try {
            // Construction de l'objet document, correspondant au fichier XML
            File file = new File(FILEPATH);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(file);

            // Récupération du noeud, où ajouter le nouvel élément
            Node destNode = document.getElementsByTagName("p:CstmrDrctDbtInitn").item(0);

            // Création du contexte JAXB pour "marshaller" l'objet Transaction
            JAXBContext context = JAXBContext.newInstance(Transaction.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Construction de l'élément XML, contenant la transaction
            m.marshal(transaction, destNode);

            // Ecriture le l'élément dans le fichier XML.
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new AssertionError("Erreur lors de l'insertion de la transaction");
        }
    }

    @Override
    public void remove(String id) {

    }

    // OUTILS
    /**
     * Renvoie le DataContext associé à l'extraction des informations
     * requises par le DAO, deupis le fichier .xml où sont stockées les
     * transactions.
     *
     * @return Une table de données correspondant à une vue utilisable
     *         du fichier .xml contenant les transactions.
     */
    private DataContext getContext() {
        // Le chemin désignant l'élément transaction depuis la racine
        String rowXPath = "/p:CstmrDrctDbtInitn/DrctDbtTxInf";

        /*
         * Définition du schéma de la table :
         *  - Le premier paramètre désigne chemin menant au type des éléments XMLs ciblés
         *  - Le second correspond à l'ensemble des chemins de tous les sous-éléments
         *  devant être décrits, en tant que colonnes de cette table
         */
        XmlSaxTableDef tableDef = new XmlSaxTableDef(
                rowXPath,
                new String[]{
                        rowXPath + "/DBID",
                        rowXPath + "/PmtInf",
                        rowXPath + "/InstdAmt/amount",
                        rowXPath + "/InstdAmt@Ccy",
                        rowXPath + "/DrctDbtTx/MndtId",
                        rowXPath + "/DrctDbtTx/DtOfSgntr",
                        rowXPath + "/DbtrAgt/BIC",
                        rowXPath + "/DbtrAgt/Othr/Id",
                        rowXPath + "/Dbtr/Nm",
                        rowXPath + "/DbtrAcct/IBAN",
                        rowXPath + "/DbtrAcct/PrvtId/Othr/Id",
                        rowXPath + "/RmtInf"
                }
        );

        // On renvoie le contexte de données correspondant
        return new XmlSaxDataContext(new File(FILEPATH), tableDef);
    }

    /**
     * Convertit une ligne de données correspondant à une transaction
     * en un objet Transaction.
     */
    private Transaction toTransaction(Table table, Row row) {
        // Les variables de lecture d'objets
        Object value = null, value2 = null;

        // Création de la transaction
        Transaction t = new Transaction(); {
            // DBID
            value = row.getValue(table.getColumnByName("/DBID"));
            if (value == null) {
                return null;
            }
            t.setDBID(new DBID((String) value));

            // PmtInf
            value = row.getValue(table.getColumnByName("/PmtInf"));
            if (value == null) {
                return null;
            }
            t.setPmtInf((String) value);

            // InstdAmt
            value = row.getValue(table.getColumnByName("/InstdAmt/amount"));
            value2 = row.getValue(table.getColumnByName("/InstdAmt@Ccy"));
            if (value == null && value2 == null) {
                return null;
            }
            t.setInstdAmt(new InstdAmt(new Amount(Double.valueOf((String) value)),
                    new Ccy((String) value2)));

            // DrctDbtTx
            // TODO Gérer DrctDbtTx, notamment avec la date...
            /* value = row.getValue(table.getColumnByName("/DrctDbtTx/MndtId"));
            value2 = row.getValue(table.getColumnByName("/DrctDbtTx/DtOfSgntr"))
            if (value == null && value2 == null) {
                return null;
            }*/

            // DbtrAgt
            value = row.getValue(table.getColumnByName("/DbtrAgt/BIC"));
            if (value == null) {
                value = row.getValue(table.getColumnByName("/DbtrAgt/Othr/Id"));
                if (value == null) {
                    return null;
                }
                t.setDbtrAgt(new DbtrAgt(new Othr((String) value)));
            } else {
                t.setDbtrAgt(new DbtrAgt(new BIC((String) value)));
            }

            // Dbtr
            value = row.getValue(table.getColumnByName("/Dbtr/Nm"));
            if (value == null) {
                return null;
            }
            t.setDbtr(new Dbtr(new Max35Text((String) value)));

            // DbtrAcct
            value = row.getValue(table.getColumnByName("/DbtrAcct/IBAN"));
            if (value == null) {
                value = row.getValue(table.getColumnByName("/DbtrAcct/PrvtId/Othr/Id"));
                if (value == null) {
                    return null;
                }
                t.setDbtrAcct(new DbtrAcct(new PrvtId((String) value)));
            } else {
                t.setDbtrAcct(new DbtrAcct(new IBAN((String) value)));
            }

            // RmtInf
            value = row.getValue(table.getColumnByName("/RmtInf"));
            if (value == null) {
                return null;
            }
            t.setRmtInf((String) value);
        }

        return t;
    }

    /**
     * DEBUG ONLY
     * Méthode utilisée pour afficher le contenu du fichier XML.
     */
    public void print() {
        DataContext context = getContext();
        Table table = context.getDefaultSchema().getTable(0);

        for (int k = 0; k < table.getColumnNames().length; ++k) {
            System.out.print(table.getColumnNames()[k] + " ");
        }
        System.out.println();


        // Réalisation de la requête
        DataSet result = context.query()
                .from(table)
                .selectAll()
                .execute();

        for (Row row : result.toRows()) {
            for (Object o : row.getValues()) {
                System.out.print(o + " | ");
            }
            System.out.println();
        }
    }
}
