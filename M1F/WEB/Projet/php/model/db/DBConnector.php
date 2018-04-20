<!--
	DBConnector.php : Crée un objet permettant d'établir une liaison avec une base
    de données MySQL distante.

    Cette connexion sera établie au moyen des informations de connexion 
    fournis par le fichier désigné par les informations de la session 
    actuelle (variable de session 'dbconfig').

    /!\ La connexion requiert que la variable $_SESSION['dbconfig'] soit
    correctement définie.

    La destruction du connecteur détruira également la connexion qu'il
    avait établie.
-->
<?php
    require_once("DBConfig.php");

    interface IDBConnector {
        // REQUETES
        /**
         * Indique si l'objet dispose d'une connexion à la base de données
         */
        public function isConnected();

        /**
         * Renvoie la connexion existante de ce connecteur
         */
        public function getConnexion();


        // COMMANDES
        /**
         * Force la fermeture de la connexion existante 
         * entre cet objet et la base de données.
         */
        public function disconnect();
    }


    class DBConnector implements IDBConnector {
        // ATTRIBUTS
        /** La connexion établie par cet objet **/
        private $connexion;


        // CONSTRUCTEUR
        public function __construct() {
            if (!isset($_SESSION['dbconfig'])) {
                throw new Exception(
                    "connect : Le fichier de configuration n'est pas défini"
                );
            }

            try {
                $cfg = DBConfig::load($_SESSION['dbconfig']);
                $this->connexion = new PDO(
                    $cfg->get("dsn"), 
                    $cfg->get("login"), 
                    $cfg->get("password")
                );
            } catch (Exception $e) {
                throw new Exception("connect : ".$e->getMessage());
            }
        }


        // REQUETES
        public function isConnected() {
            return $this->connexion != null;
        }

        public function getConnexion() {
            if (!$this->isConnected()) {
                throw Exception("getConnexion : connexion inexistante");
            }

            return $this->connexion;
        }


        // COMMANDES
        public function disconnect() {
            $this->connexion = null;
        }
    }
?>
