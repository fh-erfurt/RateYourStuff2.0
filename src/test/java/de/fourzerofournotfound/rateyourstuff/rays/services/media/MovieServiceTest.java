package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MovieRepository;
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

}
