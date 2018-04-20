<!--
	editor.php : Page d'édition d'un article
-->

<?php
	session_start();

	$_SESSION['dbconfig'] = "../../dbconfig.ini";
	$_SESSION['page'] = $_SERVER['PHP_SELF'];

	// On restreint l'accès aux utilisateurs
	if (!isset($_SESSION['user']) || $_SESSION['user']['isAdmin']) {
		$_SESSION['note'] = "L'accès à cette page requiert d'être inscrit";
		header("location:home.php");
	}
?>

<!DOCTYPE html>

<head>
	<title>SciMS - Accueil</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="../../css/editor.css" media="all">
	<script src="../../js/struct.js"></script>
	<script src="../../js/editor.js"></script>
</head>

<body>
	<!-- Couche SUPERIEURE : Haut de page et affichage des erreurs/notifications -->
	<?php require("components/top_layouts.php"); ?>

	<!-- Couche CENTRALE : contenu principal du site -->
	<div id="center_layout" class="layout">
		<div class="layout_content">
			<?php require("../controller/menu_bar.php"); ?>

			<div id="main_content">
				<h2>Editeur</h2>
				<hr class="content_separator" />
				<?php require("../controller/editor_controller.php"); ?>
			</div>
			
		</div>
	</div>
	<script>
		initializeEditor();
	</script>
</body>