/************ EXO 1 ************/
/** 1 **/
CREATE OR REPLACE TYPE qualif_type AS OBJECT (
    qualif varchar(64),
    tarif number
);
/

/** 2 **/
CREATE TABLE QUALIFICATION OF qualif_type;

INSERT INTO QUALIFICATION VALUES ('Développeur', 500);
INSERT INTO QUALIFICATION VALUES ('Analyse', 700);
INSERT INTO QUALIFICATION VALUES ('Chef de projet', 900);
INSERT INTO QUALIFICATION VALUES ('Consultant', 1000);
INSERT INTO QUALIFICATION VALUES ('Directeur commercial', 1300);

/** 3 **/
desc qualification;
desc qualif_type;

select * from qualification;

/** 4 **/
select * from qualification order by qualif;

/** 5 **/
select AVG(TARIF) AS Tarif_moyen from qualification;

/** 6 **/
update qualification set tarif = tarif * 1.025;
select * from qualification;


/************ EXO 1 ************/
/** 1 **/
CREATE TABLE QUALIFICATION (
    valeur qualif_type
);

INSERT INTO QUALIFICATION VALUES (qualif_type('Développeur', 500));
INSERT INTO QUALIFICATION VALUES (qualif_type('Analyste', 700));
INSERT INTO QUALIFICATION VALUES (qualif_type('Chef de projet', 900));
INSERT INTO QUALIFICATION VALUES (qualif_type('Consultant', 1000));
INSERT INTO QUALIFICATION VALUES (qualif_type('Directeur commercial', 1300));

/** 2 **/
desc qualification;

select * from qualification;

select min(q.valeur.tarif) as tarif_min, max(q.valeur.tarif) as tarif_max from qualification q;

/** 3 **/
update qualification q set q.valeur.tarif = q.valeur.tarif * 1.1
where q.valeur.qualif = 'Analyste';


/************ EXO 1 ************/
/** 1 **/
CREATE TABLE INTERVENANT (
  mat integer primary key not null,
  nom varchar(64),
  prenom varchar(64),
  qualif qualif_type
);

/** 2 **/
desc intervenant;

/** 3 **/
INSERT INTO INTERVENANT VALUES (2516, 'Dupont', 'Pierre', qualif_type('Développeur', 550));
INSERT INTO INTERVENANT VALUES (7655, 'Henri', 'Jacques', qualif_type('Consultant', 990.90));
INSERT INTO INTERVENANT VALUES (7687, 'Triolet', 'Elsa', qualif_type('Consultant', 1029.00));

/** 4 **/
select nom, i.qualif.tarif as tarif from intervenant i;

/** 5 **/
select mat, nom from intervenant i where i.qualif.qualif = 'Consultant';

/** 6 **/
select mat, nom, (i.qualif.tarif - q.valeur.tarif) as ecart 
from intervenant i, qualification q
where i.qualif.qualif = 'Consultant' 
and q.valeur.qualif = i.qualif.qualif
and i.qualif.tarif >= q.valeur.tarif;
