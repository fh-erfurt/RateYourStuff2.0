package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GenreDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Genre;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GenreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active = test")
public class GenreServiceTest {
    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void beforeEach()
    {
        genreRepository.deleteAll();
    }

    @Test
    void genreDtoShouldMatchGenre() {
        //Given
        Genre given = Genre
                .builder()
                .genreName("Horror")
                .build();
        genreRepository.save(given);

        //When
        GenreDto genreDto = genreService.convertToDto(given);

        //Then
        Assertions.assertThat(genreDto.getId()).isNotNull();
        Assertions.assertThat(genreDto.getId()).isEqualTo(given.getId());
        Assertions.assertThat(genreDto.getGenreName()).isEqualTo(given.getGenreName());

    }
}
