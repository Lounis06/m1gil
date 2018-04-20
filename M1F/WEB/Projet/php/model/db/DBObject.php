<!--
	DBObject.php : Définit le comportement des objets contenus dans la base
	de données.
	Chaque "type d'objet" contenu dans une table de base de données, est doté
	par cette interface d'un objet php, facilitant les opérations de
	modification de ce même objet, par l'applicatif.
-->
<?php
	interface DBObject {
		// REQUETES
		/**
		 * Renvoie le nom de la table de données, qui stocke
		 * les objets similaires à celui-ci.
		 */
		public function getTableName();

		/**
		 * Renvoie un tableau contenant le nom de l'attribut
		 * qui définit l'identifiant de l'objet.
		 */
		public function getIDName();

		/**
		 * Renvoie le tableau de tous les noms d'attributs utilisés
		 * pour cet objet.
		 */
		public function getKeys();

		/**
		 * Renvoie le tableau de tous les noms d'attributs utilisés
		 * pour cet objet qui correspondent à des dates.
		 */
		public function getDateKeys();

		/**
		 * Renvoie la valeur de l'attribut, associée à la clé donnée
		 */
		public function get($key);

		/**
		 * Renvoie un tableau associatif des valeurs de l'attribut,
		 */
		public function getAll();

		/**
		 * Renvoie la valeur de l'identifiant, associé à cet objet
		 */
		public function getID();

		/**
		 * Indique si cet objet (désigné par son identifiant),
		 * existe déjà auprès de la base de données.
		 */
		public function exists($connexion);

		/**
		 * Indique si l'état de l'objet est cohérent, pour une utilisation
		 * dans la base de données.
		 */
		public function isUsable($connexion);


		// COMMANDES
		/**
		 * Modifie la valeur d'un attribut donné, l'attribut à modifier ne
		 * peut être celui de l'identifiant.
		 */
		public function set($key, $val);

		/**
		 * Modifie les valeurs des attributs de l'objet, en employant un 
		 * tableau de couples (clé, valeur).
		 */
		public function setAll($map);

		/**
		 * Met-à-jour l'état actuel de l'objet au sein de sa table
		 * de données associée.
		 * L'objet doit déjà exister dans la base afin d'être modifié
		 */
		public function saveChanges($connexion);

		/**
		 * Inscrit l'objet auprès de la base de données.
		 * A la suite de l'inscription, la valeur de l'identifiant
		 * sera définie pour cet objet.
		 */
		public function register($connexion);

		/**
		 * Désincrit l'objet auprès de la base de données.
		 * A la suite de cette procédure, la valeur de la clé primaire de cet
		 * objet sera supprimée.
		 */
		public function unregister($connexion);
	}

	/**
	 * Implémentation des comportements principaux
	 */
	abstract class AbstractDBObject implements DBObject {
		// METHODES STATIQUES
		/**
		 * Renvoie le résultat d'une requête préparée, renvoyant l'ensemble
		 * des lignes d'une table ayant les valeurs d'attribut spécifiées.
		 * La requête est terminée par la chaîne extra, permettant de définir
		 * des contraintes supplémentaires (ordre, groupes, ...)
		 */
		public static function requestTable($connexion, $tableName, $attrs, $extra) {
			// Création de la requête de consultation
			$sql = "select * from ".$tableName;

			$values = array();
			foreach ($attrs as $key => $val) {
				if (count($values) == 0) {
					$sql = $sql." where ";
				} else {
					$sql = $sql." and ";
				}

				// On regarde si la valeur est null.
				if ($val === null) {
					$sql = $sql.$key." is null";
				} else {
					$sql = $sql.$key." = ?";
					array_push($values, $val);
				}
			}

			// Ajout des déclarations suffixe supplémentaires
			if ($extra != "") {
				$sql = $sql." ".$extra.";";
			}

			// Exécution de la requête
			$req = $connexion->prepare($sql);
			$req->execute($values);

			// Renvoi de l'objet requête, après exécution.
			return $req;
		}

		/**
		 * Renvoie un tableau contenant toutes les lignes d'une
		 * table de données, qui contiennent les couples 
		 * (nom d'attribut, valeur) contenus le tableau d'attributs fourni.
		 */
		public static function extractFromTable($connexion, $tableName, $attrs) {
			$req = AbstractDBObject::requestTable(
				$connexion, $tableName, $attrs, "");
			return $req->fetchAll();
		}

		/**
		 * Indique s'il existe une ligne de table de données,
		 * contenant les couples (nom d'attribut, valeur) contenus
		 * le tableau d'attributs fourni.
		 */
		public static function inTable($connexion, $tableName, $attrs) {
			$req = AbstractDBObject::requestTable(
				$connexion, $tableName, $attrs, "");
			return $req->rowCount() != 0;
		}

		/**
		 * Renvoie le prochain ID utilisable dans la table spécifiée
		 */
		public static function nextInsertID($connexion, $tableName, $idName) {
			// Création de la requête
			$col = "max(".$idName.")";
			$sql = "select ".$col." from ".$tableName.";";

			// Exécution de la requête
			$res = $connexion->query($sql);

			// Renvoi de la plus petite valeur disponible
			while ($line = $res->fetch()) {
				return $line[$col] + 1;
			}
			// Si la table ne contient aucune ligne, renvoi de la valeur 1.
			return 1;
		}

		/**
		 * Indique si le format d'une date est correct
		 */
		public static function isDateUsable($date) {
			$day = "(0[1-9]|[1-2][0-9]|3[0-1])";
			$month = "(0[1-9]|1[0-2])";
			$year = "[0-9]{4}";
			$pattern = "/^".$day."\/".$month."\/".$year."$/";

			return preg_match($pattern, $date);
		}

		/**
		 * Indique si le format mySQL d'une date est correct
		 */
		public static function isMySQLDateUsable($date) {
			$day = "(0[1-9]|[1-2][0-9]|3[0-1])";
			$month = "(0[1-9]|1[0-2])";
			$year = "[0-9]{4}";
			$pattern = "/^".$year."\-".$month."\-".$day."$/";

			return preg_match($pattern, $date);
		}


		// ATTRIBUTS
		/**
		 * Le tableau contenant les différents couples (clé, valeur)
		 * correspondant aux caractéristiques de l'objet.
		 */
		public $attrs;


		// CONSTRUCTEUR
		function __construct() {
			$this->attrs = array();

			// Initialisation du tableau d'attributs
			$keys = $this->getKeys();
			$n = count($keys);

			for ($k = 0; $k < $n; ++$k) {
				$this->attrs[$keys[$k]] = null;
			}
		}


		// REQUETES
		public function get($key) {
			if (!in_array($key, $this->getKeys())) {
				throw new InvalidArgumentException("get : Attribut d'objet inexistant");
			}

			if (in_array($key, $this->getDateKeys())) {
				// Conversion format mySQL -> format standard
				$tab = explode("-", $this->attrs[$key]);
				$val = $tab[2]."/".$tab[1]."/".$tab[0];
				return $val;
			}

			return $this->attrs[$key];
		}

		public function getAll() {
			$tab = array();
			foreach ($this->attrs as $key => $val) {
				$tab[$key] = $val;
			}

			return $tab;
		}

		public function getID() {
			return $this->get($this->getIDName());
		}

		public function exists($connexion) {
			$tab = array($this->getIDName() => $this->getID());

			return AbstractDBObject::inTable($connexion, $this->getTableName(), $tab);
		}


		// COMMANDES
		public function set($key, $val) {
			if (!in_array($key, $this->getKeys())) {
				throw new InvalidArgumentException(
					"set : Attribut d'objet inexistant"
				);
			}
			if ($key == $this->getIDName()) {
				throw new InvalidArgumentException(
					"set : Modification d'identifiant interdite"
				);
			}

			if (in_array($key, $this->getDateKeys())) {
				// Vérification du format souhaité
				if (AbstractDBObject::isDateUsable($val)) {
					// Conversion format standard -> format mySQL
					$tab = explode("/", $val);
					$val = $tab[2]."-".$tab[1]."-".$tab[0];
				} else {
					if (!AbstractDBObject::isMySQLDateUsable($val)) {
						throw new InvalidArgumentException(
							"set : Format de date incorrect"
						);
					}
				}
			}

			$this->attrs[$key] = $val;
		}
		public function setAll($map) {
			// Contrôle des préconditions
			foreach ($map as $key => $val) {
				if (!in_array($key, $this->getKeys())) {
					throw new InvalidArgumentException(
						"set : Attribut d'objet inexistant"
					);
				}
				if ($key == $this->getIDName()) {
					throw new InvalidArgumentException(
						"set : Modification d'identifiant interdite"
					);
				}
			}

			// Modification des valeurs
			foreach ($map as $key => $val) {
				$this->set($key, $val);
			}
		}

		public function saveChanges($connexion) {
			// Vérification des préconditions
			if (!$this->exists($connexion)) {
				throw new InvalidArgumentException(
					"saveChanges : Sauvegarde impossible car l'objet n'est pas identifié"
				);
			}
			if (!$this->isUsable($connexion)) {
				throw new InvalidArgumentException(
					"saveChanges : L'objet est inutilisable pour la base de données"
				);
			}

			// Création de la requête UPDATE
			$sql = "update ".$this->getTableName()." set ";
			$values = array();

			// --- Ajout des attributs à modifier ---
			$idn = $this->getIDName();
			$keys = $this->getKeys();

			$n = count($keys);
			$sep = false;
			for ($k = 0; $k < $n; ++$k) {
				$key = $keys[$k];

				if ($key != $idn) {
					if ($sep) {
						$sql = $sql.", ";
					} else {
						$sep = true;
					}
					$sql = $sql.$key." = ?";
					array_push($values, $this->attrs[$key]);
				}
			}

			// --- Ajout de la condtion (égalité avec l'identifiant)
			$sql = $sql." where ".$idn." = ?;";
			array_push($values, $this->getID());

			// Execution de la requete
			$req = $connexion->prepare($sql);
			$req->execute($values);
		}

		public function register($connexion) {
			// Vérification des préconditions
			if ($this->exists($connexion)) {
				throw new InvalidArgumentException(
					"register : L'objet est déjà associé à une valeur"
				);
			}
			if (!$this->isUsable($connexion)) {
				throw new InvalidArgumentException(
					"register : L'objet est inutilisable pour la base de données"
				);
			}

			// Création de la requête INSERT
			$sql = "insert into ".$this->getTableName()." values( ";
			$values = array();

			// Définition de l'identifiant de l'objet
			$idn = $this->getIDName();
			$this->attrs[$idn] = AbstractDBObject::nextInsertID($connexion,
				$this->getTableName(), $this->getIDName());

			// --- Ajout des valeurs à insérer ---
			$keys = $this->getKeys();
			$n = count($keys);

			for ($k = 0; $k < $n; ++$k) {
				$key = $keys[$k];
				$sql = $sql."?";
				if ($k < $n - 1) {
					$sql = $sql.", ";
				}

				array_push($values, $this->attrs[$key]);
			}
			$sql = $sql.");";

			// Execution de la requete
			$req = $connexion->prepare($sql);
			$req->execute($values);
		}

		public function unregister($connexion) {
			// Vérification de l'existance de l'objet
			if (!$this->exists($connexion)) {
				throw new InvalidArgumentException(
					"unregister : L'objet n'est pas inscrit auprès de la base de données"
				);
			}

			// Requête de suppression
			$sql = "delete from ".$this->getTableName();
			$sql = $sql." where ".$this->getIDName()." = ?;";

			// Execution de la requête
			$req = $connexion->prepare($sql);
			$req->execute(array($this->getID()));
		}
	}
?>