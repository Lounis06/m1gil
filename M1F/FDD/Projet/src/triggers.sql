/* 
 * triggers.sql : Définit les différents triggers associés
 * au modèle de données.
 *
 */


/** ---- Ajout automatique des champs de clé primaire (auto-increment) ---- **/
/* -- 1 : Laboratoire -- */
CREATE SEQUENCE idlab_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER laboratoire_keygen
BEFORE INSERT ON laboratoire FOR EACH ROW
BEGIN
	SELECT idlab_sequence.nextval INTO :NEW.idlab FROM dual;
END;
/

/* -- 2 : Medecin -- */
CREATE SEQUENCE iddoc_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER medecin_keygen
BEFORE INSERT ON medecin FOR EACH ROW
BEGIN
	SELECT iddoc_sequence.nextval INTO :NEW.iddoc FROM dual;
END;
/

/* -- 3 : Symptome -- */
CREATE SEQUENCE idsym_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER symptome_keygen
BEFORE INSERT ON symptome FOR EACH ROW
BEGIN
	SELECT idsym_sequence.nextval INTO :NEW.idsym FROM dual;
END;
/

/* -- 4 : Maladie -- */
CREATE SEQUENCE idmal_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER maladie_keygen
BEFORE INSERT ON maladie FOR EACH ROW
BEGIN
	SELECT idmal_sequence.nextval INTO :NEW.idmal FROM dual;
END;
/

/* -- 5 : Effet indésirable -- */
CREATE SEQUENCE ideff_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER effet_indesirable_keygen
BEFORE INSERT ON effet_indesirable FOR EACH ROW
BEGIN
	SELECT ideff_sequence.nextval INTO :NEW.ideff FROM dual;
END;
/

/* -- 6 : Substance active -- */
CREATE SEQUENCE idsub_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER substance_active_keygen
BEFORE INSERT ON substance_active FOR EACH ROW
BEGIN
	SELECT idsub_sequence.nextval INTO :NEW.idsub FROM dual;
END;
/

/* -- 7 : Caractéristique -- */
CREATE SEQUENCE idcar_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER caracteristique_keygen
BEFORE INSERT ON caracteristique FOR EACH ROW
BEGIN
	SELECT idcar_sequence.nextval INTO :NEW.idcar FROM dual;
END;
/

/* -- 8 : Caractéristique -- */
CREATE SEQUENCE idint_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER interaction_keygen
BEFORE INSERT ON interaction FOR EACH ROW
BEGIN
	SELECT idint_sequence.nextval INTO :NEW.idint FROM dual;
END;
/

/* -- 9 : Médicament -- */
CREATE SEQUENCE idmed_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER medicament_keygen
BEFORE INSERT ON medicament FOR EACH ROW
BEGIN
	SELECT idmed_sequence.nextval INTO :NEW.idmed FROM dual;
END;
/

/* -- 10 : Patient -- */
CREATE SEQUENCE idpat_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER patient_keygen
BEFORE INSERT ON patient FOR EACH ROW
BEGIN
	SELECT idpat_sequence.nextval INTO :NEW.idpat FROM dual;
END;
/

/* -- 11 : Developpement -- */
CREATE SEQUENCE iddvt_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER developpement_keygen
BEFORE INSERT ON developpement FOR EACH ROW
BEGIN
	SELECT iddvt_sequence.nextval INTO :NEW.iddvt FROM dual;
END;
/

/* -- 12 : Consultation -- */
CREATE SEQUENCE idcon_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER consultation_keygen
BEFORE INSERT ON consultation FOR EACH ROW
BEGIN
	SELECT idcon_sequence.nextval INTO :NEW.idcon FROM dual;
END;
/

/* -- 13 : Traitement -- */
CREATE SEQUENCE idtra_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER traitement_keygen
BEFORE INSERT ON traitement FOR EACH ROW
BEGIN
	SELECT idtra_sequence.nextval INTO :NEW.idtra FROM dual;
END;
/