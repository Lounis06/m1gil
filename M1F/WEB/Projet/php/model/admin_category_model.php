<!--
	admin_category_model.php : Effectue les traitements demandés par l'interface
	d'administration pour la modification des catégories.
-->

<?php
	session_start();
	$_SESSION['dbconfig'] = "../../dbconfig.ini";

	// Initialisation des valeurs requises
	require("db/DBConnector.php");
	require("db/category.php");
	$DBC = new DBConnector();
	$keys = Category::$category_keys;

	// Traitement
	if (isset($_GET['mode'])) {
		// Chargement du profil existant, s'il existe
		$cats = Category::load($DBC->getConnexion(), array("idcat" => $_GET['idcat']));
		if (count($cats) == 0) {
			$cat = new Category();
		} else {
			$cat = $cats[0];
		}
		// Action selon le mode choisi
		switch ($_GET['mode']) {
			case "delete" :
				$cat->unregister($DBC->getConnexion());
				break;
			case "save" :
				// Modification des valeurs
				foreach ($keys as $key) {
					if ($key != "idcat") {
						if ($_GET[$key] !== "") {
							$cat->set($key, $_GET[$key]);
						} else {
							$cat->set($key, null);
						}
					}
				}

				// Sauvegarde si le contenu est cohérent
				if ($cat->isUsable($DBC->getConnexion())) {
					$cat->saveChanges($DBC->getConnexion());
					$_SESSION['note'] = "Sauvegarde réussie de la catégorie ".$cat->get("name");
				} else {
					$_SESSION['note'] = "Erreur lors de la sauvegarde : certains champs ne sont pas cohérents";
				}
				break;
			case "insert" :
				// Modification des valeurs
				foreach ($keys as $key) {
					if ($key != "idcat") {
						if ($_GET[$key] !== "") {
							$cat->set($key, $_GET[$key]);
						} else {
							$cat->set($key, null);
						}
					}
				}

				// Sauvegarde si le contenu est cohérent
				if ($cat->isUsable($DBC->getConnexion())) {
					if (!Category::isAvailable($DBC->getConnexion(), $cat->get('name'), $cat->get("parent"))) {
						$_SESSION['note'] = "Une catégorie possède déjà ces informations";
					} else {
						try {
							$cat->register($DBC->getConnexion());
							$_SESSION['note'] = "Ajout réussi de la catégorie ".$cat->get("name");
						} catch (Exception $e) {
							$_SESSION['error'] = "Erreur lors du processus de sauvegarde";
						}
					}
				} else {
					$_SESSION['note'] = "Erreur lors de l'ajout: certains champs ne sont pas cohérents";
				}
				break;
		}
	}

	header("location:".$_SESSION['page']);
?>