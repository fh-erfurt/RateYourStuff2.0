package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class GamePublisherRepositoryTest {
    @Autowired
    private GamePublisherRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    void should_save_gamePublisher() {
        //Given

        GamePublisher given = new GamePublisher("Nintendo");

        //When
        GamePublisher result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_find_gamePublisher_by_title () {
        //Given
        GamePublisher given1 = new GamePublisher("Nintendo");
        GamePublisher given2 = new GamePublisher("Electronic Arts");

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<GamePublisher> result = repository.findByGamePublisherTitle("Nintendo");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getGamePublisherTitle()).isEqualTo("Nintendo");
    }

    @Test
    void should_find_all_gamePublishers() {
        //Given
        GamePublisher given1 = new GamePublisher("Nintendo");
        GamePublisher given2 = new GamePublisher("Electronic Arts");

        List<GamePublisher> persisted = new ArrayList<>();
        persisted.add(repository.save(given1));
        persisted.add(repository.save(given2));

        //When
        List<GamePublisher> results = repository.findAll();

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    public void should_update_gamePublisher() {
        //Given
        GamePublisher given = new GamePublisher("Nintendo");
        GamePublisher saved = repository.save(given);

        //When
        saved.setGamePublisherTitle("Electronic Arts");
        GamePublisher result = repository.save(saved);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getGamePublisherTitle()).isEqualTo("Electronic Arts");
    }

    @Test
    public void should_delete_gamePublisher () {
        //Given
        GamePublisher given = new GamePublisher("Nintendo");
        GamePublisher saved = repository.save(given);

        //When
        repository.delete(given);
        Optional<GamePublisher> result = repository.findById(saved.getId());

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}
