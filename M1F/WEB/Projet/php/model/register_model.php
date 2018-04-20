<!--
	register_model.php : Traite la demande d'inscription auprès du site.
-->

<?php
	// Initialisation
	session_start();

	require_once("db/DBConnector.php");
	require_once("db/user.php");
	$_SESSION['dbconfig'] = "../../dbconfig.ini";

	// Vérifications préliminaires (phase de confirmation)
	if (!isset($_POST['password']) || !isset($_POST['password_confirm'])) {
		$_SESSION["note"] = "Certaines informations liés au mots de passe sont manquantes. Veuillez réessayer.";
		header("location:".$_SESSION['page']);
	}
	if ($_POST['password'] != $_POST['password_confirm']) {
		$_SESSION["note"] = "Les mots de passe entrés sont différents. Veuillez réessayer.";
		header("location:".$_SESSION['page']);
	}
	if (!isset($_POST['birthDate']) || !AbstractDBObject::isDateUsable($_POST['birthDate'])) {
		$_SESSION["note"] = "Le format de la date de naissance est incorrect";
		header("location:".$_SESSION['page']);
	}

	// Création d'un nouveau profil utilisateur par rapport aux données fournies
	$usr = new User(false);
	$args = array();
	$keys = $usr->getKeys();

	foreach ($keys as $key) {
		if ($key != "idusr" && isset($_POST[$key])) {
			$usr->set($key, $_POST[$key]);
		}
	}

	// Création d'une liaison avec la base de données
	$DBC = new DBConnector();

	// Test de cohérence de l'objet utilisateur crée
	if (!$usr->isUsable($DBC->getConnexion())) {
		// Invalide : On notifie l'utilisateur
		$_SESSION["note"] = "Certaines informations sont manquantes ou incorrectes. Veuillez réessayer.";

		// Retour à la page d'inscription
		header("location:".$_SESSION['page']);
	} else {
		// Valide : Enregistrement auprès de la base de données
		if (!User::isLoginAvailable($DBC->getConnexion(), $usr->get("login"))) {
			$_SESSION["note"] = "Le nom d'utilisateur est dejà utilisé";

			// Retour à la page d'inscription
			header("location:".$_SESSION['page']);
		} else {
			try {
				$usr->register($DBC->getConnexion());
			} catch (InvalidArgumentException $e) {
				$_SESSION["error"] = "Cas incohérent lors de l'inscription :";
				$_SESSION["error"] = $_SESSION["error"].$e->getMessage();

				// Retour à la page d'inscription
				header("location:".$_SESSION['page']);
			}

			// Chargement des informations dans la session
			$_SESSION['user'] = $usr->getAll();
			$_SESSION['note'] = "Vous êtes maintenants inscrit ! Bienvenue ".$_SESSION['user']['firstName'];
			// Retour à la page d'accueil
			header("location:../view/home.php");
		}
	}
?>