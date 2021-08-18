package de.fourzerofournotfound.rateyourstuff.rays;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active = test")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LoginRepository loginRepository;

    private User testUser;


    @BeforeEach
    void beforeEach() {
        createTestUser();
    }

    void createTestUser() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        loginRepository.deleteAll();

        //Given
        Login givenLogin = Login.builder()
                .email("mickey.knop@fh-erfurt.de")
                .passwordHash("1234")
                .isEnabled(false)
                .build();

        Role userRole = Role.builder().roleName("User").build();
        roleRepository.save(userRole);

        testUser = User.builder()
                .firstName("Mickey")
                .lastName("Knop")
                .secondName("noe")
                .gender("m")
                .userName("mikmin")
                .login(givenLogin)
                .role(userRole)
                .build();
        testUser = userRepository.save(testUser);
    }

    @Test
    void userDtoShouldMatchUser() {
        //Given
        User given = testUser;

        //When
        UserDto result = userService.convertToDto(given);

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getFirstName()).isEqualTo(given.getFirstName());
        Assertions.assertThat(result.getLastName()).isEqualTo(given.getLastName());
        Assertions.assertThat(result.getUserName()).isEqualTo(given.getUserName());
        Assertions.assertThat(result.getLoginEmail()).isEqualTo(given.getLogin().getEmail());
        Assertions.assertThat(result.getLoginId()).isEqualTo(given.getLogin().getId());
        Assertions.assertThat(result.getRoleId()).isEqualTo(given.getRole().getId());
        Assertions.assertThat(result.getLoginIsEnabled()).isEqualTo(given.getLogin().getIsEnabled());

    }
}
