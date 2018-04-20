package urouen.sepa.model.dao;

import urouen.sepa.model.transaction.Transaction;
import urouen.sepa.model.transaction.types.DBID;

import java.util.List;
import java.util.Map;

/**
 * Définit un DAO (Data Access Objet) pour la gestion de la
 * persistance des transactions.
 *
 * Ce DAO gèrera les différentes transactions enregistrées afin
 * de pouvoir les manipuler plus facilement lors des opérations
 * de lecture/écriture avec le système de persistance utilisé
 */
public interface TransactionDAO {
    // INSTANCE
    /** Renvoie l'implantation utilisée par l'application */
    public static final TransactionDAO INSTANCE = new TransactionDAOImpl();


    // REQUETES
    /**
     * Renvoie un ensemble d'informations à propos des différentes
     * transactions enregistrées :
     *      lastId : Renvoie le dernier identifiant utilisé pour l'insertion
     *      total : Le montant total de toutes las transactions utilisées
     *      count : Le nombre de transaction enregistrées dans ce service
     *
     * @return La table des informations correspondantes
     */
    Map<String, String> stats();

    /**
     * Renvoie la transaction enregistrée correspondant au numéro
     * d'identification fourni en paramètre
     *
     * @param id L'identifiant utilisé pour désigner cette transaction
     *             dans le système de persistance.
     *
     * @return La transaction correspondante si l'identifiant existe,
     *         null sinon.
     */
    Transaction get(String id);

    /**
     * Renvoie toutes les transactions enregistrées.
     *
     * @return La liste des transactions correspondante.
     */
    List<Transaction> getAll();

    // COMMANDES
    /**
     * Enregistre une nouvelle transaction auprès du service de persistance
     *
     * @param transaction La transaction
     * @throws IllegalArgumentException L'identifiant de la transaction
     *         est déjà utilisé dans la base de données
     */
    void add(Transaction transaction) throws Exception;

    /**
     * Supprime une transaction enregistrée auprès du service de persistance
     *
     * @param id L'identifiant utilisé pour désigner cette transaction
     *             dans le système de persistance.
     * @throws IllegalArgumentException L'identifiant de la transaction
     *         n'est pas utilisé dans la base de données
     */
    void remove(String id);
}
