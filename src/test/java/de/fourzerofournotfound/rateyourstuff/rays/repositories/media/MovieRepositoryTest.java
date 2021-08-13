package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.models.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void should_save_movie_with_network() {
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
    void should_update_description_of_movie() {
        //Given
        Movie given = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();
        Movie saved = repository.save(given);
        String initialDescription = saved.getShortDescription();

        //When
        String updatedShortDescription = "Great Scott!";
        saved.setShortDescription(updatedShortDescription);
        Movie result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedShortDescription);

    }

    @Test
    void should_find_movie_by_title () {
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
    void should_find_movies_with_network() {
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
    void should_remove_movie_from_database() {
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

    @Test
    public void givenPartialMediumName_WhenFindByMediumNameContaining_ThenMoviesShouldReturn() {
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

        List<Movie> results = repository.findByMediumNameContaining("Zukunft");
        assertEquals(1, results.size());

        results = repository.findByMediumNameLike("%Zukunft%");
        assertEquals(1, results.size());

        results = repository.findByMediumNameIsContaining("Z");
        assertEquals(2, results.size());

        results = repository.findByMediumNameContains("Z");
        assertEquals(2, results.size());
    }

    @Test
    public void givenPartialTitle_WhenFindByMediumNameContainingIgnoreCase_ThenMoviesShouldReturn() {
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

        List<Movie> results = repository.findByMediumNameContainingIgnoreCase("zUKuNfT");
        assertEquals(1, results.size());
    }

    @Test
    public void givenPartialTitle_WhenSearchByMediumNameLike_ThenMoviesShouldReturn() {
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

        List<Movie> results = repository.searchByMediumNameLike("Zuk");
        assertEquals(1, results.size());
    }

    @Test
    public void givenPartialTitle_WhenFindByMediumNameLikeIgnoreCase_ThenMoviesShouldReturn() {
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

        String givenInput = "zurück in räumt zukU";


        ArrayList<String> separatedInput = new ArrayList<String>();
        Collections.addAll(separatedInput,givenInput.split(" "));
        System.out.println(separatedInput.size());

        int minLengthForValidWord = 4;
        Iterator<String> iterator = separatedInput.iterator();
        while (iterator.hasNext()) {
            String currentString = iterator.next();
            if(currentString.length() < minLengthForValidWord)
            {
                iterator.remove();
            }
        }


        ArrayList<String> alteredInputList = new ArrayList<String>();

        ArrayList<Movie> allMoviesThatFit = new ArrayList<Movie>();
        repository.save(given1);
        repository.save(given2);

        //List<Movie> results;

        for(String s: separatedInput)
        {
            System.out.println("test: " + s);
            String forLikeliness = "%";
            String alteredInput = forLikeliness+s+forLikeliness;
            System.out.println("test: " + alteredInput);
            alteredInputList.add(alteredInput);
        }


        HashSet allMovies = new HashSet();
        for(String a: alteredInputList)
        {
            List<Movie> results = repository.findByMediumNameLikeIgnoreCase(a);

            for(Movie match: results)
            {
                allMovies.add(match.getId());
            }
        }
        assertEquals(2, allMovies.size());
    }
}
