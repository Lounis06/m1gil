<!--
	user.php : Définit un objet php modélisant un utilisateur.
	Permet de simplifier les interactions 
	entre la base de données et l'applicatif.
-->
<?php
	require_once("DBObject.php");

	class User extends AbstractDBObject {
		// CONSTANTES
		/** La liste des noms d'attribut */
		public static $user_keys = array("idusr", "login", "mail", "password",
			"firstName", "lastName", "birthDate", "telephone", "isAdmin");

		/** La liste des attributs éditables pour un administrateur */
		public static $user_editable_keys = array("login", "mail", "password", 
			"firstName", "lastName", "birthDate", "telephone");

		/** Le nom de la table des utilisateurs */
		public static $user_table = "users";

		/** Le nom de l'attribut d'identification */
		public static $user_id = "idusr";

		/** La description des différents attributs d'un utilisateur */
		public static $user_keys_desc = array(
			"idusr" => "ID",
			"login" => "Login",
			"mail" => "Adresse Mail",
			"password" => "Mot de passe",
			"firstName" => "Prénom",
			"lastName" => "Nom",
			"birthDate" => "Date de naissance",
			"telephone" => "N° de contact",
			"isAdmin" => "Admin ?"
		);


		// METHODES STATIQUES
		/**
		 * Indique si le nom d'utilisateur est déjà utilisé disponible
		 */
		public static function isLoginAvailable($connexion, $login) {
			$crits = array("login" => $login);

			return !AbstractDBObject::inTable(
				$connexion, 
				User::$user_table,
				$crits
			);
		}

		/**
		 * Indique si l'adresse mail utilisée est cohérente
		 */
		public static function isMailUsable($subject) {
			return filter_var($subject, FILTER_VALIDATE_EMAIL);
		}

		/**
		 * Indique si le nom entré correspond bien à la syntaxe d'un nom
		 */
		public static function isNameUsable($name) {
			$pattern = "/^[a-zA-Z]+$/";

			return preg_match($pattern, $name);
		}

		/**
		 * Indique si le numéro de téléphone est utilisable
		 */
		public static function isTelephoneUsable($number) {
			$pattern = "/^0[1-9]([-. ]?[0-9]{2}){4}$/";

			return preg_match($pattern, $number);
		}

		

		/**
		 * Charge les profils d'utilisateur depuis la base de données 
		 * spécifiée, qui correspondent à une liste de critères,
		 * défini par des couples (nom d'attribut, valeur).
		 */
		public static function load($connexion, $attrs) {
			return User::loadExtended($connexion, $attrs, "");
		}

		/**
		 * Charge les profils d'utilisateur depuis la base de données 
		 * spécifiée, qui correspondent à une liste de critères,
		 * défini par des couples (nom d'attribut, valeur).
		 * Des critères supplémentaires représentées par la variable extra,
		 * permettent de rajouter un suffixe à la requête
		 */
		public static function loadExtended($connexion, $attrs, $extra) {
			// Chargement des profils
			$req = AbstractDBObject::requestTable(
				$connexion, 
				User::$user_table, 
				$attrs,
				$extra
			);
			$lines = $req->fetchAll();

			// Initialisation
			$keys = User::$user_keys;

			// Conversion en tableau d'utilisateurs
			$tab = array();
			for ($i = 0; $i < count($lines); ++$i) {
				$line = $lines[$i];
				$usr = new User();
				
				for ($j = 0; $j < count($keys); ++$j) {
					$key = $keys[$j];
					$usr->attrs[$key] = $line[$key];
				}
				array_push($tab, $usr);
			}

			// Renvoi du tableau des utilisateurs
			return $tab;
		}


		// CONSTRUCTEUR
		public function __construct($admin = false) {
			parent::__construct();
			$this->attrs["isAdmin"] = $admin;
		}

		// REQUETES
		public function getTableName() {
			return User::$user_table;
		}

		public function getIDName() {
			return User::$user_id;
		}

		public function getKeys() {
			$keys = array();
			$n = count(User::$user_keys);

			for ($k = 0; $k < $n; ++$k) {
				array_push($keys, User::$user_keys[$k]);
			}
			return $keys;
		}

		public function getDateKeys() {
			return array("birthDate");
		}

		public function isUsable($connexion) {
			if (!User::isTelephoneUsable($this->get("telephone"))) {
				return false;
			}
			if (!User::isMailUsable($this->get("mail"))) {
				return false;
			}
			if (!User::isNameUsable($this->get("lastName"))
				|| !User::isNameUsable($this->get("firstName"))) {
				return false;
			}
			if(!User::isMySQLDateUsable($this->attrs["birthDate"])) {
				return false;
			}
			return true;
		}

		/**
		 * Indique si l'utilisateur possède les droits d'administration
		 */
		public function isAdmin() {
			return $this->get("isAdmin");
		}
	}
?>