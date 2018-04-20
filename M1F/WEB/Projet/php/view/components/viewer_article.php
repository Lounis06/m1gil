<!--
	viewer_article.php : Gère la partie de la vue associée à l'affichage d'un article.
-->
<?php 
	require_once("../model/db/DBConnector.php");
	require_once("../model/db/article.php");
	require_once("../model/db/user.php");

	// Vérification des arguments de la requête
	if (!isset($_GET['idart'])) {
		$_SESSION['error'] = "Aucune article n'est utilisable";
		header("location:home.php");
	}

	// Chargement de l'article'
	$arts = Article::load($DBC->getConnexion(), array(Article::$article_id => $_GET['idart']));
	if (count($arts) != 1) {
		$_SESSION['note'] = "L'article demandé n'existe pas";
		header("location:home.php");
	}

	// Chargement des informations sur la catégorie
	try {
		$DBC = new DBConnector();
		$art = $arts[0];

		$cat = Category::load(
			$DBC->getConnexion(), 
			array(Category::$category_id => $art->get("category"))
		)[0];

		$author = User::load(
			$DBC->getConnexion(), 
			array(User::$user_id => $art->get("author"))
		)[0];

		$tree = $cat->getTree($DBC->getConnexion());
	} catch (Exception $e) {
		$_SESSION['error'] = "Erreur lors du chargement des informations sur la catégorie ".$art->getID();
		header("location:home.php");
	}

	$_SESSION['page'] = $_SESSION['page']."?idart=".$_GET['idart'];
?>

<article>
	<!-- Affichage des informations sur l'article -->
	<h1 id="article_title"><?php echo $art->get('title'); ?></h1>
	<div id="article_infos">
		<div id="right_infos">
			<p id="article_date"> 
				De <?php echo $author->get("firstName")." ".$author->get("lastName"); ?>
				(<?php echo $author->get("login"); ?>)
				, le
				<?php echo $art->get('publishingDate'); ?>
			</p>
			<p id="article_code"> Code article : <?php echo $art->getID(); ?></p>
		</div>
		<div id="left_infos">
			<p id="article_path"> Chemin : 
				<?php 
				$n = count($tree);
				for ($k = $n - 1; $k >= 0; --$k) {
					$c = $tree[$k];
				?>
					<a href=<?php echo "viewer.php?idcat=".$c->get('idcat'); ?>>
						<?php echo $c->get('name'); ?>
					</a>/
				<?php } ?>
				<?php echo $art->get('title'); ?>
			</p>
			<p id="article_keywords"> Mot(s)-clé(s) : 
				<?php echo $art->get("keywords"); ?>
			</p>
		</div>
	</div>
	<hr class="content_separator" />

	<!-- Affichage de l'abstract -->
	<div id="article_abstract">
		<h2 id="abstract_title">Abstract</h2>
		<p id="abstract_content"><?php echo $art->get("abstract"); ?></p>
	</div>
	<hr class="content_separator" />

	<!-- Affichage du contenu de l'article -->
	<div id="article_content">
		<?php echo $art->get("content"); ?>
	</div>
</article>