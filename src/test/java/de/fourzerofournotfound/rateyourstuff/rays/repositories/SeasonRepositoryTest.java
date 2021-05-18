package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Objects;

@SpringBootTest(properties = "spring.profiles.active=test")
public class SeasonRepositoryTest {
    @Autowired
    SeasonRepository repository;

    @Autowired
    EpisodeRepository episodeRepository;

    @AfterEach
    public void afterEach() {
        episodeRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    void should_save_season() {
        //Given
        Season given = new Season(1, "");

        //When
        Season result = repository.save(given);

        //Then
        Assertions.assertThat(result.getSeasonId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void should_save_season_and_episodes() {
        //Given
        Season given = new Season(1, "");
        Episode episode1 = new Episode("Kuckuck im Nest", "[...]", LocalDate.of(1965,9,17),1,20);
        Episode episode2 = new Episode("Operation Tiger", "[...]", LocalDate.of(1965,9,24),2,20);

        //When
        episode1.setSeason(given);
        episode2.setSeason(given);
        given.getEpisodes().add(episode1);
        given.getEpisodes().add(episode2);

        Season saved = repository.save(given);


        //Then
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getEpisodes()).allMatch(Objects::nonNull);
        Assertions.assertThat(saved.getEpisodes().stream().count()).isEqualTo(given.getEpisodes().stream().count());
    }
}
