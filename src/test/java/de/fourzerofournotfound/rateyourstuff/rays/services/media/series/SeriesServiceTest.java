package de.fourzerofournotfound.rateyourstuff.rays.services.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.series.SeriesDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Series;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series.SeriesRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.series.SeriesService;
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
    void beforeEach() {
        seriesRepository.deleteAll();
    }

    @Test
    public void seriesDtoShouldMatchSeries() {
        //Given
        Series series = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965, 9, 17))
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

    @Test
    void shouldDetectDuplicatesOfGivenSeries() {
        //Given
        LocalDate releaseDate0 = LocalDate.of(2017, 9, 24);

        Series givenSeries = Series.builder()
                .mediumName("Star Trek: Discovery")
                .ageRestriction(0)
                .shortDescription("Star Trek: Discovery ist eine US-amerikanische Science-Fiction-Fernsehserie und die sechste Realfilm-Fernsehserie, die im fiktiven Star-Trek-Universum spielt. In der auch mit DSC abgekürzten Serie geht es um das titelgebende Sternenflottenraumschiff Discovery. ")
                .averageLength(51)
                .releaseDate(releaseDate0)
                .isCompleted(false)
                .build();

        Series givenSeries2 = Series.builder()
                .mediumName("Star Trek: Discovery")
                .ageRestriction(0)
                .shortDescription("StarTrek: Discovery ist eine US-amerikanische Science-Fiction-Fernsehserie und die sechste Realfilm-Fernsehserie, die im fiktiven Star-Trek-Universum spielt. In der auch mit DSC abgekürzten Serie geht es um das titelgebende Sternenflottenraumschiff Discovery. ")
                .averageLength(51)
                .releaseDate(releaseDate0)
                .isCompleted(false)
                .build();

        Series givenSeriesNotToStore = Series.builder()
                .mediumName("How i met your Mother")
                .releaseDate(LocalDate.of(2005, 9, 19))
                .shortDescription("[...]")
                .ageRestriction(0)
                .isCompleted(true)
                .build();

        Episode givenEpisodeOne = Episode.builder()
                .mediumName("Leuchtfeuer")
                .episodeNumber(1)
                .shortDescription("[...]")
                .releaseDate(releaseDate0)
                .length(51)
                .build();

        Season starTrekDiscoverySeasonOne = Season.builder()
                .seasonNumber(1)
                .seasonTitle("Season 1")
                .build();


        givenEpisodeOne.setSeason(starTrekDiscoverySeasonOne);
        starTrekDiscoverySeasonOne.getEpisodes().add(givenEpisodeOne);
        givenSeries.getSeasons().add(starTrekDiscoverySeasonOne);


        // When
        Series result = seriesRepository.save(givenSeries);


        //Then
        org.junit.jupiter.api.Assertions.assertTrue(seriesService.isValidSeries(givenSeries));
        org.junit.jupiter.api.Assertions.assertTrue(seriesService.isValidSeries(result));
        org.junit.jupiter.api.Assertions.assertTrue(seriesService.isValidSeries(givenSeriesNotToStore));
        org.junit.jupiter.api.Assertions.assertFalse(seriesService.isValidSeries(givenSeries2));
    }
}
