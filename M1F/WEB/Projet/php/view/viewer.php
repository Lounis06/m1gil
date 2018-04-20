<!--
	viewer.php : Page de lecture du contenu des catégories et des articles
	Permet d'afficher un article particulier, ou d'afficher les sous-catégories disponibles
	d'une catégorie spécifiée
-->


<?php
	session_start();

	$_SESSION['dbconfig'] = "../../dbconfig.ini";
	$_SESSION['page'] = $_SERVER['PHP_SELF'];
?>

<!DOCTYPE html>

<head>
	<title>SciMS - Consultation</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="../../css/viewer.css" media="all">
</head>

<body>
	<!-- Couche SUPERIEURE : Haut de page et affichage des erreurs/notifications -->
	<?php require("components/top_layouts.php"); ?>

	<!-- Couche CENTRALE : contenu principal du site -->
	<div id="center_layout" class="layout">
		<div class="layout_content">
			<?php require("../controller/menu_bar.php"); ?>

			<div id="main_content">
				<?php
					// Consultation d'un article
					if (isset($_GET['idart'])) {
						require("components/viewer_article.php");
					// Consultation d'une catégorie
					} else { 
						require("../controller/viewer_category_controller.php");
					}
				?>
			</div>
		</div>
	</div>
</body>