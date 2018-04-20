<!--
	login_bar_model.php : Définit le traitement du formulaire
	associé à la barre d'authentification.
-->

<?php
	session_start();
	$_SESSION['dbconfig'] = "../../dbconfig.ini";

	// Traitement de la demande
	if (isset($_POST['login'])) {
		// Cas n°1 : demande de connexion
		require_once("db/DBConnector.php");
		require_once("db/user.php");

		// Connexion à la base de données
		$DBC = new DBConnector();

		// Recherche du profil de l'utilisateur
		$crits = array(
			"login" => $_POST["username"], 
			"password" => $_POST["password"]
		);
		$tab = User::load($DBC->getConnexion(), $crits);

		// Traitement
		if (count($tab) == 0) {
			// Si aucun compte n'est trouvé, on avertit l'utilisateur
			$_SESSION['note'] = "Aucun compte n'est rattaché à ces informations de connexion.";

		} else if (count($tab) == 1) {
			// Si un compte est trouvé, on authentifie l'utilisateur
			$_SESSION['user'] = $tab[0]->getAll();

		} else {
			// Cas d'occurence multiple : erreur dans la base de données.
			$_SESSION['error'] = "Comptes multiples attachés à ces identifiants";
		}
	} else if (isset($_POST['logoff'])) {
		// Cas n°2 : demande de déconnexion
		$_SESSION["user"] = null;
	}

	header("location:".$_SESSION["page"]);
?>