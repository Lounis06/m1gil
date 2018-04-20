package urouen.sepa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urouen.sepa.model.dao.TransactionDAO;
import urouen.sepa.model.transaction.Transaction;

import java.util.Map;

@RestController
public class SepaController {
    // REQUETES GEREES
    /**
     * Gère l'entrée /
     * Renvoie un message d'accueil comportant le nom des auteurs de l'application.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String home() {
        return "Bienvenue sur SEPA ! Ce service a été réalisé par CARON Franck et POMMIER Grégoire";
    }

    /**
     * Gère l'entrée /stats
     * Renvoie des statistiques sur les transactions enregistrées par le service
     */
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public @ResponseBody String stats() {
        Map<String, String> stats = TransactionDAO.INSTANCE.stats();
        StringBuilder content = new StringBuilder(); {
            content.append("<stats>"); {
                content.append("<total>").append(stats.get("total")).append("</total>");
                content.append("<count>").append(stats.get("count")).append("</count>");
            }
            content.append("</stats>");
        }

        return content.toString();
    }

    /**
     * Gère l'entrée /resume
     * Renvoie un résumé de chaque transaction enregistrée par le service, en
     * renseignant leurs identifiants, leurs montants et leurs date de réalisation.
     */
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public @ResponseBody String resume() {
        StringBuilder content = new StringBuilder(); {
            content.append("<resume>"); {
                for (Transaction tr : TransactionDAO.INSTANCE.getAll()) {
                    content.append("<Transaction>"); {
                        content.append("<Num>").append(tr.getDBID()).append("</Num>");
                        content.append("<Identifiant>").append(tr.getPmtInf()).append("</Identifiant>");
                        content.append("<Montant>").append(tr.getInstdAmt().getAmount()).append("</Montant>");
                        content.append("<Date>").append(tr.getDrctDbtTx().getDate()).append("</Date>");
                    }
                    content.append("</Transaction>");
                }
            }
            content.append("</resume>");
        }

        return content.toString();
    }

    /**
     * Gère l'entrée /trx/{id}
     * Renvoie le détail des informations
     */
    @RequestMapping(value = "/trx/{id}", method = RequestMethod.GET)
    public @ResponseBody Transaction trx(@PathVariable("id") String id) {
        return TransactionDAO.INSTANCE.get(id);
    }

    /**
     * Gère l'entrée /depot
     * Ajoute la transaction envoyée dans la requête à l'ensemble
     * des transactions enregistrées.
     */
    @RequestMapping(value = "/depot", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Void> trx(@RequestBody Transaction transaction) {
        try {
            TransactionDAO.INSTANCE.add(transaction);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
