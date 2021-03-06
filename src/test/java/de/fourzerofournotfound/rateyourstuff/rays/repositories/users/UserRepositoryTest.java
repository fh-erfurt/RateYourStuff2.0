package de.fourzerofournotfound.rateyourstuff.rays.repositories.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
        roleRepository.deleteAll();
        loginRepository.deleteAll();
    }

    @Test
    public void shouldAddNewUser() {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("User")
                .build();

        roleRepository.save(givenRole);

        User given = User.builder()
                .firstName("Kurt")
                .lastName("Mustermann")
                .secondName("Ryan")
                .userName("Kurt_der_Schnurrt")
                .gender("male")
                .login(givenLogin)
                .role(givenRole)
                .build();

        //When
        User result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void shouldFindUserByUserName() {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("User")
                .build();

        roleRepository.save(givenRole);

        User given = User.builder()
                .firstName("Heinrich")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Heiner34")
                .gender("male")
                .login(givenLogin)
                .role(givenRole)
                .build();

        //When
        User result = repository.save(given);

        //Then
        Assertions.assertThat(result.getUserName()).isEqualTo(given.getUserName());
    }

    @Test
    public void shouldAddLogin() {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("User")
                .build();

        roleRepository.save(givenRole);

        User given = User.builder()
                .firstName("Sigfried")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Siggi1234")
                .gender("male")
                .login(givenLogin)
                .role(givenRole)
                .build();

        //When
        User result = repository.save(given);

        //Then
        System.out.println(result.getRole().getId());
        Assertions.assertThat(result.getLogin().getId()).isNotNull();
    }

    @Test
    public void shouldUpdateLastName() {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("User")
                .build();

        roleRepository.save(givenRole);

        User given = User.builder()
                .firstName("Simone")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Simme1234")
                .gender("female")
                .login(givenLogin)
                .role(givenRole)
                .build();
        User saved = repository.save(given);


        //When
        saved.setLastName("Musterfrau");
        User result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getLastName()).isEqualTo("Musterfrau");
    }

    @Test
    public void shouldDeleteUser() {
        //Given
        Login givenLogin = Login.builder()
                .email("siggi@rays.de")
                .passwordHash("1234Hallo4567")
                .isEnabled(true)
                .build();

        Role givenRole = Role.builder()
                .roleName("User")
                .build();

        roleRepository.save(givenRole);

        User given = User.builder()
                .firstName("Simon")
                .secondName("Muster")
                .lastName("Mustermann")
                .userName("Simmo1234")
                .gender("male")
                .login(givenLogin)
                .role(givenRole)
                .build();
        User saved = repository.save(given);

        //When
        repository.delete(saved);
        Optional<User> result = repository.findByUserName("Simmo1234");

        //Then
        Assertions.assertThat(result).isNotPresent();

    }
}