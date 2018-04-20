/* Suppression des tables existantes */
drop table developpement;
drop table medicament;
drop table substance_active;
drop table effet_indesirable;
drop table maladie;
drop table symptome;
drop table medecin;
drop table laboratoire;

/* Suppression des types existants */
drop type Liste_cles_type;
drop type Laboratoire_type;
drop type Medecin_type;
drop type Symptome_type;
drop type Maladie_type;
drop type Effet_indesirable_type;
drop type Substance_active_type;
drop type Interaction_type;
drop type Liste_interactions_type;
drop type Medicament_type;
drop type Developpement_type;


/* Création des types */
/* -- Types utilitaires -- */
create or replace type Liste_cles_type as table of integer;
/

/* -- 1 : Laboratoire -- */
create or replace type Laboratoire_type as object (
	idlab integer,
	nom varchar(64)
);
/
create table laboratoire of Laboratoire_type;
alter table laboratoire add 
	constraint laboratoire_pk primary key (idlab);

/* -- 2 : Medecin -- */
create or replace type Medecin_type as object (
	iddoc integer,
	nom varchar(64),
	prenom varchar(64)
);
/
create table medecin of Medecin_type;
alter table medecin add constraint medecin_pk primary key (iddoc);

/* -- 3 : Symptome -- */
create or replace type Symptome_type as object (
	idsym integer,
	label varchar(64)
);
/
create table symptome of Symptome_type;
alter table symptome add constraint symptome_pk primary key (idsym);

/* -- 4 : Maladie -- */
create or replace type Maladie_type as object (
	idmal integer,
	idparent integer,
	nom varchar(64),
	symptomes Liste_cles_type
);
/
create table maladie of Maladie_type 
	nested table symptomes store as maladieSymptomesTable;
alter table maladie add constraint maladie_pk primary key (idmal);

/* -- 5 : Effet indésirable -- */
create or replace type Effet_indesirable_type as object (
	ideff integer,
	nom varchar(64),
	description varchar(255)
);
/
create table effet_indesirable of Effet_indesirable_type;
alter table effet_indesirable add 
	constraint effet_pk primary key (ideff);

/* -- 6 : Substance active -- */
create or replace type Substance_active_type as object (
	idsub integer,
	idparent integer,
	nom varchar(64),
	effets Liste_cles_type
);
/
create table substance_active of Substance_active_type
	nested table effets store as substanceEffetsTable;
alter table substance_active add 
	constraint substance_active_pk primary key (idsub);

/* -- 7 : Caracteristique -- */


/* -- 8 : Medicament -- */
/* Définition d'une interaction médicamenteuse  */
create or replace type Interaction_type as object (
	idmed integer,
	ideff integer
);
/
create or replace type Liste_interactions_type 
	as table of Interaction_type;
/

/* Définition du type, puis de la table */
create or replace type Medicament_type as object (
	idmed integer,
	nom varchar(64),
	composition Liste_cles_type,
	incompatibilites Liste_cles_type,
	maladies_ciblees Liste_cles_type,
	effets Liste_cles_type,
	interactions Liste_interactions_type
);
/
create table medicament of Medicament_type
	nested table composition store as compositionTable,
	nested table incompatibilites store as incompatibilitesTable,
	nested table maladies_ciblees store as medicamentMaladiesTable,
	nested table effets store as medicamentEffetsTable,
	nested table interactions store as interactionsTable;
alter table medicament add
	constraint medicament_pk primary key (idmed);

/* -- 10 : Developpement -- */
create or replace type Developpement_type as object (
	iddvt integer,
	idmed integer,
	idlab integer,
	equipe Liste_cles_type
);
/

create table developpement of Developpement_type
	nested table equipe store as equipeTable;
/
alter table developpement add
	constraint developpement_pk primary key (iddvt);
alter table developpement add
	constraint developpement_fk_med
	foreign key (idmed) references medicament(idmed);
alter table developpement add
	constraint developpement_fk_lab
	foreign key (idlab) references laboratoire(idlab);


/* -- 12 : Consultation -- */
create or replace type Observations_type as table of varchar(255);
/
create or replace type Consultation_type as object (
	idcon integer,
	idpat integer,
	iddoc integer,
	symptomes Liste_cles_type,
	maladies Liste_cles_type,
	observations Observations_type
);
/

create table consultation of Consultation_type
	nested table symptomes store as consultationSymptomesTable,
	nested table maladies store as consultationMaladiesTable,
	nested table observations store as observationsTable;
alter table consultation add
	constraint consultation_pk primary key (idcon);
alter table consultation add
	constraint consultation_fk_pat
	foreign key (idpat) references patient(idpat);
alter table consultation add
	constraint consultation_fk_doc
	foreign key (iddoc) references medecin(iddoc);