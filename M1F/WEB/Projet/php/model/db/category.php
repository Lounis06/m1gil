<!--
	category.php : Définit un objet php modélisant une catégorie d'articles.
	Permet de simplifier les interactions entre la base de données et l'applicatif.
-->
<?php
	require_once("DBObject.php");
	require_once("article.php");

	class Category extends AbstractDBObject {
		// CONSTANTES
		/** La liste des noms d'attribut */
		public static $category_keys = array("idcat", "name", "parent");

		/** Le nom de la table des catégories */
		public static $category_table = "category";

		/** Le nom de l'attribut d'identification */
		public static $category_id = "idcat";

		/** La description des attributs d'une catégorie */
		public static $category_keys_desc = array(
			"idcat" => "Code",
			"name" => "Nom de la catégorie",
			"parent" => "Code de la catégorie parente"
		);


		// METHODES STATIQUES
		/**
		 * Indique si le couple (nom, id_parent) est disponible au sein de
		 * la base de données.
		 */
		public static function isAvailable($connexion, $name, $parent) {
			$crits = array("name" => $name, "parent" => $parent);

			return !AbstractDBObject::inTable(
				$connexion,
				Category::$category_table,
				$crits
			);
		}

		/**
		 * Indique si le nom entré correspond bien à la syntaxe d'un nom
		 */
		public static function isNameUsable($name) {
			// Nom d'une catégorie : Série de mots séparés par un tiret
			// apostrophe, et espacés par des "underscore" ou des espaces.
			$pattern = "/^([a-zA-Zàéèùä]+['-_]?[_ ]*)+$/";

			return preg_match($pattern, $name);
		}

		/**
		 * Indique si le numéro de catégorie parent donné, 
		 * correspond bien à un numéro de catégorie existante.
		 */
		public static function isParentUsable($connexion, $parent) {
			return $parent == null || AbstractDBObject::inTable(
				$connexion, 
				Category::$category_table,
				array("idcat" => $parent)
			);
		}

		/**
		 * Charge les catégories depuis la base de données 
		 * spécifiée, qui correspondent à une liste de critères,
		 * défini par des couples (nom d'attribut, valeur).
		 */
		public static function load($connexion, $attrs) {
			return Category::loadExtended($connexion, $attrs, "");
		}

		/**
		 * Charge les catégories depuis la base de données 
		 * spécifiée, qui correspondent à une liste de critères,
		 * défini par des couples (nom d'attribut, valeur).
		 * Des critères supplémentaires représentées par la variable extra,
		 * permettent de rajouter un suffixe à la requête
		 */
		public static function loadExtended($connexion, $attrs, $extra) {
			// Chargement des profils
			$req = AbstractDBObject::requestTable(
				$connexion, 
				Category::$category_table, 
				$attrs,
				$extra
			);
			$lines = $req->fetchAll();

			// Initialisation
			$keys = Category::$category_keys;

			// Conversion en tableau de catégories
			$tab = array();
			for ($i = 0; $i < count($lines); ++$i) {
				$line = $lines[$i];
				$cat = new Category();
				
				for ($j = 0; $j < count($keys); ++$j) {
					$key = $keys[$j];
					$cat->attrs[$key] = $line[$key];
				}
				array_push($tab, $cat);
			}

			// Renvoi du tableau des catégories
			return $tab;
		}


		// REQUETES
		public function getTableName() {
			return Category::$category_table;
		}

		public function getIDName() {
			return Category::$category_id;
		}

		public function getKeys() {
			$keys = array();
			$n = count(Category::$category_keys);

			for ($k = 0; $k < $n; ++$k) {
				array_push($keys, Category::$category_keys[$k]);
			}
			return $keys;
		}

		public function getDateKeys() {
			return array();
		}

		public function isUsable($connexion) {
			if (!Category::isNameUsable($this->get("name"))) {
				return false;
			}
			if (!Category::isParentUsable($connexion, $this->get("parent"))) {
				return false;
			}

			return true;
		}

		/**
		 * Renvoie le tableau des catégories enfants de cette catégorie.
		 */
		public function getChildren($connexion) {
			// Vérification de l'existance
			if (!$this->exists($connexion)) {
				throw new InvalidArgumentException(
					"getChildren : La catégorie n'existe pas dans la base de données"
				);
			}

			// Extraction des données
			$children = Category::load(
				$connexion, 
				array("parent" => $this->getID())
			);

			// Retour du tableau des catégories
			return $children;
		}

		/**
		 * Renvoie la profondeur de la catégorie, i.e le nombre de catégories
		 * qui lui sont supérieures hiérarchiquement.
		 */
		public function getDepth($connexion) {
			return count($this->getTree($connexion)) - 1;
		}

		/**
		 * Renvoie le tableau des catégories, représentant 
		 * l'arbre généralogique de cette catégorie.
		 * La clé correspond au niveau d'élévation dans cette arbre
		 * (0 : elle-même, 1: parent, 2 : grand-parent ...)
		 */
		public function getTree($connexion) {
			// Vérification de l'existance
			if (!$this->exists($connexion)) {
				throw new InvalidArgumentException(
					"getTree : La catégorie n'existe pas dans la base de données"
				);
			}

			// Initialisation
			$tree = array($this);
			$parentId = $this->get("parent");

			// Calcul de l'arborescence
			while ($parentId != null) {
				// Lecture de la BDD
				$tab = Category::load($connexion, 
					array($this->getIDName() => $parentId));

				// Récupération de la catégorie parent
				if (count($tab) != 1) {
					throw new InvalidArgumentException(
						"getTree : BDD incohérente"
					);
				}
				$parent = $tab[0];

				// Elévation dans l'arbre
				array_push($tree, $parent);
				$parentId = $parent->get("parent");
			}

			return $tree;
		}

		/**
		 * Renvoie le nom complet de la catégorie, incluant
		 * la descendance complète menant de la racine à cette catégorie.
		 */
		public function getFullName($connexion) {
			// Obtention de l'arborescence
			try {
				$tree = $this->getTree($connexion);
			} catch (Exception $e) {
				throw $e;
			}

			// Création du nom complet
			$str = "";
			$n = count($tree);
			for ($k = 0; $k < $n; ++$k) {
				$str = $tree[$k]->get("name").$str;

				if ($k < $n - 1) {
					$str = '/'.$str;
				}
			}

			return $str;
		}

		/**
		 *	Surcharge de la fonction de supression : Supprime les sous-catégories qui
		 *  lui sont associées, ainsi que les articles qu'il contient.
		 */
		public function unregister($connexion) {
			foreach ($this->getChildren($connexion) as $child) {
				$child->unregister($connexion);

				$articles = Article::load($connexion, array("category" => $this->getID()));
				foreach ($articles as $art) {
					$art->unregister($connexion);
				}
			}
			parent::unregister($connexion);
		}
	}
?>