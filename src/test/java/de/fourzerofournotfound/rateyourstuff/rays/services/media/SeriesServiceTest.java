package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeriesDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeriesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class SeriesServiceTest {

    @Autowired
    SeriesRepository seriesRepository;

    @Autowired
    SeriesService seriesService;

    @BeforeEach
    void beforeEach()
    {
        seriesRepository.deleteAll();
    }
    @Test
    public void seriesDtoShouldMatchSeries() {
        //Given
        Series series = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        Series given = seriesRepository.save(series);

        //When
        SeriesDto result = seriesService.convertToDto(given);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getMediumName()).isEqualTo(given.getMediumName());
        Assertions.assertThat(result.getAgeRestriction()).isEqualTo(given.getAgeRestriction());
        Assertions.assertThat(result.getAverageLength()).isEqualTo(given.getAverageLength());
        Assertions.assertThat(result.getIsCompleted()).isEqualTo(given.getIsCompleted());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(given.getShortDescription());
        Assertions.assertThat(result.getReleaseDate()).isEqualTo(given.getReleaseDate().toString());

    }
}
