package fr.rouen.mastergil.TDD;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by rudy on 12/03/17.
 */
public class BowlingCalculatorTest {


    /**
     * Exemple de tests :
     * <p/>
     * #1 : score avec que des valeurs entières en frame 1
     * #2 : score avec que des valeurs entières en frame 1 et frame 2
     * #3 : score avec que des valeurs entières en frame 1 et frame 2 et 1 spare
     * #4 : score avec que des valeurs entières en frame 1 et frame 2 et 2 spare
     * #5 : score avec que des valeurs entières en frame 1 et frame 2 et 1 strike
     * ...
     * autre exemple :
     * <p/>
     * #1 : score avec que des valeurs à 1 en frame 1
     * #2 : score avec que des valeurs à 2 en frame 1
     * #3 : score avec que des valeurs à 1 en frame 1 et frame 2
     * #4 : score avec que des valeurs à 2 en frame 1 et frame 2
     * #5 : score avec que des valeurs à 2 en frame 1 et frame 2 et 1 spare
     * <p/>
     * S'inspirer des presets du calculator http://warwickbowling.50webs.com/calculator.html APRES les cas "basiques"
     */

    private BowlingCalculator bowlingCalculator;

    @Before
    public void setUp() throws Exception {
        bowlingCalculator = new BowlingCalculator();
    }

    /**
     * Test de calcul du score avec aucun point marqué durant la partie.
     */
    @Test
    public void scoreTest1() {
        // GIVEN
        String[] examples = {
            "--;--;--;--;--;--;--;--;--;--"
        };
        int[] results = {0};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul du score lorsque les points sont marqués,
     * pendant le premier lancer d'une ou plusieurs manches.
     */
    @Test
    public void scoreTest2() {
        // GIVEN
        String[] examples = {
            "1-;--;--;--;--;--;--;--;--;--",
            "1-;1-;1-;1-;1-;1-;1-;1-;1-;1-",
            "1-;2-;3-;4-;5-;6-;7-;8-;9-;1-"
        };
        int[] results = {1, 10, 46};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul du score lorsque des points sont marqués,
     * pendant le second lancer d'une ou plusieurs manches.
     */
    @Test
    public void scoreTest3() {
        // GIVEN
        String[] examples = {
            "12;--;--;--;--;--;--;--;--;--",
            "12;-2;-1;-9;--;--;--;--;--;--",
            "12;--;--;--;--;--;--;--;--;-1-"
        };
        int[] results = {3, 15, 4};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul du score lorsqu'un spare est réalisé
     * lors du second lancer d'une ou plusieurs manches.
     */
    @Test
    public void scoreTest4() {
        // GIVEN
        String[] examples = {
                "1/;--;--;--;--;--;--;--;--;--",
                "1/;1-;--;--;--;--;--;--;--;--"
        };
        int[] results = {10, 12};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul du score lorsqu'un strike est réalisé
     * lors du premier lancer d'une manche ou plusieurs manches.
     */
    @Test
    public void scoreTest5() {
        // GIVEN
        String[] examples = {
            "x;--;--;--;--;--;--;--;--;--",
            "x;1-;--;--;--;--;--;--;--;--",
            "x;12;--;--;--;--;--;--;--;--",
            "45;12;--;--;x;7-;--;x;--;--"
        };
        int[] results = {10, 12, 16, 46};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul du score lorsqu'une série de strike/spares consécutifs
     * est réalisée lors d'une partie.
     */
    @Test
    public void scoreTest6() {
        // GIVEN
        String[] examples = {
            "1/;1/;--;--;--;--;--;--;--;--",
            "-/;x;--;--;--;--;--;--;--;--",
            "-/;2/;x;--;--;x;--;12;--;--"
        };
        int[] results = {21, 30, 55};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul du score lorsqu'un 3e lancer lors de
     * la dernière manche est joué
     */
    @Test
    public void scoreTest7() {
        // GIVEN
        String[] examples = {
                "9/;9/;9/;9/;9/;9/;9/;9/;9/;9/9",
                "9/;9/;9/;9/;9/;9/;9/;9/;9/;xx9",
                "x-;x-;x-;x-;x-;x-;x-;x-;x-;xx1"
        };
        int[] results = {190, 201, 291, 277};

        // WHEN / THEN
        performTest(examples, results);
    }


    /**
     * Test de calcul du score d'une partie parfaite
     */
    @Test
    public void scoreTest8() {
        // GIVEN
        String[] examples = {
                "x;x;x;x;x;x;x;x;x;xxx"
        };
        int[] results = {300};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul d'une série de strike interrompue
     */
    @Test
    public void scoreTest9() {
        // GIVEN
        String[] examples = {
            "x;x;8/;x;x;x;x;x;x;xx9"
        };
        int[] results = {277};

        // WHEN / THEN
        performTest(examples, results);
    }

    /**
     * Test de calcul sur des parties quelconques
     */
    @Test
    public void scoreTest10() {
        // GIVEN
        String[] examples = {
            "11;22;33;44;51;62;71;81;9-;11",
            "11;2/;33;44;51;62;71;81;9-;11",
            "11;2/;33;4/;51;62;71;81;9-;11",
            "11;2/;3/;4/;51;62;71;81;9-;11",
            "11;x;33;44;51;62;71;81;9-;11",
            "11;x;x;44;51;62;71;81;9-;11",
            "x;8/;x;9/;x;7/;x;6/;x;1/x"
        };
        int[] results = {62, 71, 78, 86, 74, 94, 200};

        // WHEN / THEN
        performTest(examples, results);
    }


    // OUTILS
    /**
     * Méthode outil servant de squelette à toutes les méthodes de test.
     *
     * @param examples Les différentes feuilles de scores à évaluer
     * @param results Les résultats à obtenir pour chaque feuille de score
     */
    private void performTest(String[] examples, int[] results) {
        // WHEN
        int[] scores = new int[examples.length];
        for (int i = 0; i < scores.length; ++i) {
            scores[i] = bowlingCalculator.score(examples[i]);
        }

        // THEN
        for (int i = 0; i < scores.length; ++i) {
            assertThat(scores[i]).isEqualTo(results[i]);
        }
    }
}