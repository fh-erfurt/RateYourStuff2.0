package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @AfterEach
    public void afterEach() {
       repository.deleteAll();
    }

    @Test
    void should_save_movie() {
        //Given
        Movie given = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);

        //When
        Movie result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_save_movie_with_network() {
        //Given
        Movie given = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);
        Network network = new Network("Warner Bros");
        given.setNetwork(network);

        //When
        Movie result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getNetwork().getNetworkId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_update_description_of_movie() {
        //Given
        Movie given = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);
        Movie saved = repository.save(given);
        String initialDescription = saved.getShortDescription();

        //When
        String updatedShortDescription = "Great Scott!";
        saved.setShortDescription(updatedShortDescription);
        Movie result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getMediumId()).isEqualTo(saved.getMediumId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedShortDescription);

    }

    @Test
    void should_find_movie_by_title () {
        //Given
        Movie given1 = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);
        Movie given2 = new Movie("Zwiebeljack räumt auf", "[...]", LocalDate.now(), 85, 12);

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Movie> result = repository.findByMediumName("Zwiebeljack räumt auf");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Zwiebeljack räumt auf");
    }

    @Test
    void should_find_movies_with_network() {
        //Given
        Movie given1 = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);
        Network network1  = new Network("CBS");

        given1.setNetwork(network1);

        Movie given2 = new Movie("Batman hält die Welt in Atem", "[...]", LocalDate.now(), 90, 6);
        Network network2  = new Network("Warner Bros");

        given1.setNetwork(network1);
        given2.setNetwork(network2);

        List<Movie> persisted = new ArrayList<>();
        persisted.add(repository.save(given1));
        persisted.add(repository.save(given2));

        //When
        List<Movie> results = repository.findAllByNetwork(network2);

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    void should_remove_movie_from_database() {
        //Given
        Movie given = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);
        Movie saved = repository.save(given);

        //When
        repository.delete(saved);
        Optional<Movie> result = repository.findByMediumName("Zurück in die Zukunft");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}
