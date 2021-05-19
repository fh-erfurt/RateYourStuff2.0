package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties = "spring.profiles.active = test")

class LoginRepositoryTest {
    @Autowired
    LoginRepository loginRepository;

    @BeforeEach
    public void beforeEach() {loginRepository.deleteAll();};

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
        Assertions.assertThat(result.getLoginId()).isNotNull().isGreaterThan(0);
    }
}