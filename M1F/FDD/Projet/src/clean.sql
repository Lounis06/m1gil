/*
 * clean.sql : Contient les requêtes de suppression (dans l'ordre
 * d'exécution), utilisables pour supprimer le schéma de données existant.
 *
 */

/* ==== Suppression des méthodes définies ==== */
drop function proposer_traitement;
drop function debusquer_maladie;
drop function med_doc_labo;
drop function med_doc_equipe;
drop function effets_probables;
drop function idees_medicaments;
drop function identiques;
drop function obtenir_interactions;
drop function obtenir_medicaments_sensibles;
drop function obtenir_nb_effets;
drop function obtenir_effets;
drop function obtenir_suivi;
drop procedure prescription;


/* ==== Suppression des triggers ==== */
drop trigger laboratoire_keygen;
drop trigger medecin_keygen;
drop trigger symptome_keygen;
drop trigger maladie_keygen;
drop trigger effet_indesirable_keygen;
drop trigger substance_active_keygen;
drop trigger caracteristique_keygen;
drop trigger interaction_keygen;
drop trigger medicament_keygen;
drop trigger patient_keygen;
drop trigger developpement_keygen;
drop trigger consultation_keygen;
drop trigger traitement_keygen;


/* ==== Suppression des séquences ==== */
drop sequence idlab_sequence;
drop sequence iddoc_sequence;
drop sequence idsym_sequence;
drop sequence idmal_sequence;
drop sequence ideff_sequence;
drop sequence idsub_sequence;
drop sequence idcar_sequence;
drop sequence idint_sequence;
drop sequence idmed_sequence;
drop sequence idpat_sequence;
drop sequence iddvt_sequence;
drop sequence idcon_sequence;
drop sequence idtra_sequence;


/* ==== Suppression des tables existantes ==== */
drop table traitement FORCE;
drop table consultation FORCE;
drop table developpement FORCE;
drop table patient FORCE;
drop table medicament FORCE;
drop table interaction FORCE;
drop table caracteristique FORCE;
drop table substance_active FORCE;
drop table effet_indesirable FORCE;
drop table maladie FORCE;
drop table symptome FORCE;
drop table medecin FORCE;
drop table laboratoire FORCE;


/* ==== Suppression des types existants ==== */
drop type Liste_cles_type FORCE;
drop type Laboratoire_type FORCE;
drop type Medecin_type FORCE;
drop type Symptome_type FORCE;
drop type Maladie_type FORCE;
drop type Effets_indesirables_type FORCE;
drop type Effet_indesirable_type FORCE;
drop type Substance_active_type FORCE;
drop type Caracteristique_type FORCE;
drop type Medicament_type FORCE;
drop type Interaction_type FORCE;
drop type Patient_type FORCE;
drop type Developpement_type FORCE;
drop type Consultation_type FORCE;
drop type Observations_type FORCE;
drop type Traitements_type FORCE;
drop type Traitement_type FORCE;


