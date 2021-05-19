package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import com.mysql.cj.log.Log;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginRoleRepositoryTest {

    @Autowired
    LoginRoleRepository loginRoleRepository;

    @AfterEach
    public void AfterEach(){loginRoleRepository.deleteAll();};

    @Test
    public void should_add_LoginRole()
    {
        //Given
        LoginRole given = LoginRole.builder()
                .build();

        //When
        LoginRole result = loginRoleRepository.save(given);

        //Then
        Assertions.assertThat(result.getLoginRoleId()).isNotNull();
        Assertions.assertThat(result.getLoginRoleId()).isGreaterThan(0);
    }

    @Test
    public void should_delete_LoginRole()
    {
        //Given
        LoginRole given = LoginRole.builder()
                .build();
        LoginRole saved = loginRoleRepository.save(given);

        //When
    }

}