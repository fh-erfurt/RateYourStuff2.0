package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @AfterEach
    public void afterEach() {
        //repository.deleteAll();
    }

    @Test
    public void should_have_new_user()
    {
        //Given
        User given = new User("Kurt", "Mustermann", "Ryan", "Kurti789", "male");

        //When
        User result = repository.save(given);

        //Then
        Assertions.assertThat(result.getUserId()).isNotNull().isGreaterThan(0);
    }
}