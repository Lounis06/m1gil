<!--
	admin_user_model.php : Effectue les traitements demandés par l'interface
	pour l'administration des utilisateurs.
-->

<?php
	session_start();
	$_SESSION['dbconfig'] = "../../dbconfig.ini";

	// Initialisation des valeurs requises
	require("db/DBConnector.php");
	require("db/user.php");
	$DBC = new DBConnector();
	$keys = User::$user_editable_keys;

	// Traitement
	if (isset($_GET['mode'])) {
		// Chargement du profil existant
		if (isset($_GET['idusr'])) {
			$usr = User::load($DBC->getConnexion(), array("idusr" => $_GET['idusr']))[0];
		} else {
			$usr = new User(false);
		}

		// Action selon le mode choisi
		switch ($_GET['mode']) {
			case "delete" :
				$usr->unregister($DBC->getConnexion());
				break;
			case "save" :
				// Modification des valeurs
				foreach ($keys as $key) {
					$usr->set($key, $_GET[$key]);
				}
				print_r($usr->getAll());
				// Sauvegarde si le contenu est cohérent
				if ($usr->isUsable($DBC->getConnexion())) {
					$usr->saveChanges($DBC->getConnexion());
					$_SESSION['note'] = "Sauvegarde réussie du profil ".$usr->get("login");
				} else {
					$_SESSION['note'] = "Erreur lors de la sauvegarde : certains champs ne sont pas cohérents";
				}
				break;
			case "insert" :
				// Modification des valeurs
				foreach ($keys as $key) {
					$usr->set($key, $_GET[$key]);
				}

				// Sauvegarde si le contenu est cohérent
				echo '|'.$usr->get('birthDate').'|';
				if (!User::isLoginAvailable($DBC->getConnexion(), $usr->get("login"))) {
					$_SESSION['note'] = "Ce nom d'utilisateur est déjà attribué";
				} else {
					if ($usr->isUsable($DBC->getConnexion())) {
						$usr->register($DBC->getConnexion());
						$_SESSION['note'] = "Ajout réussi du profil ".$usr->get("login");
					} else {
						$_SESSION['note'] = "Erreur lors de l'ajout: certains champs ne sont pas cohérents";
					}
				}
				break;
		}
	}

	header("location:".$_SESSION['page']);
?>