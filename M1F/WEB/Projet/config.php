<!DOCTYPE html>

<?php
	// Initialisation de la session
	session_start();
	$_SESSION['dbconfig'] = "dbconfig.ini";
	$_SESSION['page'] = $_SERVER['PHP_SELF'];

	require_once("php/model/db/DBConfig.php");
	require_once("php/model/db/DBConnector.php");
	require_once("php/model/db/DBInitializer.php");

	// Traitement de l'installation
	function install() {
		$installFiles = array("sql/install.sql", "sql/exemples.sql");
		$keys = DBConfig::$keys;

		foreach ($keys as $key) {
			if (!isset($_POST[$key])) {
				$_SESSION['note'] = "Erreur : Paramètre ".$key." non spécifié";
				return;
			}
		}

		// Création de la configuration
		$cfg = new DBConfig();
		foreach ($keys as $key) {
			$cfg->set($key, $_POST[$key]);
		}

		$cfg->save($_SESSION['dbconfig']);

		// Test de la connexion
		try {
			$DBC = new DBConnector();
		} catch (Exception $e) {
			$_SESSION['note'] = "Erreur : Connexion impossible au SGBD distant";
			return;
		}

		// Initialisation de la base de données
		try {
			$DBI = new DBInitializer();
			foreach($installFiles as $file) {
				$DBI->exec($DBC->getConnexion(), $file);
			}
		} catch (Exception $e) {
			$_SESSION['note'] = "Erreur : Initialisation impossible du SGBD distant";
			return;
		}

		// Fin du processus d'installation
		$_SESSION['note'] = "Installation du site réussie !";
	}

	// Traitement de la désinstallation
	function uninstall() {
		$uninstallFiles = array("sql/uninstall.sql");
		
		try {
			$DBC = new DBConnector();
			$DBI = new DBInitializer();
			foreach ($uninstallFiles as $file) {
				$DBI->exec($DBC->getConnexion(), $file);
			}
			unlink($_SESSION['dbconfig']);
			$_SESSION['note'] = "Désinstallation effectuée avec succès !";
		} catch (Exception $e) {
			$_SESSION['note'] = "Echec de la désinstallation";
		}
	}

	// Traitement de la configuration
	if (isset($_POST['install'])) {
		install();
	} else if (isset($_POST['uninstall'])) {
		uninstall();
	}
?>


<html>
<head>
	<title>SciMS - Configuration du Site </title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
	<!-- Affichage d'une confirmation -->
	<?php if (isset($_SESSION['note'])) { ?>
		<p> <?php echo $_SESSION['note']; $_SESSION['note'] = null; ?> </p>
		<hr />
	<?php } ?>

	<!-- Configuration du site -->
	<div id="install">
		<h2>Configuration du site</h2>
		<p> Ce formulaire a pour but de founir les informations nécessaires à la
		configuration du site. <br />Veuillez remplir les différents champs, 
		afin de procéder à la configuration :</p>
		<form action="config.php" method="POST">
			<label> DSN : </label><input type="text" name="dsn"><br />
			<label> Compte : </label><input type="text" name="login"><br />
			<label> Mot de passe : </label><input type="text" name="password"><br />
			<input type="submit" name="install" value="Configurer">
		</form>
	</div>
	<hr />
	<!-- Désinstallation -->
	<div id	="uninstall"> 
		<h2>Désinstallation du site</h2>
		<p> Ce bouton vous permet de supprimer la configuration 
		existante de manière définitive</p>
		<form action="config.php" method="POST">
			<input type="submit" name="uninstall" value="Suprimer la configuration">
		</form>
	</div>
</body>
