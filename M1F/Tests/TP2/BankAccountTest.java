package fr.rouen.mastergil;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 * Classe de test de la classe BankAccount.
 */
public class BankAccountTest {
    // TESTS METHODES
    /**
     * Teste la méthode creerCompte avec des arguments cohérents et non nuls.
     */
    @Test
    public void creerCompteAvecArguments() {
        // GIVEN
        BankAccount account = new BankAccount();

        // WHEN
        account.creerCompte(6561, Devise.LIVRE);

        // THEN
        assertThat(account.consulterSolde()).isEqualTo("Votre solde actuel est de 6561 LIVRE");
    }

    /**
     * Teste la méthode creerCompte sans arguments.
     */
    @Test
    public void creerCompteSansArguments() {
        // GIVEN
        BankAccount account = new BankAccount();

        // WHEN
        account.creerCompte();

        // THEN
        assertThat(account.consulterSolde()).isEqualTo("Votre solde actuel est de 0 PESO");
    }

    /**
     * Teste si la méthode creerCompte lance une exception
     * avec un montant fixe et une devise non spécifiée.
     */
    @Test
    public void creerCompteAvecDeviseNulle() {
        // GIVEN
        BankAccount account = new BankAccount();

        try {
            // WHEN
            account.creerCompte(396, null);
            // THEN
            fail("Une exception aurait du être lancée");
        } catch (Exception e) {}
    }

    /**
     * Teste si la méthode deposerArgent ajoute correctement le
     * montant donné au montant du compte actuel.
     */
    @Test
    public void deposerArgent() {
        // GIVEN
        BankAccount account = new BankAccount();
        account.creerCompte();

        // WHEN
        account.deposerArgent(1000);

        // THEN
        assertThat(account.consulterSolde()).isEqualTo("Votre solde actuel est de 1000 PESO");
    }

    /**
     * Teste si la méthode deposerArgent ajoute correctement le
     * montant donné au montant du compte actuel.
     */
    @Test
    public void retirerArgent() {
        // GIVEN
        BankAccount account = new BankAccount();
        account.creerCompte(2000, Devise.DINAR);

        // WHEN
        account.retirerArgent(1494);

        // THEN
        assertThat(account.consulterSolde()).isEqualTo("Votre solde actuel est de 506 DINAR");
    }

    /**
     * Teste si la méthode isSoldePositif renvoie false, si le montant
     * d'un compte donné est négatif.
     */
    @Test
    public void isSoldePositifCompteNegatif() {
        // GIVEN
        BankAccount account = new BankAccount();
        account.creerCompte(-2468, Devise.DINAR);

        // WHEN/THEN
        assertThat(account.isSoldePositif()).isFalse();
    }

    /**
     * Teste si la méthode isSoldePositif renvoie true, si le montant
     * d'un compte donné est positif ou nul.
     */
    @Test
    public void isSoldePositifComptePositif() {
        // GIVEN
        BankAccount account1 = new BankAccount();
        account1.creerCompte();
        BankAccount account2 = new BankAccount();
        account2.creerCompte(12345, Devise.DINAR);

        // WHEN/THEN
        assertThat(account1.isSoldePositif()).isTrue();
        assertThat(account2.isSoldePositif()).isTrue();
    }

    /**
     * Teste si la méthode transfererArgent lance une exception,
     * si l'on tente de transférer un montant négatif.
     */
    @Test
    public void transfererArgentMontantNegatif() {
        // GIVEN
        BankAccount src = new BankAccount();
        src.creerCompte();
        BankAccount dest = new BankAccount();
        dest.creerCompte();

        try {
            // WHEN
            src.transfererArgent(dest, -48494);

            // THEN
            fail("Une exception aurait du être lancée");
        } catch (IllegalArgumentException e) {}
    }

    /**
     * Teste si la méthode transfererArgent lance une exception,
     * si l'on tente de transférer vers un compte inexistant.
     */
    @Test
    public void transfererArgentCompteInexistant() {
        // GIVEN
        BankAccount src = new BankAccount();
        src.creerCompte();

        try {
            // WHEN
            src.transfererArgent(null, 48494);

            // THEN
            fail("Une exception aurait du être lancée");
        } catch (IllegalArgumentException e) {}
    }

    /**
     * Teste si la méthode transfererArgent a l'effet escompté :
     * On ajoute un certain montant au compte de destination (passé en argument)
     * qui a été retiré du compte d'origine (l'appelant de la méthode)
     */
    @Test
    public void transfererArgentEffet() {
        // GIVEN
        BankAccount src = new BankAccount();
        src.creerCompte(2524, Devise.DOLLAR);
        BankAccount dest = new BankAccount();
        src.creerCompte();

        // WHEN
        src.transfererArgent(dest, 1000);

        // THEN
        assertThat(src.consulterSolde()).isEqualTo("Votre solde actuel est de 1524 DOLLAR");
        assertThat(dest.consulterSolde()).isEqualTo("Votre solde actuel est de 1000 PESO");
    }
}

