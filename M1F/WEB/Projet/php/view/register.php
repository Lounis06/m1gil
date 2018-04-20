<!--
	register.php : Page d'inscription du site
-->

<?php
	session_start();

	$_SESSION['dbconfig'] = "../../dbconfig.ini";
	$_SESSION['page'] = $_SERVER['PHP_SELF'];
?>

<!DOCTYPE html>

<head>
	<title>SciMS - Inscription</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="../../css/register.css" media="all">
	<script type="text/javascript" src="../../js/register.js"></script>
</head>

<body>
	<!-- Couche SUPERIEURE : Haut de page et affichage des erreurs/notifications -->
	<?php require("components/top_layouts.php"); ?>

	<!-- Couche CENTRALE : contenu principal du site -->
	<div id="center_layout" class="layout">
		<div class="layout_content">
			<?php require("../controller/menu_bar.php"); ?>

			<div id="main_content">
				<h2>Formulaire d'inscription</h2>
				<hr />
				<p> 
					Remplissez les différents champs afin de pouvoir vous inscrire sur le site.
					L'inscription vous permet d'écrire et de publier vos articles gratuitement sur le site, afin qu'ils puissent être consultés par les autres utilisateurs.
				</p>

				<?php require("../controller/register_controller.php"); ?>

				<p>
				Vous devez remplir toutes les champs de ce formulaire pour
				pouvoir compléter l'incription.
				</p>
			</div>
		</div>
	</div>
</body>