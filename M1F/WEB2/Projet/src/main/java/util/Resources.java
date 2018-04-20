package util;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Fournit un accès aux fichiers et ressources définis dans le
 * répertoire des resources.
 */
public abstract class Resources {
    // CONSTANTES
    /** Le répertoire contenant tous les fichiers nécessaires */
    public static final String DIRECTORY = "./src/main/resources/";


    // CHARGEMENT DES PROPRIETES
    /** Le bundle contenant les différentes propriétés de l'application */
    public static ResourceBundle PROPERTIES = getProperties();

    /**
     * Renvoie le gestionnaire de propriétés associé à l'application
     */
    public static ResourceBundle getProperties() {
        try {
            Reader reader = new FileReader(new File(DIRECTORY + "sepa.properties"));
            PROPERTIES = new PropertyResourceBundle(reader);
            reader.close();
        } catch (Exception e) {
            throw new AssertionError("Le fichier de propriétés sepa.properties devrait exister");
        }

        return PROPERTIES;
    }
}
