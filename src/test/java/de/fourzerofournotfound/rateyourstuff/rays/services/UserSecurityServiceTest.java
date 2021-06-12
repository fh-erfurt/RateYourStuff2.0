package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active = test")
class UserSecurityServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginRepository loginRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
        loginRepository.deleteAll();
    }

    @Test
    public void shouldEncryptPassword()
    {
        //Given
        UserSecurityService service = new UserSecurityService();

        Login givenLogin = Login.builder()
                .email("mickey.knop@fh-erfurt.de")
                .passwordHash("1234")
                .isEnabled(false)
                .build();

        User givenUser = User.builder()
                .firstName("Mickey")
                .lastName("Knop")
                .secondName("noe")
                .gender("m")
                .userName("mikmin")
                .login(givenLogin)
                .build();



        //When
        String unhashedPassword = givenLogin.getPasswordHash();
        service.hashPasswordOfSignUp(givenUser);
        User result = userRepository.save(givenUser);


        //Then
        assertNotEquals(unhashedPassword, result.getLogin().getPasswordHash());

    }
}