package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import com.mysql.cj.log.Log;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginRoleRepositoryTest {

    @Autowired
    LoginRoleRepository loginRoleRepository;

/*
    @BeforeEach
    public void BeforeEach(){loginRoleRepository.deleteAll();}
*/

    @AfterEach
    public void AfterEach(){loginRoleRepository.deleteAll();}

    @Test
    public void should_add_LoginRole()
    {
        //Given
        LoginRole given = LoginRole.builder()
                .build();

        //When
        LoginRole result = loginRoleRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isGreaterThan(0);
    }

    @Test
    public void should_delete_LoginRole()
    {
        //Given
        LoginRole given = LoginRole.builder()
                .build();
        LoginRole saved = loginRoleRepository.save(given);

        //When
        Long savedId = saved.getId();
        loginRoleRepository.delete(saved);
        Optional<LoginRole> result = loginRoleRepository.findById(savedId);

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}