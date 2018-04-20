<!--
	DBInitializer.php : Permet d'exécuter des instructions présentes
    dans des fichiers SQL externes, afin d'initialiser correctement le SGBD.

    /!\ Cet objet n'est à utiliser que lors de la configuration
    du site, afin de créer les tables de données.
-->
<?php
    class DBInitializer {
        // FONCTION
        /**
         * Exécute le contenu du fichier SQL spécifié.
         */
        function exec($connexion, $filepath) {
            // Test d'existence du fichiers de construction requis
            if (!file_exists($filepath) || !is_file($filepath)) {
                throw new InvalidArgumentException(
                    "initializeDatabase : Fichier ".$filepath." inexistant ou inutilisable"
                );
            }
            /*
            // Lecture du fichier contenant le modèle de BDD
            $desc = fopen($filepath, "r");
            $sql = "";
            while ($ligne = fgets($desc)) {
                $sql = $sql.$ligne;
            }
            fclose($desc);*/
            $sql = file_get_contents($filepath);

            // Création de la BDD
            $status = $connexion->exec($sql);
            if ($status === false) {
                throw new Exception(
                    "initializeDatabase : Echec d'exécution de la requête SQL associé au fichier"
                );
            }
        }
    }
?>
