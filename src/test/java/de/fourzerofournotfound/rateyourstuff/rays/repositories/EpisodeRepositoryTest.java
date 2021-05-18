package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
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
        Episode given = new Episode("Fassen Sie sich, Kurtz!", "[...]", LocalDate.now(), 1, 20);

        //When
        Episode result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void should_find_episode_by_name() {
        //Given
        Episode given1 = new Episode("Fasse dich, Kurtz!", "[...]", LocalDate.now(), 23, 20);
        Episode given2 = new Episode("Klink der Casanova", "[...]", LocalDate.now(), 6, 20);

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
        Episode given = new Episode("Fasse dich, Kurtz!", "[...]", LocalDate.now(), 23, 20);
        Episode savedEpisode = repository.save(given);
        String initialDescription = savedEpisode.getMediumName();

        //When
        savedEpisode.setShortDescription("Test");
        Episode result = repository.save(savedEpisode);

        //Then
        Assertions.assertThat(result.getMediumId()).isEqualTo(savedEpisode.getMediumId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo("Test");
    }

    @Test
    public void should_delete_episode() {
        //Given
        Episode given = new Episode("Fasse dich, Kurtz!", "[...]", LocalDate.now(), 23, 20);
        repository.save(given);

        //When
        repository.delete(given);
        Optional<Episode> result = repository.findByMediumName("Fasse dich, Kurtz!");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }
}
