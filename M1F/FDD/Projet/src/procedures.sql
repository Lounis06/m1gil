/*
 * procedures.sql : Contient le code SQL nécessaire des différentes
 * procédures et fonctions disponibles pour ce modèle de données.
 *
 */

/* -- QUESTION 1 -- */
/**
 * Enregistre un nouveau traitement sur la durée d'aujourd'hui à 
 * fin (si fin est à NULL, il s'agit d'un traitement longue durée),
 * impliquant l'utilisation des médicaments medics, et prescrits 
 * lors de la consultation consult.
 *
 * @param
 *		medics : La liste des médicaments prescrits
 *      consult : La consultation ayant entraîné ce traitement
 *		fin : La date de fin de traitement
 */
CREATE OR REPLACE PROCEDURE prescription(medics Liste_cles_type, fin date, consult integer) IS
	v_debut date;
	v_table Liste_cles_type;
	BEGIN
		/* Obtention de la date actuelle */
		SELECT SYSDATE INTO v_debut FROM DUAL;

		/* Insertion dans la table des traitements */
		INSERT INTO traitement(idcon, date_debut, date_fin, medicaments) 
		VALUES (consult, v_debut, fin, medics);
	END;
/


/* -- QUESTION 2 -- */
/**
 * Renvoie tous les traitements en cours d'un patient
 *
 * @param
 *		idpatient : Le n° du patient à décrire
 */
CREATE OR REPLACE FUNCTION obtenir_suivi(idpatient IN patient.idpat%type) 
	RETURN Liste_cles_type AS v_resultat Liste_cles_type;
	v_date date;
	BEGIN
		/* Obtention de la date actuelle */
		SELECT SYSDATE INTO v_date FROM DUAL;

		/* Lecture des traitement en cours */
		SELECT idtra 
		BULK COLLECT INTO v_resultat
		FROM traitement, consultation
		WHERE traitement.idcon = consultation.idcon
		AND consultation.idpat = idpatient
		AND date_debut <= v_date
		AND date_fin > v_date;

		RETURN v_resultat;
	END;
/

/* -- QUESTION 3 -- */
/**
 * Renvoie les informations sur les effets indésirables d'un médicament
 * (nombre et listing)
 *
 * @param
 *		medic : L'ID du médicament à décrire
 *		nb_effets : La valeur de retour qui contiendra le nombre d'effets
 *		effets : La table de retour contenant la liste des effets
 *		
 */
CREATE OR REPLACE FUNCTION obtenir_effets(medic IN medicament.idmed%type)
RETURN Liste_cles_type AS v_resultat Liste_cles_type;
	BEGIN
		SELECT effets
		INTO v_resultat
		FROM medicament
		WHERE idmed = medic;

		RETURN v_resultat;
	END;
/

CREATE OR REPLACE FUNCTION obtenir_nb_effets(medic IN medicament.idmed%type)
RETURN integer AS v_resultat integer;
	BEGIN
		SELECT count(*)
		INTO v_resultat
		FROM medicament m, table(m.effets) t
		WHERE m.idmed = medic;

		RETURN v_resultat;
	END;
/

/* -- QUESTION 4 -- */
/**
 * Renvoie les médicaments sujets à des interactions médicamenteuses
 */
CREATE OR REPLACE FUNCTION obtenir_medicaments_sensibles
RETURN Liste_cles_type AS v_resultat Liste_cles_type;
	BEGIN
	    SELECT DISTINCT idmed 
	    BULK COLLECT INTO v_resultat 
	    FROM MEDICAMENT
	    WHERE idmed IN (SELECT idmed FROM INTERACTION)
	    OR idmed IN (SELECT idmed2 FROM INTERACTION);
	    RETURN v_resultat;
	END;
/


/**
 * Renvoie les intéractions avec d'autres médicaments pour un médicament
 * donné.
 *
 * @param
 * 		medic : l'id du médicament à détailler
 */
CREATE OR REPLACE FUNCTION obtenir_interactions(medic IN medicament.idmed%type)
RETURN Liste_cles_type AS v_resultat Liste_cles_type;
	BEGIN
		SELECT DISTINCT idint
		BULK COLLECT INTO v_resultat
		FROM interaction
		WHERE idmed = medic OR idmed2 = medic;
		RETURN v_resultat;
	END;
/


/* -- QUESTION 5 -- */
/**
 * Renvoie le nombre de valeurs communes aux deux listes suivantes
 *
 * @param
 *		l1, l2 : Les deux listes à comparer
 */
CREATE OR REPLACE FUNCTION identiques(l1 IN Liste_cles_type, l2 IN Liste_cles_type)
	RETURN integer AS v_resultat integer;
	BEGIN
		SELECT COUNT(*)
		INTO v_resultat
		FROM table(l1) t
		WHERE column_value in (SELECT column_value from table(l2));

		RETURN v_resultat;
	END;
/

/**
 * Renvoie une liste de médicaments utilisables pour une maladie
 * diagnostiquée.
 *
 * @param
 *		maladie : L'id de la maladie à détailler
 */
