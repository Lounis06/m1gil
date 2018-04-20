/* Table des qualifications */

CREATE OR REPLACE TYPE qualif_type AS OBJECT (
    qualif varchar(64),
    tarif number
);
/

CREATE TABLE QUALIFICATION OF qualif_type;

INSERT INTO QUALIFICATION VALUES ('Développeur', 500);
INSERT INTO QUALIFICATION VALUES ('Analyse', 700);
INSERT INTO QUALIFICATION VALUES ('Chef de projet', 900);
INSERT INTO QUALIFICATION VALUES ('Consultant', 1000);
INSERT INTO QUALIFICATION VALUES ('Directeur commercial', 1300);


/* Table des intervenants */

CREATE TABLE INTERVENANT (
  mat integer primary key not null,
  nom varchar(64),
  prenom varchar(64),
  qualif qualif_type
);

INSERT INTO INTERVENANT VALUES (2516, 'Dupont', 'Pierre', qualif_type('Développeur', 550));
INSERT INTO INTERVENANT VALUES (7655, 'Henri', 'Jacques', qualif_type('Consultant', 990.90));
INSERT INTO INTERVENANT VALUES (7687, 'Triolet', 'Elsa', qualif_type('Consultant', 1029.00));
