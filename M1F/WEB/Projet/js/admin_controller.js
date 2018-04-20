/**
 * admin_controller.js : Gère le fonctionnement des contrôleurs
 * de l'interface d'administration dynamique.
 *
 * Vu que le fonctionnement de chaque contrôleur est définie par ce script,
 * certaines varaibles doient impérativement être définies
 * pour agir correctement :
 *      - controllerId : Le contrôleur en cours d'utilisation
 *      - url : Le nom du modèle PHP associé au contrôleur
 *		- idName : Le nom de l'attribut d'identification
 *		- keys : Les attributs de l'objet à utiliser
 *
 * Il faudra également veiller lors de la mise en place, 
 * à la mise-à-jour de ces informations, lors des changements de
 * contrôleur.
 */

// Gestion de la sauvegarde d'une ligne de tableau
function saveLine(id, idrow) {
	// Récupération de la ligne de la ligne
	var ligne = document.getElementById(idrow);

	// Création de la requête
	var req = "?mode=save&" + idName + "=" + id;
	var tds = ligne.getElementsByClassName("attr");

	if (tds.length != keys.length) {
		alert("Erreur : Ligne de données incohérente");
		return
	}
	for (var k = 0; k < tds.length; ++k) {
		req += "&" + keys[k] + "=" + tds[k].textContent.trim();
	}

	// Exécution de la requête : Chargement de la page de traitement
	window.location = url + req;
}


// Gestion de la suppression d'une ligne de tableau
function deleteLine(id) {
	var req = "?mode=delete&" + idName + "=" + id;
	// Exécution de la requête de suppression
	window.location = url + req;
}


// Gestion de l'insertion d'une nouvelle ligne
function insertLine() {
	// Récupération de la ligne d'insertion
	var table = document.getElementById(controllerId);
	var ligne = table.getElementsByClassName("insert")[0];

	// Création de la requête
	var req = "?mode=insert";
	var tds = ligne.getElementsByClassName("attr");

	if (tds.length != keys.length) {
		alert("Erreur : Ligne de données incohérente");
		return
	}
	for (var k = 0; k < tds.length; ++k) {
		req += "&" + keys[k] + "=" + tds[k].textContent.trim();
	}

	// Exécution de la requête : Chargement de la page de traitement
	window.location = url + req;
}