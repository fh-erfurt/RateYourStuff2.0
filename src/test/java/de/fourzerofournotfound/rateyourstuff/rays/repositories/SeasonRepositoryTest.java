package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
public class SeasonRepositoryTest {
    @Autowired
    SeasonRepository repository;

    @AfterEach
    public void afterEach() {
        //repository.deleteAll();
    }

    @Test
    void saveSeason() {
        //Given
        Season given = new Season(1, "");

        //When
        Season result = repository.save(given);

        //Then
        Assertions.assertThat(result.getSeasonId()).isNotNull().isGreaterThan(0);
    }
}
