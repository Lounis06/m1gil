package fr.rouen.mastergil.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by caronfr1 on 06/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    // ATTRIBUTS (Mocks et objet testé)
    /** Le mock de MailService */
    @Mock
    private MailService mockMailService;

    /** Le mock de UserRepository */
    @Mock
    private UserRepository mockUserRepository;

    /** Le mock de PasswordEncoder */
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    /** Le mock de AuthorityRepository */
    @Mock
    private AuthorityRepository mockAuthorityRepository;

    /** Le mock de MyLogger */
    @Mock
    private MyLogger mockLogger;

    /** L'objet UserService utilisé pour les tests */
    @InjectMocks
    private UserService tested;


    // TESTS
    @Test
    public void createUserInformation() throws Exception {
        // GIVEN
        Mockito.when(mockAuthorityRepository.findOne(AuthoritiesConstants.USER)).thenReturn(new Authority());
        Mockito.when(mockPasswordEncoder.encode("azerty")).thenReturn("qwerty");

        // WHEN
        User usr = tested.createUserInformation("caronfr1", "azerty", "Franck", "Caron", "fc@gmail.com", "FR");

        // THEN
        // -- Vérification des appels de méthodes
        Mockito.verify(mockAuthorityRepository).findOne(AuthoritiesConstants.USER);
        Mockito.verify(mockPasswordEncoder).encode("azerty");
        Mockito.verify(mockUserRepository).save(usr);
        Mockito.verify(mockLogger).debug("Created Information for User: {}", usr);

        // -- Vérification des assertions
        assertThat(usr.getAuthorities().size()).isEqualTo(1);
        assertThat(usr.getAuthorities().iterator().next()).isEqualTo(new Authority());

        assertThat(usr.getLogin()).isEqualTo("caronfr1");
        assertThat(usr.getPassword()).isEqualTo("qwerty");
        assertThat(usr.getFirstName()).isEqualTo("Franck");
        assertThat(usr.getLastName()).isEqualTo("Caron");
        assertThat(usr.getEmail()).isEqualTo("fc@gmail.com");
        assertThat(usr.getLangKey()).isEqualTo("FR");
        assertThat(usr.getActivationKey()).isNotEmpty();
        assertThat(usr.isActivated()).isFalse();
    }

    @Test
    public void activateRegistration() throws Exception {
        // GIVEN
        String key = "key123456";
        User usr = new User();
        Mockito.when(mockUserRepository.findOneByActivationKey(Mockito.eq(key))).thenReturn(Optional.of(usr));

        // WHEN
        Optional<User> opt = tested.activateRegistration(key);

        // THEN
        Mockito.verify(mockLogger).debug("Activating user for activation key {}", key);
        Mockito.verify(mockUserRepository).findOneByActivationKey(key);
        Mockito.verify(mockUserRepository).save(usr);
        Mockito.verify(mockLogger).debug("Activated user: {}", usr);

        assertThat(opt.isPresent()).isTrue();
        assertThat(opt.get().isActivated()).isTrue();
        assertThat(opt.get().getActivationKey()).isNull();
    }

    @Test
    public void completePasswordReset() throws Exception {
        // GIVEN
        String key = "key123456", key2 = "bjbkkb";
        String newPassword = "blablabla";
        String newPasswordEncoded = "alualualu";

        // -- Cas n°1 : Prédicat vérifié
        User usr = new User();
        usr.setResetDate(ZonedDateTime.now());
        Mockito.when(mockUserRepository.findOneByResetKey(Mockito.eq(key))).thenReturn(Optional.of(usr));
        Mockito.when(mockPasswordEncoder.encode(Mockito.eq(newPassword))).thenReturn(newPasswordEncoded);

        // -- Cas n°2 : Prédicat non vérifié
        User usr2 = new User();
        usr2.setResetDate(ZonedDateTime.now().minusHours(48));
        Mockito.when(mockUserRepository.findOneByResetKey(Mockito.eq(key2))).thenReturn(Optional.of(usr2));

        // WHEN
        Optional<User> opt = tested.completePasswordReset(newPassword, key);
        Optional<User> opt2 = tested.completePasswordReset(newPassword, key2);

        // THEN
        // -- Cas n°1 :
        Mockito.verify(mockLogger).debug("Reset user password for reset key {}", key);
        Mockito.verify(mockUserRepository).findOneByResetKey(key);
        Mockito.verify(mockUserRepository).save(usr);
        Mockito.verify(mockPasswordEncoder).encode(newPassword);

        assertThat(opt.isPresent()).isTrue();
        assertThat(opt.get().getPassword()).isEqualTo(newPasswordEncoded);
        assertThat(opt.get().getResetDate()).isNull();
        assertThat(opt.get().getResetKey()).isNull();

        // -- Cas n°2 :
        Mockito.verify(mockLogger).debug("Reset user password for reset key {}", key2);
        Mockito.verify(mockUserRepository).findOneByResetKey(key2);

        assertThat(opt2.isPresent()).isFalse();
    }

    @Test
    public void requestPasswordReset() throws Exception {
        // GIVEN
        String mail = "xyz@gmail.com", mail2 = "abc@gmail.com";
        User usr = new User(), usr2 = new User();
        usr.setActivated(true);

        Mockito.when(mockUserRepository.findOneByEmail(Mockito.eq(mail))).thenReturn(Optional.of(usr));
        Mockito.when(mockUserRepository.findOneByEmail(Mockito.eq(mail2))).thenReturn(Optional.of(usr2));

        // WHEN
        Optional<User> opt = tested.requestPasswordReset(mail);
        Optional<User> opt2 = tested.requestPasswordReset(mail2);

        // THEN
        Mockito.verify(mockUserRepository).findOneByEmail(mail);
        Mockito.verify(mockUserRepository).save(usr);
        Mockito.verify(mockUserRepository).findOneByEmail(mail2);

        assertThat(opt2.isPresent()).isFalse();
        assertThat(opt.isPresent()).isTrue();
        assertThat(opt.get().getResetDate()).isExactlyInstanceOf(ZonedDateTime.class);
        assertThat(opt.get().getResetKey()).isNotEmpty();
    }

    @Test
    public void checkUserStatus() throws Exception {
        // GIVEN
        Mockito.when(mockUserRepository.findAll()).thenReturn(new ArrayList<User>());

        // WHEN
        tested.checkUserStatus();

        // THEN
        Mockito.verify(mockUserRepository).findAll();
    }
}