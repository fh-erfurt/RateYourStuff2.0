package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
       //repository.deleteAll();
    }

    @Test
    public void should_add_new_user()
    {
        //Given
        User given = User.builder()
                .firstName("Kurt")
                .lastName("Mustermann")
                .secondName("Ryan")
                .userName("Kurt_der_Schnurrt")
                .gender("male")
                .build();

        //When
        User result = repository.save(given);

        //Then
        Assertions.assertThat(result.getUserId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void should_find_user_by_userName()
    {
        //Given
        User given = User.builder()
                .firstName("Heinrich")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Heiner34")
                .gender("male")
                .build();

        //When
        User result = repository.save(given);

        //Then
        Assertions.assertThat(result.getUserName()).isEqualTo(given.getUserName());
    }

    @Test
    public void should_add_login()
    {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        User given = User.builder()
                .firstName("Sigfried")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Siggi1234")
                .gender("male")
                .login(givenLogin)
                .build();

        //When
        User result = repository.save(given);

        //Then
        Assertions.assertThat(result.getUserId()).isEqualTo(given.getLogin().getLoginId());
    }

    @Test
    public void should_update_lastName()
    {
        //Given
        User given = User.builder()
                .firstName("Simone")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Simme1234")
                .gender("female")
                .build();
        User saved = repository.save(given);


        //When
        saved.setLastName("Musterfrau");
        User result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getUserId()).isEqualTo(saved.getUserId());
        Assertions.assertThat(result.getLastName()).isEqualTo("Musterfrau");
    }

    @Test
    public void should_delete_user()
    {
        //Given
        User given = User.builder()
                .firstName("Simon")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Simmo1234")
                .gender("male")
                .build();
        User saved = repository.save(given);

        //When
        repository.delete(saved);
        Optional<User> result = repository.findByUserName("Simmo1234");

        //Then
        Assertions.assertThat(result).isNotPresent();

    }
}