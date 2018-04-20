<!--
	article.php : Définit un objet php modélisant un article de recherche.
	Permet de simplifier les interactions 
	entre la base de données et l'applicatif.
-->
<?php
	require_once("DBObject.php");
	require_once("user.php");
	require_once("category.php");

	class Article extends AbstractDBObject {
		// CONSTANTES
		/** La liste des noms d'attribut */
		public static $article_keys = array("idart", "title", "publishingDate",
			"author", "category", "abstract", "keywords", "content");

		/** Le nom de la table des articles */
		public static $article_table = "article";

		/** Le nom de l'attribut d'identification */
		public static $article_id = "idart";


		// METHODES STATIQUES
		/**
		 * Charge les articles depuis la base de données 
		 * spécifiée, qui correspondent à une liste de critères,
		 * défini par des couples (nom d'attribut, valeur).
		 */
		public static function load($connexion, $attrs) {
			return Article::loadExtended($connexion, $attrs, "");
		}

		/**
		 * Charge les articles depuis la base de données 
		 * spécifiée, qui correspondent à une liste de critères,
		 * défini par des couples (nom d'attribut, valeur).
		 * Des critères supplémentaires représentées par la variable extra,
		 * permettent de rajouter un suffixe à la requête
		 */
		public static function loadExtended($connexion, $attrs, $extra) {
			// Chargement des profils
			$req = AbstractDBObject::requestTable(
				$connexion, 
				Article::$article_table, 
				$attrs,
				$extra
			);
			$lines = $req->fetchAll();

			// Initialisation
			$keys = Article::$article_keys;

			// Conversion en tableau d'articles
			$tab = array();
			for ($i = 0; $i < count($lines); ++$i) {
				$line = $lines[$i];
				$art = new Article();
				
				for ($j = 0; $j < count($keys); ++$j) {
					$key = $keys[$j];
					$art->attrs[$key] = $line[$key];
				}
				array_push($tab, $art);
			}

			// Renvoi du tableau des articles
			return $tab;
		}


		// REQUETES
		public function getTableName() {
			return Article::$article_table;
		}

		public function getIDName() {
			return Article::$article_id;
		}

		public function getKeys() {
			$keys = array();
			$n = count(Article::$article_keys);

			for ($k = 0; $k < $n; ++$k) {
				array_push($keys, Article::$article_keys[$k]);
			}
			return $keys;
		}

		public function getDateKeys() {
			return array("publishingDate");
		}

		public function isUsable($connexion) {
			// Format de date correct
			if(!User::isMySQLDateUsable($this->attrs["publishingDate"])) {
				return false;
			}
			// Auteur existant
			if(!AbstractDBObject::inTable( 
					$connexion, 
					User::$user_table, 
					array("idusr" => $this->get("author"))
				)) {
				return false;
			}
			// Catégorie existante
			if(!AbstractDBObject::inTable( 
					$connexion, 
					Category::$category_table, 
					array("idcat" => $this->get("category"))
				)) {
				return false;
			}

			return true;
		}
	}
?>