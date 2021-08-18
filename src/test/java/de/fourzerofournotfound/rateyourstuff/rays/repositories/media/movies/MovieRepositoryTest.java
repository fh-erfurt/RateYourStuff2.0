package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.movies.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    void shouldSaveMovie() {
        //Given
        Movie given = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        //When
        Movie result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldSaveMovieWithNetwork() {
        //Given
        Movie given = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();
        Network network = Network.builder()
                .networkTitle("Warner Bros")
                .build();
        given.setNetwork(network);

        //When
        Movie result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getNetwork().getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldUpdateDescriptionOfMovie() {
        //Given
        Movie given = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();
        Movie saved = repository.save(given);

        //When
        String updatedShortDescription = "Great Scott!";
        saved.setShortDescription(updatedShortDescription);
        Movie result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedShortDescription);

    }

    @Test
    void shouldFindMovieByTitle() {
        //Given
        Movie given1 = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        Movie given2 = Movie.builder()
                .mediumName("Zwiebeljack räumt auf")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(85)
                .ageRestriction(12)
                .build();

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Movie> result = repository.findByMediumName("Zwiebeljack räumt auf");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Zwiebeljack räumt auf");
    }

    @Test
    void shouldFindMoviesWithNetwork() {
        //Given
        Movie given1 = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();
        Network network1 = Network.builder()
                .networkTitle("CBS")
                .build();

        given1.setNetwork(network1);

        Movie given2 = Movie.builder()
                .mediumName("Batman hält die Welt in Atem")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        Network network2 = Network.builder()
                .networkTitle("Warner Bros")
                .build();

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
    void shouldRemoveMovieFromDatabase() {
        //Given
        Movie given = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        Movie saved = repository.save(given);

        //When
        repository.delete(saved);
        Optional<Movie> result = repository.findById(saved.getId());

        //Then
        Assertions.assertThat(result).isNotPresent();
    }
}
