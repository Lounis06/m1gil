<!--
	DBConfig : Modélise l'ensemble des paramètres consituant une configuration
        pour interagir avec une base de données distante.

    Une configuration comporte 3 valeurs indispensables :
        - Le DSN (Data Source Name) : Employé pour se connecter au serveur distant,
        il s'agit d'une chaîne propre au SGBD employé, qui contient 
        le nom du serveur et de la base à laquelle se connecter.
        - Le login d'utilisateur employé pour obtenir un accès 
        à la base de données distante
        - Le mot de passe associé au login de connexion.
-->
<?php
    class DBConfig {
        // ATTRIBUTS STATIQUES
        /**
         * Les différents types d'attributs de cette configuration
         */
        public static $keys = array("dsn", "login", "password");

        // METHODES STATIQUES
        /**
         * Charge une configuration depuis le fichier de sauvegarde spécifié
         */
        public static function load($filepath) {
            // Test d'existence du fichier de configuration
            if (!file_exists($filepath) || !is_file($filepath)) {
                throw new InvalidArgumentException(
                    "load : Fichier ".$filepath." inexistant ou inutilisable"
                );
            }

            // Lecture du fichier de configuration
            $tab = file($filepath);
            $keys = DBConfig::$keys;
            $n = count($keys);
            if (count($tab) != $n) {
                throw new Exception("load : Contenu du fichier incohérent");
            }

            // Création de l'objet correspondant
            $cfg = new DBConfig();
            for ($k = 0; $k < $n; ++$k) {
                $key = $keys[$k];
                $line = $tab[$k];

                // Vérification de la cohérence de la ligne
                if (!preg_match("/^".$key."=/", $line)) {
                    throw new Exception("load : ".$key." non défini ou invalide");
                }

                // Extraction de la valeur
                $val = substr($line, strlen($key) + 1, strlen($line));
                $cfg->set($key, rtrim($val));
            }

            return $cfg;
        }

        // ATTRIBUT
        /**
         * Le tableau des attributs de la configuration
         */
        private $attrs;


        // CONSTRUCTEUR
        public function __construct() {
            $this->attrs = array();
        }


        // REQUETES
        /**
         * Renvoie la valeur désirée associée à cette configuration
         */
        public function get($key) {
            if (!in_array($key, DBConfig::$keys)) {
                throw new InvalidArgumentException(
                    "get : Attribut d'objet inexistant"
                );
            }

            return $this->attrs[$key];
        }


        // COMMANDES
        /**
         * Modifie la valeur de l'attribut désiré
         */
        public function set($key, $val) {
            if (!in_array($key, DBConfig::$keys)) {
                throw new InvalidArgumentException(
                    "set : Attribut d'objet inexistant"
                );
            }

            $this->attrs[$key] = $val;
        }

        /**
         * Sauvegarde cette configuration sous forme de chaîne sérialisée,
         * dane le fichier spécifié (tout contenu précédent sera alors écrasé)
         */
        public function save($filepath) {
            // Ouverture (ou création) du fichier de configuration
            $desc = fopen($filepath, "w");

            // Ecriture dans le fichier cible
            $keys = DBConfig::$keys;
            $n = count($keys);
            for ($k = 0; $k < $n; ++$k) {
                // Ecriture du paramètre
                $key = $keys[$k];
                fwrite($desc, $key."=".$this->get($key));

                // Passage à la ligne suivante, s'il reste des paramètres à écrire
                if ($k < $n - 1) {
                    fwrite($desc, "\n");
                }
            }

            // Fermeture du descripteur de fichier : fin de fonction
            fclose($desc);
        }
    }
?>