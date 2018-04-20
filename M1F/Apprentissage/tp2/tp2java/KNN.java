import java.util.*;

/**
 * Created by caronfr1 on 27/03/17.
 */
public class KNN {
    // CONSTANTE
    /** Le nombre de classes de glyphe */
    public static final int CLASS_NB = 10;

    // ATTRIBUTS
    public static List<ArrayList<byte[]>> images;
    static {
        try {
            images = Utils.loadImages("../classe");
        } catch (Exception e) {}
    }

    // METHODES STATIQUES
    /**
     * Renvoie le glyphe spécifié par sa classe et son style
     * @param cls La classe du glyphe (Le chiffre qu'il représente)
     * @param kind Le type de chiffre à extraire
     * @return Le glyphe correspondant
     */
    public static LabelledData getGlyph(int cls, int kind) {
        return new LabelledData(cls, images.get(cls).get(kind));
    }

    /**
     * Affiche le glyphe donné dans une fenêtre graphique adaptée
     *
     * @param glyph Le glyphe à afficher
     */
    public static void print(LabelledData glyph) {
        Utils.viewGlyph(glyph.getGlyph());
    }


    // REQUETES
    /**
     * Question 1.3
     * Renvoie une liste contenant un certain nombre de glyphes.
     * Ces glyphes ont été choisis aléatoirement
     * parmi ceux chargés depuis les classes.
     *
     * @param images L'ensemble des glyphes chargés
     * @param n Le nombre de glyphes de chaque classe, qui seront choisis aléatoirement
     *          parmi ceux chargés précedemment
     * @return Une liste contenant l'ensemble des glyphe choisis
     */
    public ArrayList<LabelledData> getRandomGlyphs(List<ArrayList<byte[]>> images, int n) {
        ArrayList<LabelledData> result = new ArrayList<>();

        for (int i = 0; i < CLASS_NB; ++i) {
            Set<Integer> used = new HashSet<>();

            for (int k = 0; k < n; ++k) {
                int j = 0;
                do {
                    j = (int) (Math.random() * images.get(i).size());
                } while (used.contains(j));

                used.add(j);
                result.add(new LabelledData(i, images.get(i).get(j)));
            }
        }

        return result;
    }

    /**
     * Question 1.3
     * Renvoie une liste contenant un certain nombre d'ensemble de glyphes.
     * Ces ensembles ont été choisis aléatoirement parmi les images chargées, de
     * telle façon que ces ensembles soient disjoints.
     *
     * @param images L'ensemble des glyphes chargés
     * @param n Le nombre de glyphes de chaque classe, qui seront choisis aléatoirement
     *          parmi ceux chargés précedemment
     * @param m Le nombre d'ensembles à générer
     * @return Une liste contenant l'ensemble des ensembles de glyphe crées
     */
    public List<List<LabelledData>> getRandomGlyphSets(List<ArrayList<byte[]>> images, int n, int m) {
        List<List<LabelledData>> result = new ArrayList<>();

        // Constitution des ensembles
        for (int k = 0; k < m; ++k) {
            List<LabelledData> ens = new ArrayList<>();
            for (int i = 0; i < CLASS_NB; ++i) {
                for (int j = 0; j < n; ++j) {
                    int index = (int) (Math.random() * images.get(i).size());
                    ens.add(new LabelledData(i, images.get(i).get(index)));
                    images.get(i).remove(index);
                }
            }

            result.add(ens);
        }

        return result;
    }


    /**
     * Renvoie les k glyphes les plus proches (en terme de distance), parmi
     * une liste de glyphes données, depuis un certain glyphe de référence
     *
     * @param k Le nombre maximal de glyphes les plus proches
     * @param data Les glyphes utilisées pour la recherche
     * @param glyph Le glyphe de référence
     * @return Une liste d'au plus k éléments, contenant les glyphes de data,
     * ayant la plus courte distance jusqu'au glyphe de référence
     */
    public List<LabelledData> getNearestGlyphs(int k, List<LabelledData> data, byte[] glyph) {
        TreeMap<Double, LabelledData> map = new TreeMap<>();

        // Récupération/tri des glyphes en fonction de la distance avec celui de référence
        for (LabelledData ld : data) {
            map.put(getDistance(ld, glyph), ld);
        }

        // Récupération des k glyphes les plus proches
        List<LabelledData> result = new ArrayList<>();
        for (LabelledData ld : map.values()) {
            result.add(ld);
            --k;
            if (k == 0) break;
        }
        return result;
    }


    /**
     * Renvoie la distance définie entre deux glyphes, telle que définie
     * par la fiche de TP.
     *
     * @param glyph1 Le premier glyphe
     * @param glyph2 Le second glyphe
     * @return La distance entre les deux glyphes
     */
    public double getDistance(LabelledData glyph1, byte[] glyph2) {
        int sum = 0;
        for (int k = 0; k < glyph1.getGlyph().length; ++k) {
            int d = glyph1.getGlyph()[k] - glyph2[k];
            sum += d * d;
        }

        return Math.sqrt(sum);
    }

    /**
     * Détermine la classe du glyphe ayant pour plus proches voisins,
     * les glyphes présents dans la liste spécifiée
     *
     * @param neighbors La liste des plus proches voisins du glyphe à classifier
     * @return La classe du glyphe correspondant
     */
    public int checkClass(List<LabelledData> neighbors) {
        // Récupération des classes des voisins
        int[] count = new int[CLASS_NB];
        for (LabelledData ld : neighbors) {
            ++count[ld.getCls()];
        }

        // On prend le max. de vraisemblance
        int cls = 0, max = 0;
        for (int k = 0; k < CLASS_NB; ++k) {
            if (max < count[k]) {
                max = count[k];
                cls = k;
            }
        }

        return cls;
    }

    /**
     * Recherche la classe du glyphe, en se basant sur
     * les glyphes présents dans la liste spécifiée
     *
     * @param k Le nombre maximal de glyphes les plus proches
     * @param data Les glyphes utilisées pour la recherche
     * @param glyph Le glyphe de référence
     * @return La classe du glyphe correspondant
     */
    public int findClass(int k, List<LabelledData> data, byte[] glyph) {
        return checkClass(getNearestGlyphs(k, data, glyph));
    }


    // POINT D'ENTREE
    public static void main(String[] args) throws Exception {
        // Question 2.2
        for (int k = 1; k <= 200; ++k) {
            test(k, 20);
        }
    }


    // TESTS
    public static void test(int k, int modelSize) throws Exception {
        KNN knn = new KNN();

        // -- DONNEES
        List<ArrayList<byte[]>> img = Utils.loadImages("../classe");
        List<LabelledData> testGlyphs = knn.getRandomGlyphs(img, 50);
        List<List<LabelledData>> dataList = knn.getRandomGlyphSets(img, modelSize, 20);

        // -- EXECUTION
        int errors = 0;
        for (LabelledData glyph : testGlyphs) {
            // Test sur un nouveau jeu de données
            for (List<LabelledData> data : dataList) {
                int cls = knn.findClass(k, data, glyph.getGlyph());
                if (cls != glyph.getCls()) {
                    ++errors;
                }

                // Suppression des données utilisées
                for (LabelledData ld : data) {
                    img.get(ld.getCls()).remove(ld.getGlyph());
                }
            }
        }

        double errorRate = ((double) errors) / (testGlyphs.size() * dataList.size());
        System.out.println("Taux d'erreur (k = " + k + ") : " + 100 * errorRate + "%");
    }
}
