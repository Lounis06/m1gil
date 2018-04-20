package fr.rouen.mastergil;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 * Classe de test de la classe Money.
 */
public class MoneyTest {
    // TESTS CONSTRUCTEURS
    /**
     * Teste le constructeur avec arguments de la classe Money
     */
    @Test
    public void constructeurAvecArguments() {
        // GIVEN/WHEN
        Money money = new Money(6561, Devise.LIVRE);
        // THEN
        assertThat(money.getMontant()).isEqualTo(6561);
        assertThat(money.getDevise()).isEqualTo(Devise.LIVRE);
    }

    /**
     * Teste le constructeur sans arguments de la classe Money
     */
    @Test
    public void constructeurSansArguments() {
        // GIVEN/WHEN
        Money money = new Money();

        // THEN
        assertThat(money.getMontant()).isEqualTo(0);
        assertThat(money.getDevise()).isEqualTo(Devise.PESO);
    }

    /**
     * Teste le constructeur avec arguments de la classe Money,
     * avec l'argument de la devise à null.
     */
    @Test
    public void constructeurAvecDeviseNulle() {
        try {
            // GIVEN/WHEN
            new Money(0, null);
            // THEN
            fail("Une exception aurait du être lancée");
        } catch (Exception e) {}
    }


    // TESTS METHODES
    // ---- isPositif()
    /**
     * Teste si la méthode isPositif() renvoie false,
     * dans le cas où le montant de l'objet testé est négatif.
     */
    @Test
    public void isPositiveFalse() {
        // GIVEN/WHEN
        Money money = new Money(-1, Devise.PESO);

        // THEN
        assertThat(money.isPositif()).isFalse();
    }

    /**
     * Teste si la méthode isPositif() renvoie true, dans le cas où
     * le montant de l'objet testé est positif ou nul.
     */
    @Test
    public void isPositiveTrue() {
        // GIVEN/WHEN
        Money money1 = new Money(0, Devise.PESO); // Valeur nulle
        Money money2 = new Money(42, Devise.PESO); // Valeur positive

        // THEN
        assertThat(money1.isPositif()).isTrue();
        assertThat(money2.isPositif()).isTrue();
    }


    // ---- setMontant()
    /**
     * Teste si la méthode setMontant change le montant de l'objet
     */
    @Test
    public void setMontant() {
        // GIVEN
        Money money = new Money();

        // WHEN
        money.setMontant(42);

        // THEN
        assertThat(money.getMontant()).isEqualTo(42);
    }


    // ---- setDevise()
    /**
     * Teste si la méthode setDevise change la devise de l'objet,
     * si la nouvelle devise correspond à une devise existante.
     */
    @Test
    public void setDeviseNonNull() {
        // GIVEN
        Money money = new Money();

        // WHEN
        money.setDevise(Devise.DINAR);

        // THEN
        assertThat(money.getDevise()).isEqualTo(Devise.DINAR);
    }

    /**
     * Teste si la méthode setDevise lance une exception lorsqu'on lui
     * fournit une nouvelle devise à null.
     */
    @Test
    public void setDeviseNull() {
        try {
            // GIVEN/WHEN
            new Money().setDevise(null);

            // THEN
            fail("Une exception aurait du être lancée");
        } catch (Exception e) {}
    }

    /**
     * Teste la fonction increaseMontant dans le cas où la devise
     * manipulée est celle des YENs.
     */
    @Test
    public void increaseMontantYEN() {
        // GIVEN
        Money money = new Money(42, Devise.YEN);

        // WHEN
        money.increaseMontant(46);

        // THEN
        assertThat(money.getMontant()).isEqualTo(100);
    }

    /**
     * Teste la fonction increaseMontant dans le cas où la devise
     * manipulée est une devise existante autre que YEN.
     */
    @Test
    public void increaseMontantAutre() {
        // GIVEN
        Money money = new Money(42, Devise.DOLLAR);

        // WHEN
        money.increaseMontant(10);

        // THEN
        assertThat(money.getMontant()).isEqualTo(20);
    }

    /**
     * Teste la fonction decreaseMontant dans le cas où la devise
     * manipulée est celle des DINARs.
     */
    @Test
    public void decreaseMontantDINAR() {
        // GIVEN
        Money money = new Money(42, Devise.DINAR);

        // WHEN
        money.decreaseMontant(14);

        // THEN
        assertThat(money.getMontant()).isEqualTo(7);
    }

    /**
     * Teste la fonction decreaseMontant dans le cas où la devise
     * manipulée est une devise existante autre que DINAR.
     */
    @Test
    public void decreaseMontantAutre() {
        // GIVEN
        Money money = new Money(42, Devise.EURO);

        // WHEN
        money.decreaseMontant(14);

        // THEN
        assertThat(money.getMontant()).isEqualTo(27);
    }
}
