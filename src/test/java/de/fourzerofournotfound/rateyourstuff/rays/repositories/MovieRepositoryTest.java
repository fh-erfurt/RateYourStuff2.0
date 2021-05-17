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
    void saveMovie() {
        //Given
        Movie given = new Movie("Zurück in die Zukunft", "[...]", LocalDate.now(), 90, 6);

        //When
        Movie result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }


    @Test
    void findByMovieTitle () {
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
    void findWithNetwork() {
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

}
