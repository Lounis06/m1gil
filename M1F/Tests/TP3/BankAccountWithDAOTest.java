package fr.rouen.mastergil;

import java.sql.Connection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountWithDAOTest {
    // ATTRIBUTS
    /** Le mock du DAO utilisé par le compte à tester */
    @Mock
    private JdbcDAO mockDao;

    /** Le compte faisant office de sujet de test */
    @InjectMocks
    private BankAccountWithDAO testedAccount;


    // PROCEDURES DE TEST
    /**
     * Teste la méthode d'établissement d'une connexion distante,
     * sur le compte de test.
     *
     * Le test échouera si l'appel de la méthode creerCompte lève
     * une exception.
     */
    @Test
    public void testSetUpConnection() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));

        // WHEN/THEN
        testedAccount.creerCompte();
    }

    /**
     * Teste la méthode creerCompte sur le compte de test.
     */
    @Test
    public void testCreerCompte() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));

        // WHEN
        testedAccount.creerCompte();

        // THEN
        Mockito.verify(mockDao).creerCompte();
    }

    /**
     * Teste la méthode creerCompte avec des arguments spécifiques
     * sur le compte de test.
     */
    @Test
    public void testCreerCompteAvecArguments() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));

        // WHEN
        testedAccount.creerCompte(282, Devise.DOLLAR);

        // THEN
        Mockito.verify(mockDao).creerCompte(282, Devise.DOLLAR);
    }

    /**
     * Teste la méthode consulterSolde du compte de test
     * (appel à getSolde() + vérification de la chaîne renvoyée)
     */
    @Test
    public void testConsulterSolde() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));
        Mockito.when(mockDao.getSolde()).thenReturn(new Money(25, Devise.LIVRE));

        // WHEN
        String txt = testedAccount.consulterSolde();

        // THEN
        Mockito.verify(mockDao).getSolde();
        assertThat(txt).isEqualTo("Votre solde actuel est de 25 LIVRE");
    }

    /**
     * Teste la méthode deposerArgent du compte de test
     */
    @Test
    public void testDeposerArgent() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));
        Mockito.when(mockDao.getSolde()).thenReturn(new Money(25, Devise.EURO));

        // WHEN
        Money m = testedAccount.deposerArgent(25);

        // THEN
        Mockito.verify(mockDao).getSolde();
        Mockito.verify(mockDao).saveMoney(m);
        assertThat(m.getMontant()).isEqualTo(50);
    }


    /**
     * Teste la méthode retirerArgent du compte de test
     */
    @Test
    public void testRetirerArgent() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));
        Mockito.when(mockDao.getSolde()).thenReturn(new Money(25, Devise.EURO));

        // WHEN
        Money m = testedAccount.retirerArgent(25);

        // THEN
        Mockito.verify(mockDao).getSolde();
        Mockito.verify(mockDao).saveMoney(m);
        assertThat(m.getMontant()).isEqualTo(0);
    }

    /**
     * Teste la méthode isSoldePositif du compte de test
     */
    @Test
    public void testIsSoldePositif() throws Exception {
        // GIVEN
        Mockito.when(mockDao.setUpConnection()).thenReturn(Mockito.mock(Connection.class));
        Mockito.when(mockDao.getSolde()).thenReturn(new Money(257, Devise.YEN));

        // WHEN
        Boolean isPositive = testedAccount.isSoldePositif();

        // THEN
        Mockito.verify(mockDao).getSolde();
        assertThat(isPositive).isEqualTo(true);
    }
}