CREATE OR REPLACE FUNCTION idees_medicaments(maladie IN maladie.idmal%type)
RETURN Liste_cles_type AS v_resultat Liste_cles_type;
	v_maladies Liste_cles_type;
	BEGIN
		/* Obtention de l'arborescence des maladies possibles */
		SELECT idmal
		BULK COLLECT INTO v_maladies
		FROM maladie
		WHERE idmal IS NOT NULL
		START WITH idmal = maladie
		CONNECT BY PRIOR idparent = idmal;

		/* Récupération des médicaments cohérents */
		SELECT idmed
		BULK COLLECT INTO v_resultat
		FROM MEDICAMENT
		WHERE identiques(maladies_ciblees, v_maladies) > 0;

		return v_resultat;
	END;
/


/* -- QUESTION 6 -- */
/**
 * Renvoie une liste de médicaments utilisables pour une maladie
 * diagnostiquée.
 *
 * @param
 *		medic : L'id du médicament à décrire
 */
CREATE OR REPLACE FUNCTION effets_probables(medic IN medicament.idmed%type)
RETURN Liste_cles_type AS v_resultat Liste_cles_type;
	BEGIN
		/* Obtention de l'arborescence des maladies possibles */
		SELECT DISTINCT e.column_value
		BULK COLLECT INTO v_resultat
		FROM substance_active sa, table(sa.effets) e
		WHERE sa.idsub IS NOT NULL
		START WITH sa.idsub IN (
			SELECT c.column_value
			FROM medicament m, table(m.composition) c
			WHERE m.idmed = medic
		)
		CONNECT BY PRIOR sa.idparent = sa.idsub;

		return v_resultat;
	END;
/


/* -- QUESTION 7 -- */
/**
 * Renvoie la liste des médicaments qui ne sont prescrits que 
 * par des médecins ayant travaillé à leur conception.
 */
CREATE OR REPLACE FUNCTION med_doc_equipe
RETURN Liste_cles_type as v_resultat Liste_cles_type;
	BEGIN
		SELECT idmed
		BULK COLLECT INTO v_resultat
		FROM medicament m
		WHERE m.idmed IN (
			SELECT ms.column_value
			FROM traitement t, table(t.medicaments) ms
			WHERE t.idcon IN (
				SELECT c.idcon
				FROM consultation c
				WHERE c.iddoc IN (
					SELECT e.column_value
					FROM developpement d, table(d.equipe) e
					WHERE d.idmed = m.idmed
				)
			)
		);
		RETURN v_resultat;
	END;
/


/* -- QUESTION 8 -- */
/**
 * Renvoie la liste des médicaments qui ne sont prescrits 
 * que par des médecins ayant travaillé pour
 * les laboratoires les ayant conçus.
 */
CREATE OR REPLACE FUNCTION med_doc_labo
RETURN Liste_cles_type as v_resultat Liste_cles_type;
	BEGIN
		SELECT idmed
		BULK COLLECT INTO v_resultat
		FROM medicament m
		WHERE m.idmed IN (
			SELECT ms.column_value
			FROM traitement t, table(t.medicaments) ms
			WHERE t.idcon IN (
				SELECT c.idcon
				FROM consultation c
				WHERE c.iddoc IN (
					SELECT iddoc
					FROM developpement
					WHERE idlab IN (
						SELECT idlab
						FROM developpement
						WHERE idmed = m.idmed
					)
				)
			)
		);
		RETURN v_resultat;
	END;
/


/* -- QUESTION 9 -- */
/**
 * Renvoie une liste de maladies probables selon
 * les symptomes trouvés lors de la consultation.
 *
 * @param
 * 		consult : L'ID de la consultation à analyser
 */
CREATE OR REPLACE FUNCTION debusquer_maladie(consult consultation.idcon%type)
RETURN Liste_cles_type as v_resultat Liste_cles_type;
	v_symptomes Liste_cles_type;
	v_nb integer;
	BEGIN
		/* Obtention des symptomes décelés */
		SELECT symptomes
		INTO v_symptomes
		FROM consultation
		WHERE idcon = consult;

		/* Obtention du nombre de symptomes */
		SELECT COUNT(*)
		INTO v_nb
		FROM table(v_symptomes);

		/* Sélection des maladies potentielles, une maladie
		 * potentielle inclut tous les symptômes décelés
		 */
		SELECT idmal
		BULK COLLECT INTO v_resultat
		FROM maladie
		WHERE identiques(symptomes, v_symptomes) = v_nb;

		RETURN v_resultat;
	END;
/


/**
 * Renvoie une liste de médicaments pouvant potentiellement
 * cibler la maladie traduite par les symptomes décrits lors
 * de la consultation.
 *
 * @param
 * 		consult : L'ID de la consultation à analyser
 */
CREATE OR REPLACE FUNCTION proposer_traitement(consult consultation.idcon%type)
RETURN Liste_cles_type as v_resultat Liste_cles_type;
	v_maladies Liste_cles_type;
	v_pat patient.idpat%type;
	BEGIN
		/* Obtention des maladies potentielles */
		SELECT debusquer_maladie(consult) INTO v_maladies FROM DUAL;

		/* Obtention du n° du patient */
		SELECT idpat INTO v_pat
		FROM consultation
		WHERE consult = idcon;

		/* Obtention des médicaments */
		SELECT idmed
		BULK COLLECT INTO v_resultat
		FROM medicament
		WHERE identiques(maladies_ciblees, v_maladies) > 0;

		RETURN v_resultat;
	END;
/
