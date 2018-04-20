/** 1 **/
CREATE OR REPLACE TRIGGER TRIGGER_EX1
  BEFORE INSERT ON CARON_MALADE
  FOR EACH ROW
DECLARE
  id_max integer;
BEGIN
  SELECT max(n_malade) INTO id_max FROM CARON_MALADE;
  :NEW.n_malade := id_max + 1;
  :NEW.nom_malade := LOWER(:NEW.nom_malade);
  :NEW.prenom_malade := LOWER(:NEW.prenom_malade);
END;
/


insert into caron_malade VALUES(5410,'Henri','Jacques','76 rue de la Paix, Paris','0145897622','0612121212');
insert into caron_malade VALUES(4000,'Sam','Flynn','none','0145889966','0612121213');


/** 2 **/
CREATE OR REPLACE TRIGGER TRIGGER_EX2
  BEFORE INSERT ON CARON_CONSULTATION
  FOR EACH ROW
BEGIN
  IF :NEW.prescription IS NULL AND :NEW.examen IS NULL
  THEN :NEW.examen := 'Visite de routine';
  END IF;
END;
/

insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(146, 5780, '19-11-2011','09');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(146, 5780, '20-11-2011','11');


/** 3 **/
CREATE TABLE CARON_ARCHIVE_CONSULTATION
  AS (SELECT * FROM CARON_CONSULTATION WHERE h_consult IS NULL);

CREATE OR REPLACE TRIGGER TRIGGER_EX3
  AFTER DELETE ON CARON_CONSULTATION
  FOR EACH ROW
BEGIN
  IF :OLD.date_consult < current_date THEN
    INSERT INTO CARON_ARCHIVE_CONSULTATION
    VALUES(:OLD.n_med, :OLD.n_malade, :OLD.date_consult, :OLD.h_consult, :OLD.prescription, :OLD.examen);
  END IF;
END;
/


/** 4 **/
/*CREATE OR REPLACE TRIGGER TRIGGER_EX4 
BEFORE INSERT OR UPDATE OR DELETE ON CARON_ARCHIVE_CONSULTATION
DECLARE 
  unauthorized_operation EXCEPTION;
BEGIN
  RAISE unauthorized_operation;
EXCEPTION
  WHEN unauthorized_operation THEN
  DBMS_OUTPUT.PUT_LINE('Opération de modification non autorisée');
END;
/*/

CREATE OR REPLACE TRIGGER TRIGGER_EX4 
BEFORE UPDATE OR DELETE ON CARON_ARCHIVE_CONSULTATION
BEGIN
  raise_application_error(-20121, 'Opération de modification interdite');
END;
/

insert into caron_archive_consultation(n_med,n_malade,date_consult,h_consult) values(123,5780,'18-10-2011','09');


/** 5a **/
CREATE OR REPLACE TRIGGER TRIGGER_EX5A
BEFORE INSERT OR UPDATE ON CARON_MEDECIN
FOR EACH ROW
BEGIN
  IF NOT REGEXP_LIKE(:NEW.identite_med, '^[A-Z][a-z]* [A-Z]+$','c')
  THEN raise_application_error(-20122, 'Format incohérent');
  END IF;
END;
/

INSERT INTO CARON_MEDECIN VALUES(199,'Henri DECLIN','0149884461','henri.declin@free.fr','Alexandre TARAUD','CCA');
INSERT INTO CARON_MEDECIN VALUES(200,'HenRi DecLIN','0149884461','henri.declin@free.fr','Alexandre TARAUD','CCA');


/** 5b **/
CREATE OR REPLACE TRIGGER TRIGGER_EX5B
BEFORE INSERT OR UPDATE ON CARON_MEDECIN
FOR EACH ROW
BEGIN
  IF NOT REGEXP_LIKE(:NEW.adr_elec_med, '^.+@.+\..+$','c')
  THEN raise_application_error(-20122, 'Format incohérent');
  END IF;
END;
/

INSERT INTO CARON_MEDECIN VALUES(201,'Henri DECLIN','0149884461','henri.declin@free.fr','Alexandre TARAUD','CCA');
INSERT INTO CARON_MEDECIN VALUES(202,'Henri DECLIN','0149884461','henri.declinfreefr','Alexandre TARAUD','CCA');
INSERT INTO CARON_MEDECIN VALUES(203,'Henri DECLIN','0149884461','henri.declinfree.fr','Alexandre TARAUD','CCA');


/** 6 **/
ALTER TABLE CARON_MEDECIN ADD prime integer;
UPDATE CARON_MEDECIN SET prime = 0;


CREATE OR REPLACE TRIGGER TRIGGER_EX6
  AFTER DELETE ON CARON_CONSULTATION
  FOR EACH ROW
DECLARE
  prime_incr integer;
BEGIN
  prime_incr := 23;
  IF :OLD.date_consult < current_date THEN
    UPDATE CARON_MEDECIN
    SET prime = prime + prime_incr
    WHERE n_med = :OLD.n_med;
  END IF;
END;
/



CREATE OR REPLACE TRIGGER TRIGGER_EX7
  BEFORE INSERT ON CARON_MED_PLAGE_CONSULT
  FOR EACH ROW
DECLARE
  consult_nb integer;
BEGIN
  SELECT count(*) INTO consult_nb FROM CARON_MED_PLAGE_CONSULT
  WHERE jour_sem = :NEW.jour_sem
  AND (((h_deb <= :NEW.h_deb) AND (:NEW.h_deb < h_fin))
  OR ((h_deb < :NEW.h_fin) AND (:NEW.h_fin <= h_fin)));
  
  IF consult_nb >= 3
  THEN raise_application_error(-20123, 'Aucun salle n est disponible pour ce créneau');
  END IF;
END;
/

INSERT INTO CARON_MED_PLAGE_CONSULT VALUES (107, 'mercredi', 
