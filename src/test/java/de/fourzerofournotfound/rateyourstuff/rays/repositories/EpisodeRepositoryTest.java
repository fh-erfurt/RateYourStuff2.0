package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active=test")
public class EpisodeRepositoryTest {
    @Autowired
    EpisodeRepository repository;

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    public void saveEpisode() {
        //Given
        Episode given = new Episode("Fassen Sie sich, Kurtz!", "[...]", LocalDate.now(), 1, 6);

        //When
        Episode result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }
}
