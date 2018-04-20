/*** CLEAR **/
drop table mission;
drop table intervenant;
drop type mission_type;
drop type intervenant_type;


/************ EXO 1 ************/
/** 1 **/
CREATE OR REPLACE TYPE Intervenant_type AS OBJECT (
  mat integer,
  nom varchar(64),
  prenom varchar(64),
  qualification varchar(64),
  tarifApplique number
);
/

CREATE OR REPLACE TYPE mission_type AS OBJECT (
  code varchar(64),
  intitule varchar(64),
  nbJours integer,
  intervenant ref intervenant_type
);
/

/** 2 **/
CREATE TABLE intervenant of intervenant_type;
CREATE TABLE mission of mission_type;

/** 3 **/
INSERT INTO intervenant VALUES (2516, 'Dupont', 'Pierre', 'Développeur', 550);
INSERT INTO intervenant VALUES (7655, 'Henri', 'Jacques', 'Consultant', 990.90);
INSERT INTO intervenant VALUES (7687, 'Triolet', 'Elsa', 'Consultant', 1029.00);

/** 4 **/
INSERT INTO mission VALUES (mission_type('Varalpain033', 'Etude technique du passage de PEL en CEL', 54, 
(SELECT REF(i) from intervenant i where i.mat = 7655)));
INSERT INTO mission VALUES (mission_type('Armoni002', 'Prise de contact avec le Directeur', 2, 
(SELECT REF(i) from intervenant i where i.mat = 1000)));

/** 5 **/
INSERT INTO intervenant VALUES (1000, 'Michelin', 'Philippe', 'Directeur', 3000);

/** 6 **/
SELECT REF(i) from intervenant i where i.mat = 1000;

/** 10 **/
UPDATE mission SET intervenant = (SELECT REF(i) from intervenant i where i.mat = 1000) WHERE code = 'Armoni002';

/** 11 **/
DESC mission;

SELECT * FROM mission;

/** 12 **/
SELECT code, (nbjours * deref(intervenant).tarifApplique) cout FROM mission;

/** 13 **/
CREATE OR REPLACE PROCEDURE addDays(id mission.code%TYPE, n mission.nbjours%TYPE) IS
  BEGIN
    UPDATE mission 
    SET nbjours = nbjours + n
    WHERE code = id;
  END;
/

/* ESSAI */
execute addDays('Armoni002', 3);

/** 14 **/
UPDATE intervenant set tarifApplique = 2800 where nom = 'Michelin' and prenom = 'Philippe';

/** 15 **/
DELETE FROM intervenant where nom = 'Michelin' and prenom = 'Philippe';


/** CLEAR **/
DROP TABLE projet;
DROP TYPE missions_type;

/************ EXO 2 ************/
/** 1 **/
CREATE OR REPLACE TYPE missions_type AS TABLE OF mission_type;
/

/** 2 **/
CREATE TABLE projet (
  nom varchar(64) primary key, 
  dateDebut date,
  dateFin date,
  lesMissions missions_type
) NESTED TABLE lesMissions STORE AS missionsTable;

/** 3 **/
INSERT INTO projet VALUES ('CA1', '12/12/2009', '13/07/2011',
  missions_type(
    mission_type('SFG', 'Spéficifications générales', 30, (select ref(i) from intervenant i where mat=7655)),
    mission_type('SFD', 'Spéficifications détaillées', 60, (select ref(i) from intervenant i where mat=2516))
  )
);

/** 4 **/
desc projet;
select nom, dateDebut, dateFin from projet where nom='CA1';

/** 5 **/
select m.* from projet p, table(p.lesMissions) m where p.nom = 'CA1';

/** 6 **/
select m.code, m.intitule, m.nbjours, deref(m.intervenant).nom nom, deref(m.intervenant).qualification qualification
from projet p, table(p.lesMissions) m where p.nom = 'CA1';

/** 7 **/
select p.nom, p.dateFin, m.code, m.nbjours, deref(m.intervenant).nom nom_int, deref(m.intervenant).qualification qualif_int
from projet p, table(p.lesMissions) m where p.nom = 'CA1';

