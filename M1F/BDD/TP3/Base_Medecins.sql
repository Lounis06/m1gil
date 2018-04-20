DROP TABLE caron_consultation;
DROP TABLE caron_med_plage_consult;
DROP TABLE caron_malade;
DROP TABLE caron_medecin;

CREATE TABLE caron_medecin (
       n_med integer constraint pk_medecin primary key,
       identite_med varchar2(30) not null,
       tel_med char(10),
       adr_elec_med varchar2(30),
       prof_serv varchar2(30) not null,
       statut varchar2(7) constraint dom_statut_medecin check(statut in ('PATTASS', 'PATT', 'PUPH', 'CCA')) not null
);

CREATE TABLE caron_med_plage_consult(
       n_med integer constraint fk_med_plage_consult_medecin references
       				caron_medecin(n_med) on delete cascade not deferrable,
       jour_sem varchar2(8) constraint dom_jour_sem_med_plage_consult check(jour_sem in (
       			'lundi','mardi', 'mercredi', 'jeudi', 'vendredi','samedi')),
       h_deb integer constraint dom_h_deb_med_plage_consult check(h_deb between 8 and 17) not null,
       h_fin integer constraint dom_h_fin_med_plage_consult check(h_fin between 11 and 19),
       constraint ck_med_plage_consult_hdeb_hfin check (h_deb < h_fin),
       constraint pk_med_plage_consult primary key (n_med, jour_sem)
);	

CREATE TABLE caron_malade(
       n_malade integer constraint cleprim_malade primary key,
       nom_malade varchar2(20) not null,
       prenom_malade varchar2(15) not null,
       adr_malade varchar2(256),
       tel_fixe_mal char(10),
       tel_mob_mal char(10),
       constraint ck_malade_nom check (nom_malade=lower(nom_malade)),
       constraint ck_malade_prenom check (prenom_malade=lower(prenom_malade))
);

CREATE TABLE caron_consultation(
       n_med integer constraint fk_consultation_medecin references caron_medecin(n_med),
       n_malade integer constraint fk_consultation_malade references
       			        caron_malade(n_malade) on delete cascade deferrable initially deferred,
       date_consult date,
       h_consult integer not null,
       prescription varchar2(128),
       examen varchar2(128),
       constraint pk_consultation primary key(n_med, n_malade, date_consult)
);	

-- remplissage médecin
insert into caron_medecin VALUES (104,'Jean MOREL','0149282592','jean.morel@free.fr','Florence ROUSSEAU','PATTASS');
insert into caron_medecin VALUES (123,'Alexandre BERNARD','0149282594','alex.bernard@yahoo.fr','Florence ROUSSEAU','PATT');
insert into caron_medecin VALUES (107,'Florence ROUSSEAU','0149282678','florence.rousseau@wanadoo.fr','Florence ROUSSEAU','PUPH');   
insert into caron_medecin VALUES (131,'Mathilde MORIN','0149282598','mathilde.morin2@wanadoo.fr','Florence ROUSSEAU','PATT');
insert into caron_medecin VALUES (146,'Laurent MERCIER','0149282584','laurent.mercier@free.fr','Florence ROUSSEAU','PATT');
insert into caron_medecin VALUES (124,'Christophe GARNIER','0149282567','chris.garnier@free.fr','Florence ROUSSEAU','CCA');
insert into caron_medecin VALUES (130,'Jeanne TUYAUX','0149285690','jeanne.tuyaux@wanadoo.fr','Alexandre TARAUD','CCA');
insert into caron_medecin VALUES (138,'Alexandre TARAUD','0149285690','alexandre.taraud@free.fr','Alexandre TARAUD','PUPH');
insert into caron_medecin VALUES (152,'Roselyne MEDECIN','0149285694','rose.medecin@free.fr','Alexandre TARAUD','CCA');

-- remplissage  med_plage_consult
insert into caron_med_plage_consult values(123,'mardi',8,12);
insert into caron_med_plage_consult values(123,'jeudi',13,17);
insert into caron_med_plage_consult values(123,'samedi',9,12);
insert into caron_med_plage_consult values(107,'jeudi',13,17);
insert into caron_med_plage_consult values(131,'lundi',17,19);
insert into caron_med_plage_consult values(131,'samedi',10,12);
insert into caron_med_plage_consult values(146,'mercredi',13,17);
insert into caron_med_plage_consult values(146,'lundi',8,12);
insert into caron_med_plage_consult values(124,'samedi',9,12);
insert into caron_med_plage_consult values(130,'mardi',13,17);
insert into caron_med_plage_consult values(130,'jeudi',13,17);
insert into caron_med_plage_consult values(130,'mercredi',17,19);
insert into caron_med_plage_consult values(130,'lundi',10,12);
insert into caron_med_plage_consult values(130,'samedi',10,12);
insert into caron_med_plage_consult values(138,'mardi',13,17);
insert into caron_med_plage_consult values(152,'mercredi',13,17);

-- remplissage malade
insert into caron_malade VALUES(5678,'durand','pierre','8 rue des plantes 75005 Paris','0123678767','0745453423');
insert into caron_malade VALUES(5430,'marchand','fabrice','10 boulevard Sébastopol 75003 Paris','0145672312','0678905632');
insert into caron_malade VALUES(5220,'bonnet','monique','34 rue de la Chine 75020 Paris', '0162738473','0687455620');
insert into caron_malade VALUES(5970,'lambert','jacques','45 rue de Charenton 75012 Paris','0182567878','0673568098');
insert into caron_malade VALUES(5780,'durand','jeanne','20 boulevard de la Bastille 75004 Paris','0134567189','0656787362');
insert into caron_malade VALUES(5632,'faure','aline','27 rue de la Roquette 75011 Paris','0143478372','0607634567');
insert into caron_malade VALUES(5320,'garcia','paul','79 rue du Faubourg saint Antoine 75011 Paris','0143458578','0663567680');

-- remplissage consultation
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(123,5780,'18-10-2011','09');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(123,5780,'27-10-2011','15');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(130,5780,'02-11-2011','18');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(138,5780,'04-10-2011','16');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(131,5632,'03-10-2011','16');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(131,5678,'03-10-2011','17');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(131,5430,'03-10-2011','18');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(131,5780,'08-10-2011','10');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(131,5678,'10-10-2011','18');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(152,5220,'12-10-2011','14');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(146,5320,'12-10-2011','14');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(124,5320,'15-11-2011','10');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(130,5320,'24-10-2011','10');
insert into caron_consultation(n_med,n_malade,date_consult,h_consult) values(130,5320,'26-10-2011','18');




