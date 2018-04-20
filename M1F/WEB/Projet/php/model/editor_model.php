<!--
	register_model.php : Traite l'enregistrement d'un article auprès du site.
-->
<?php
	// Initialisation
	session_start();
	print_r($_POST);

	require_once("db/DBConnector.php");
	require_once("db/article.php");
	$_SESSION['dbconfig'] = "../../dbconfig.ini";
	

	// Vérification préliminaires (on retire l'id des clés potentielles)
	$keys = array_diff(Article::$article_keys, array(Article::$article_id));
	foreach ($keys as $key) {
		if (!isset($_POST[$key])) {
			$_SESSION['error'] = "Envoi invalide de l'article, information ".$key." manquante";
			header("location:".$_SESSION['page']);
		}
	}

	// Enregistrement des travaux précédents (en cas d'erreur lors de la sauvegarde)
	$_SESSION['edit'] = array(
		'abstract' => $_POST['abstract'],
		'keywords' => $_POST['keywords'],
		'title' => $_POST['title']
	);

	// Création de l'objet article en PHP
	$art = new Article();
	foreach ($keys as $key) {
		$art->set($key, $_POST[$key]);
	}

	// Connexion et enregistrement auprès de la base de données
	$DBC = new DBConnector();
	if (!$art->isUsable($DBC->getConnexion())) {
		// Cas d'erreur : Les données testées pour la 
		// validité sont fournies automatiquement
		$_SESSION['error'] = "Informations automatiques de l'article erronées";
		header("location:".$_SESSION['page']);
	}

	// Enregistrement de l'article
	try {
		$art->register($DBC->getConnexion());
	} catch (Exception $e) {
		$_SESSION['error'] = "Erreur lors du processus de sauvegarde";
		header("location:".$_SESSION['page']);
	}
	
	// Confirmation de l'ajout
	unset($_SESSION['edit']);
	$_SESSION['note'] = "Article ajouté. Merci de votre contribution ! :D";
	header("location:../view/home.php");
?>