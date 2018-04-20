/*
 * base.sql : Définit les requêtes permettant de construire
 * un jeu de données initial.
 *
 */

/* --- Laboratoire ---  */
insert into laboratoire values (1, 'Mylan');
insert into laboratoire values (2, 'Boiron');
insert into laboratoire values (3, 'Bayer');

/* --- Médecin --- */
insert into medecin values (1, 'Cymes', 'Michel');
insert into medecin values (2, 'Freud', 'Sigmund');
insert into medecin values (3, 'Alzheimer', 'Alois');
insert into medecin values (4, 'Pasteur', 'Louis');
insert into medecin values (5, 'Nicolle', 'Charles');

/* --- Symptome --- */
insert into symptome values (1, 'Fièvre');
insert into symptome values (2, 'Sueurs nocturnes');
insert into symptome values (3, 'Amaigrissement');
insert into symptome values (4, 'Douleur thoracique');
insert into symptome values (5, 'Dhiarrhée');
insert into symptome values (6, 'Nausées');
insert into symptome values (7, 'Vomissements');
insert into symptome values (8, 'Apnée');
insert into symptome values (9, 'Toux');
insert into symptome values (10, 'Déshydratation');

/* --- Maladie --- */
insert into maladie values (1, NULL, 'Virus', Liste_cles_type(1));
insert into maladie values (2, 1, 'VirusA', Liste_cles_type(1, 6));
insert into maladie values (3, 1, 'VirusB', Liste_cles_type(1, 2, 6));
insert into maladie values (4, 1, 'VirusC', Liste_cles_type(1, 9));
insert into maladie values (5, NULL, 'Maladie', Liste_cles_type(3, 4);
insert into maladie values (6, 5, 'Maladie1', Liste_cles_type(3, 8));
insert into maladie values (7, 5, 'Maladie2', Liste_cles_type(2, 3));
insert into maladie values (8, 7, 'Maladie2A', Liste_cles_type(2, 4));
insert into maladie values (9, 7, 'Maladie2B', Liste_cles_type(4, 7));
insert into maladie values (10, NULL, 'Infection', Liste_cles_type(10));

/* --- Effet indésirable --- */
insert into effet_indesirable values (1, 'Effet1', 'Provoque des effets de type 1');
insert into effet_indesirable values (2, 'Effet2', 'Provoque des effets de type 2');
insert into effet_indesirable values (3, 'Effet3', 'Provoque des effets de type 3');
insert into effet_indesirable values (4, 'Effet4', 'Provoque des effets de type 4');
insert into effet_indesirable values (5, 'Effet5', 'Provoque des effets de type 5');

/* --- Substance active --- */
insert into substance_active values (1, NULL, 'Substance', Liste_cles_type(1));
insert into substance_active values (2, 1, 'SubstanceA', Liste_cles_type(1, 3));
insert into substance_active values (3, 1, 'SubstanceB', Liste_cles_type(2));
insert into substance_active values (4, 1, 'SubstanceC', Liste_cles_type(4));
insert into substance_active values (5, NULL, 'Actif', Liste_cles_type(2, 5));
insert into substance_active values (6, 5, 'ActifA', Liste_cles_type(5));
insert into substance_active values (7, 5, 'ActifB', Liste_cles_type(2, 3));

/* --- Caractéristique --- */
insert into caracteristique values (1, 'Femme enceinte');
insert into caracteristique values (2, 'Enfant de moins de 36 mois');
insert into caracteristique values (3, 'Personne agée');
insert into caracteristique values (4, 'Personne présentant des troubles cardiaques');

/* --- Interaction ---- */
insert into interaction values (1, 1, 4, 3);
insert into interaction values (1, 2, 4, 4);

/* --- Medicament --- */
insert into medicament values (1, 'Medicament1',
	Liste_cles_type(1, 2), Liste_cles_type(3), Liste_cles_type(1, 2, 4),
	Liste_cles_type(1)
);
insert into medicament values (2, 'Medicament2',
	Liste_cles_type(3, 4), Liste_cles_type(1), Liste_cles_type(3),
	Liste_cles_type(2)
);
insert into medicament values (3, 'Medicament3',
	Liste_cles_type(5), Liste_cles_type(2), Liste_cles_type(5, 9),
	Liste_cles_type(5)
);
insert into medicament values (4, 'Medicament4',
	Liste_cles_type(6, 7), Liste_cles_type(3), Liste_cles_type(7),
	Liste_cles_type(1)
);
insert into medicament values (5, 'Medicament5',
	Liste_cles_type(7), Liste_cles_type(4), Liste_cles_type(6, 7, 8),
	Liste_cles_type(3)
);
insert into medicament values (6, 'Medicament6',
	Liste_cles_type(6), NULL, Liste_cles_type(10), NULL
);

/* --- Patient --- */
insert into patient values (1, 'Dupont Pierre', 43, Liste_cles_type(4), Liste_cles_type(1));
insert into patient values (2, 'Durand Franck', 89, Liste_cles_type(3, 4), Liste_cles_type(2));
insert into patient values (3, 'Martin Patricia', 32, Liste_cles_type(1), Liste_cles_type(3));
insert into patient values (4, 'Martin Léo', 2, Liste_cles_type(2), Liste_cles_type(4));

/* --- Développement --- */
insert into developpement values (1, 1, 1, Liste_cles_type(1, 2));
insert into developpement values (2, 2, 1, Liste_cles_type(1));
insert into developpement values (3, 3, 2, Liste_cles_type(3));
insert into developpement values (4, 4, 3, Liste_cles_type(1, 3));
insert into developpement values (5, 5, 3, Liste_cles_type(2));

/* --- Consultation --- */
insert into consultation values (1, 1, 1, Liste_cles_type(1, 9, 10), Liste_cles_type(4, 10),
	Observations_type('Analyse sanguine => VirusC', 'Présente des signes infectieux')
);
insert into consultation values (2, 2, 3, Liste_cles_type(3, 4, 7), Liste_cles_type(5, 9),
	NULL
);
insert into consultation values (3, 3, 2, Liste_cles_type(3, 8), Liste_cles_type(6),
	Observations_type('Analyse sanguine => Maladie1')
);
insert into consultation values (4, 4, 1, Liste_cles_type(2, 3), Liste_cles_type(7),
	Observations_type('Nourisson => traitement préventif')
);

/* --- Traitement --- */
insert into traitement values (1, 1, '24-12-2016', '08-01-2017', Liste_cles_type(1, 6));
insert into traitement values (2, 2, '02-01-2017', '02-07-2017', Liste_cles_type(3));
insert into traitement values (3, 3, '05-01-2017', '05-10-2017', Liste_cles_type(5));
insert into traitement values (4, 4, '07-01-2017', '14-01-2017', Liste_cles_type(4));