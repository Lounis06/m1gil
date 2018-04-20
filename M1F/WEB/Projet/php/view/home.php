<!--
	home.php : Page d'accueil du site
-->


<?php
	session_start();

	$_SESSION['dbconfig'] = "../../dbconfig.ini";
	$_SESSION['page'] = $_SERVER['PHP_SELF'];
?>

<!DOCTYPE html>

<head>
	<title>SciMS - Accueil</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="../../css/global.css" media="all">
</head>

<body>
	<!-- Couche SUPERIEURE : Haut de page et affichage des erreurs/notifications -->
	<?php require("components/top_layouts.php"); ?>

	<!-- Couche CENTRALE : contenu principal du site -->
	<div id="center_layout" class="layout">
		<div class="layout_content">
			<?php require("../controller/menu_bar.php"); ?>

			<div id="main_content">
				<h2>Accueil</h2>
				<hr />
				<p>
					Bienvenue sur le site CMS, ce site a pour but de partager des articles
					à nature scientifique au plus grand nombre.
				</p>
				<p>
					Vous pouvez consulter tout article en naviguant depuis la catégorie
					de votre choix, portant sur un thème spécifique.
					Les catégories principales étant indiquées sur votre gauche.
				</p>
				<p>
					N'hésitez pas à vous inscrire afin de pouvoir contribuer à la rédaction
					de nouveaux articles. Une interface vous sera alors proposée pour en
					faciliter la création.
				</p>
			</div>
		</div>
	</div>
</body>