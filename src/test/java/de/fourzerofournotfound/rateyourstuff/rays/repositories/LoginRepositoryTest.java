package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties = "spring.profiles.active = test")

class LoginRepositoryTest {
    @Autowired
    LoginRepository loginRepository;

    @BeforeEach
    public void beforeEach() {loginRepository.deleteAll();}

    @Test
    public void should_add_new_login_profile()
    {
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
    public void should_update_email_address()
    {
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
    public void should_find_login_by_email()
    {
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
    public void should_not_found_login_by_email()
    {
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
    public void should_delete_login_account()
    {
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