package de.fourzerofournotfound.rateyourstuff.rays.services.media.movies;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.movies.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.movies.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.movies.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @BeforeEach
    void beforeEach() {

        movieRepository.deleteAll();
    }

    @Test
    public void movieDtoShouldMatchMovie() {
        //Given
        Movie movie = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        Movie given = movieRepository.save(movie);

        //When
        MovieDto result = movieService.convertToDto(movie);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getMediumName()).isEqualTo(given.getMediumName());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(given.getShortDescription());
        Assertions.assertThat(result.getReleaseDate()).isEqualTo(given.getReleaseDate().toString());

        Assertions.assertThat(result.getLength()).isEqualTo(given.getLength());
        Assertions.assertThat(result.getAgeRestriction()).isEqualTo(given.getAgeRestriction());
    }

    @Test
    void shouldDetectDuplicationOfGivenMovie() {
        // Given
        LocalDate releaseDate = LocalDate.of(2009, 12, 17);
        Movie testMovie = Movie.builder()
                .mediumName("Avatar - Aufbruch nach Pandora")
                .releaseDate(releaseDate)
                .shortDescription("Avatar – Aufbruch nach Pandora (Originaltitel: Avatar, auch bekannt als James Cameron’s Avatar) ist ein US-amerikanischer Science-Fiction-Film des Regisseurs James Cameron, der weltweit am 17. und 18. Dezember 2009 startete.")
                .ageRestriction(12)
                .length(162)
                .build();

        // When
        Movie result = movieRepository.save(testMovie);

        // Then
        org.junit.jupiter.api.Assertions.assertTrue(movieService.isValidMovie(testMovie));

        org.junit.jupiter.api.Assertions.assertTrue(movieService.isValidMovie(result));
    }


}
