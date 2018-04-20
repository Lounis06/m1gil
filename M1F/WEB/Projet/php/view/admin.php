<!--
	admin.php : Interface d'administration du site
-->

<?php
	session_start();

	$_SESSION['dbconfig'] = "../../dbconfig.ini";
	$_SESSION['page'] = $_SERVER['PHP_SELF'];

	// On restreint l'accès aux administrateurs
	if (!isset($_SESSION['user']) || !$_SESSION['user']['isAdmin']) {
		$_SESSION['note'] = "L'accès à cette page requiert des droits d'administration";
		header("location:home.php");
	}
?>

<!DOCTYPE html>

<head>
	<title>SciMS - Gestion des utilisateurs </title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="../../css/admin.css" media="all">
	<script src="../../js/admin_controller.js"></script>
	<script src="../../js/admin.js"></script>
</head>

<body>
	<!-- Couche SUPERIEURE : Haut de page et affichage des erreurs/notifications -->
	<?php require("components/top_layouts.php"); ?>

	<!-- Couche CENTRALE : contenu principal du site -->
	<div id="center_layout" class="layout">
		<div class="layout_content">
			<?php require("../controller/menu_bar.php"); ?>

			<div id="main_content">
				<h2>Interface d'administration </h2>
				<hr class="content_separator" />
				<div id="controllers_menu">
					<h3>Choix des menus d'édition actifs</h3>
					<input id="userCheckBox" type="checkbox" onclick="updateMenuDisplay();" checked="checked">
					<label>Profils d'utilisateurs</label>
					<input id="categoryCheckBox" type="checkbox" onclick="updateMenuDisplay();" checked="checked">
					<label>Catégories d'articles</label>
					<hr class="content_separator" />
				</div>
				<div id="controllers">
					<div id="userEdit">
						<h3>Gestion des utilisateurs</h3>
						<p> Cette partie de l'interface vous permet d'éditer le profil des utilisateurs </p>
						<p> Indications :</p>
						<ul>
							<li>La première ligne du tableau permet uniquement de créer un nouveau profil</li>
							<li>Toute action de suppression est définitive</li>
							<li>Le format de la date utilisé est AAAA-MM-JJ, ce qui correspond
							au format standard des bases de données</li>
						</ul>
						<?php 
							// Définition du contrôleur associé aux utilisateurs
							require_once("../model/db/user.php");
							$_ADMIN = array(
								'model' => "../model/admin_user_model.php",
								'tableName' => User::$user_table,
								'id' => User::$user_id,
								'editableKeys' => User::$user_editable_keys,
								'uneditableKeys' => array(),
								'desc' => User::$user_keys_desc,
								'crits' => array("isAdmin" => 0)
							);

							require("../controller/admin_controller.php"); 
						?>
						<hr class="content_separator" />
					</div>
					<div id="categoryEdit">
						<h3>Gestion des catégories</h3>
						<p> Cette partie de l'interface vous permet d'éditer les catégories d'article </p>
						<p> Indications :</p>
						<ul>
							<li>La première ligne du tableau permet uniquement de créer un nouvelle catégorie</li>
							<li>Toute action de suppression est également définitive ici</li>
							<li>Pour définir un lien de parenté entre des catégories, 
							spécifiez un code de catégorie parente, pour votre sous-catégorie</li>
							<li>Ne pas définir de code parent est possible, et équivaut à
							créer une catégorie principale. Elle apparaîtra alors dans la barre
							du menu</li>
						</ul>
						<?php 
							// Définition du contrôleur associé aux catégories
							require_once("../model/db/category.php");
							$_ADMIN = array(
								'model' => "../model/admin_category_model.php",
								'tableName' => Category::$category_table,
								'id' => Category::$category_id,
								'editableKeys' => array("name", "parent"),
								'uneditableKeys' => array("idcat"),
								'desc' => Category::$category_keys_desc,
								'crits' => array()
							);
							require("../controller/admin_controller.php"); 
						?>
						<hr class="content_separator" />
					</div>
				</div>
				<script>updateMenuDisplay();</script>
			</div>
		</div>
	</div>
</body>