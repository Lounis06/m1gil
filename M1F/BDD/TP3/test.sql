CREATE OR REPLACE FUNCTION consults(id CARON_MEDECIN.n_med%TYPE, mois integer, annee integer)
  DECLARE
    nb INTEGER;
  BEGIN
    CREATE VIEW TEMP_EX3 AS SELECT n_med, EXTRACT(MONTH FROM date_consult) mo, EXTRACT(YEAR FROM date_consult) y
    FROM CARON_CONSULTATION WITH READ ONLY;
    
    SELECT COUNT(*) INTO nb FROM TEMP_EX3 
    WHERE n_med = id AND mo = mois AND y = annee;
    
    DROP VIEW TEMP_EX3;
    
    RETURN nb;
  END
/

/** Meilleure version **/
CREATE OR REPLACE FUNCTION consults(id integer, mois integer, annee integer)
  RETURN INTEGER IS nb INTEGER;
  BEGIN
    SELECT COUNT(*) INTO nb FROM CARON_CONSULTATION
    WHERE n_med = id AND EXTRACT(MONTH FROM date_consult) = mois 
    AND EXTRACT(YEAR FROM date_consult) = annee;
    RETURN nb;
  END;
/

/** TEST **/
DECLARE
  puph_inc REAL := 1.08;
  patt_inc REAL := 1.05;
  other_inc REAL := 1.02;
BEGIN
  UPDATE CARON_MEDECIN
  SET SALAIRE = SALAIRE * puph_inc
  WHERE STATUT = 'PUPH';
  
  UPDATE CARON_MEDECIN
  SET SALAIRE = SALAIRE * patt_inc
  WHERE STATUT = 'PATT';
  
  UPDATE CARON_MEDECIN
  SET SALAIRE = SALAIRE * other_inc
  WHERE STATUT <> 'PUPH' AND STATUT <> 'PATT';
END;
/
