package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series.SeasonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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
    void shouldSaveSeason() {
        //Given
        Season given = Season.builder().seasonNumber(1).seasonTitle("").build();

        //When
        Season result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void shouldSaveSeasonAndEpisodes() {
        //Given
        Season given = Season.builder().seasonNumber(1).seasonTitle("").build();
        Episode episode1 = Episode.builder()
                .mediumName("Kuckuck im Nest")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965, 9, 17))
                .episodeNumber(1)
                .length(20)
                .build();

        Episode episode2 = Episode.builder()
                .mediumName("Operation Tiger")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965, 9, 24))
                .episodeNumber(2)
                .length(20)
                .build();

        //When
        episode1.setSeason(given);
        episode2.setSeason(given);
        given.getEpisodes().add(episode1);
        given.getEpisodes().add(episode2);

        Season saved = repository.save(given);


        //Then
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getEpisodes()).allMatch(Objects::nonNull);
        Assertions.assertThat((long) saved.getEpisodes().size()).isEqualTo(given.getEpisodes().size());
    }

    @Test
    public void shouldUpdateSeason() {
        //Given
        Season given = Season.builder().seasonNumber(1).seasonTitle("").build();
        Season saved = repository.save(given);

        //When
        saved.setSeasonTitle("The Beginning");
        Season result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getSeasonTitle()).isEqualTo("The Beginning");
    }

    @Test
    public void shouldDeleteSeason() {
        //Given
        Season given = Season.builder().seasonNumber(1).seasonTitle("").build();
        Season saved = repository.save(given);

        //When
        repository.delete(given);

        Optional<Season> result = repository.findById(saved.getId());

        //Then
        Assertions.assertThat(result).isNotPresent();

    }
}
