package de.fourzerofournotfound.rateyourstuff.rays.repositories.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(properties = "spring.profiles.active = test")
class LoginRepositoryTest {
    @Autowired
    LoginRepository loginRepository;

    @BeforeEach
    public void beforeEach() {
        loginRepository.deleteAll();
    }

    @Test
    public void shouldAddNewLoginProfile() {
        //Given
        Login given = Login.builder()
                .email("max.mustermann@rays.de")
                .passwordHash("musterRays123")
                .isEnabled(false)
                .build();

        //When
        Login result = loginRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void shouldUpdateEmailAddress() {
        //Given
        Login given = Login.builder()
                .email("max.mustermann@rays.de")
                .passwordHash("musterRays123")
                .isEnabled(false)
                .build();
        Login saved = loginRepository.save(given);

        //When
        saved.setEmail("maxima.mustermann@rays.de");
        Login result = loginRepository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getEmail()).isEqualTo("maxima.mustermann@rays.de");
    }

    @Test
    public void shouldFindLoginByEmail() {
        //Given
        Login given = Login.builder()
                .email("max.mustermann@rays.de")
                .passwordHash("musterRays123")
                .isEnabled(false)
                .build();

        //When
        Login result = loginRepository.save(given);

        //Then
        Assertions.assertThat(result.getEmail()).isEqualTo(given.getEmail());

    }

    @Test
    public void shouldNotFoundLoginByEmail() {
        //Given
        Login given = Login.builder()
                .email("max.mustermann@rays.de")
                .passwordHash("musterRays123")
                .isEnabled(false)
                .build();
        //When
        Login saved = loginRepository.save(given);
        Optional<Login> result = loginRepository.findLoginByEmailNotIgnoreCase("max.mustermann@rays.de");

        //Then
        assertFalse(result.isEmpty());

    }

    @Test
    public void shouldDeleteLoginAccount() {
        //Given
        Login given = Login.builder()
                .email("max.mustermann@rays.de")
                .passwordHash("musterRays123")
                .isEnabled(false)
                .build();
        Login saved = loginRepository.save(given);

        //When
        loginRepository.delete(saved);
        Optional<Login> result = loginRepository.findByEmail("max.mustermann@rays.de");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }
}