<!--
	menu_bar.php : Composant représentant le menu de navigation gauche,
	présent sur toutes les pages du site.
-->
<nav id="main_menu">
	<!-- Ajout du sous-menu d'authentification -->
	<?php require("../controller/components/login_bar.php"); ?>

	<div id="menu_links">
		<hr class="main_menu_separator" />

		<!-- Lien vers l'interface d'administration -->
		<?php
			if (isset($_SESSION['user']) && $_SESSION['user']['isAdmin']) {
		?>
			<a href="admin.php"><h2> Administration </h2></a>
			<hr class="main_menu_separator" />
		<?php
			}
		?>

		<!-- Lien vers les interface d'édition -->
		<?php
			if (isset($_SESSION['user']) && !$_SESSION['user']['isAdmin']) {
		?>
			<a href="editor.php"><h2> Editeur </h2></a>
			<hr class="main_menu_separator" />
		<?php
			}
		?>


		<!-- Lien vers l'accueil -->
		<a href="home.php"><h2> Accueil </h2></a>
		<hr class="main_menu_separator" />

		<div id="categories">
			<h2><a href="viewer.php"> Catégories </a></h2>
			<ul>
			<?php
				require_once("../model/db/DBConnector.php");
				require_once("../model/db/category.php");
				$DBC = new DBConnector();
				$cats = Category::load($DBC->getConnexion(), array("parent" => null));

				foreach ($cats as $cat) {
					$url = "viewer.php?idcat=".$cat->get("idcat");
			?>
				<li><a href=<?php echo $url; ?>><?php echo $cat->get("name"); ?></a></li>
			<?php
				}
			?>
			</ul>
		</div>
		<hr class="main_menu_separator" />
	</div>
</nav>