<!--
	viewer_category_controller.php : Gère le contrôleur de 
	la page de consultation associé aux informations des catégories.
-->
<?php 
	require_once("../model/db/DBConnector.php");
	require_once("../model/db/article.php");
	require_once("../model/db/user.php");

	// Chargement de la catégorie
	$racine = !isset($_GET['idcat']);
	if (!$racine) {
		$cats = Category::load($DBC->getConnexion(), array("idcat" => $_GET['idcat']));
		if (count($cats) != 1) {
			$_SESSION['note'] = "Aucune catégorie n'a été trouvée pour ce code";
			header("location:home.php");
		}
	}

	/*
	 * Chargement des informations sur la catégorie
	 *
	 * Dans le cas de la catégorie racine, on ne charge que les sous-catégories.
	 */
	try {
		$DBC = new DBConnector();
		if ($racine) {
			$children = Category::load($DBC->getConnexion(), array("parent" => null));
		} else {
			$cat = $cats[0];
			$tree = $cat->getTree($DBC->getConnexion());
			$children = $cat->getChildren($DBC->getConnexion());
			$articles = Article::loadExtended(
				$DBC->getConnexion(), 
				array("category" => $cat->getID()),
				"order by publishingDate desc"
			);
			$_SESSION['page'] = $_SESSION['page']."?idcat=".$_GET['idcat'];
		}
	} catch (Exception $e) {
		$_SESSION['error'] = "Erreur lors du chargement des information sur la catégorie ".$cat->getID();
		header("location:home.php");
	}
?>

<div id="categoryInfos">
	<!-- Affichage des informations sur la catégorie -->
	<h2 id="category_title"> <?php echo $racine ? "Catéagorie racine" : $cat->get('name'); ?></h2>
	<div id="category_infos">
		<p id="category_code"> Code de catégorie : 
			<?php if ($racine) { 
				echo "-"; 
			} else { 
				echo $cat->getID(); 
			}?>
		</p>
		<p id="category_path"> Chemin complet :
			<?php if ($racine) { 
				echo "/";
			} else {
				$n = count($tree);
				for ($k = $n - 1; $k >= 0; --$k) {
					$c = $tree[$k];
					if ($k == 0) {
						echo '/ '.$c->get('name');
					} else {
				?>
					/<a href=<?php echo "viewer.php?idcat=".$c->get('idcat'); ?>>
						<?php echo $c->get('name'); ?>
					</a>
				<?php }
				} 
			} ?>
		</p>
	</div>
	<hr class="content_separator" />

	<!-- Affichage de la liste des sous-catégories -->
	<?php if (count($children) > 0) { ?>
		<h2> Liste des sous-catégories </h2>
		<ul id="subcategories">
		<?php foreach ($children as $child) { ?>
			<li>
				<h3><a href=<?php echo "viewer.php?idcat=".$child->getID(); ?>>
				<?php echo $child->get('name'); ?>
				</a></h3>
			</li>
		<?php } ?>
		</ul>
		<hr class="content_separator" />
	<?php } ?>

	<!-- Affichage de la liste des articles -->
	<?php if (!$racine && count($articles) > 0) { ?>
		<h2> Liste des articles disponibles </h2>
		<ul id="articles">
		<?php foreach ($articles as $art) { ?>
			<li>
				<h3><a href=<?php echo "viewer.php?idart=".$art->getID(); ?>>
				<?php echo $art->get('title'); ?>
				</a></h3>
				<p>
					Ecrit par
					<?php
						$author = User::load(
							$DBC->getConnexion(), 
							array("idusr" => $art->get("author"))
						)[0];

						echo $author->get("firstName")." ".$author->get("lastName");
					?>
					(<?php echo $author->get("login"); ?>)
				</p>
			</li>
		<?php } ?>
		</ul>
		<hr class="content_separator" />
	<?php } ?>
</div>
