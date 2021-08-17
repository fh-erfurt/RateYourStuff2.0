package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class EpisodeRepositoryTest {
    @Autowired
    EpisodeRepository repository;

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    public void should_save_episode() {
        //Given
        Episode given = Episode.builder()
                .mediumName("Fasse dich, Kurtz!")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .episodeNumber(23)
                .length(20)
                .build();

        //When
        Episode result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void should_find_episode_by_name() {
        //Given
        Episode given1 = Episode.builder()
                .mediumName("Fasse dich, Kurtz!")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .episodeNumber(23)
                .length(20)
                .build();

        Episode given2 = Episode.builder()
                .mediumName("Operation Tiger")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,24))
                .episodeNumber(2)
                .length(20)
                .build();

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Episode> result = repository.findByMediumName("Fasse dich, Kurtz!");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Fasse dich, Kurtz!");
    }

    @Test
    public void should_update_episode() {
        //Given
        Episode given = Episode.builder()
                .mediumName("Fasse dich, Kurtz!")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .episodeNumber(23)
                .length(20)
                .build();

        Episode savedEpisode = repository.save(given);

        //When
        savedEpisode.setShortDescription("Test");
        Episode result = repository.save(savedEpisode);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(savedEpisode.getId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo("Test");
    }

    @Test
    public void should_delete_episode() {
        //Given
        Episode given = Episode.builder()
                .mediumName("Fasse dich, Kurtz!")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .episodeNumber(23)
                .length(20)
                .build();

        Episode saved = repository.save(given);

        //When
        repository.delete(given);
        Optional<Episode> result = repository.findById(saved.getId());

        //Then
        Assertions.assertThat(result).isNotPresent();
    }
}
