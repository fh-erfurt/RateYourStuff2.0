package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
class MediaServiceTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaService mediaService;

    @BeforeEach
    void beforeEach() {
        movieRepository.deleteAll();
    }

    @Test
    void mediumDtoShouldMatchMedium() {
        //Given
        Movie given = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        movieRepository.save(given);

        //When
        MediumDto result = mediaService.convertToDto(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getMediumName()).isEqualTo(given.getMediumName());
        Assertions.assertThat(result.getNumberOfCollections()).isZero();
        Assertions.assertThat(result.getNumberOfComments()).isZero();
        Assertions.assertThat(result.getAverageRating()).isZero();
    }


    @Test
    public void givenPartialTitle_WhenFindByMediumNameLikeIgnoreCase_ThenMoviesShouldReturn() {

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

        String givenInput = "zurück in räumt zukU";


        //When
        mediaRepository.save(given1);
        mediaRepository.save(given2);


        //Then
        Assertions.assertThat(mediaService.getSearchResult(givenInput).size()).isEqualTo(2);
    }
}