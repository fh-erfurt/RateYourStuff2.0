package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
class LoginRoleRepositoryTest {

    @Autowired
    LoginRoleRepository loginRoleRepository;


    @BeforeEach
    public void BeforeEach(){loginRoleRepository.deleteAll();}


    @Test
    public void should_add_LoginRole()
    {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("Admin")
                .build();

        LoginRole givenLoginRole = LoginRole.builder()
                .login(givenLogin)
                .role(givenRole)
                .build();

        //When
        LoginRole result = loginRoleRepository.save(givenLoginRole);

        //Then
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isGreaterThan(0);
    }

    @Test
    public void should_delete_LoginRole()
    {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("Admin")
                .build();

        LoginRole givenLoginRole = LoginRole.builder()
                .login(givenLogin)
                .role(givenRole)
                .build();
        LoginRole saved = loginRoleRepository.save(givenLoginRole);

        //When
        Long savedId = givenLoginRole.getId();
        loginRoleRepository.delete(saved);
        Optional<LoginRole> result = loginRoleRepository.findById(savedId);

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}