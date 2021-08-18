package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeriesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class EpisodeServiceTest {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @BeforeEach
    void beforeEach() {

        episodeRepository.deleteAll();
        seasonRepository.deleteAll();
        seriesRepository.deleteAll();
    }

    @Test
    void episodeDtoShouldMatchEpisode() {
        //Given
        Episode episode = Episode.builder()
                .mediumName("Fasse dich, Kurtz!")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965, 9, 17))
                .episodeNumber(23)
                .length(20)
                .build();
        Episode given = episodeRepository.save(episode);

        //When
        EpisodeDto result = episodeService.convertToDto(given);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getMediumName()).isEqualTo(given.getMediumName());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(given.getShortDescription());
        Assertions.assertThat(result.getReleaseDate()).isEqualTo(given.getReleaseDate().toString());

        Assertions.assertThat(result.getEpisodeNumber()).isEqualTo(given.getEpisodeNumber());
        Assertions.assertThat(result.getLength()).isEqualTo(given.getLength());
    }

    @Test
    void shouldDetectDuplicatesOfGivenEpisode() {
        //Given
        LocalDate releaseDate = LocalDate.of(2017, 9, 26);

        Series givenSeries = Series.builder()
                .mediumName("Star Trek: Discovery")
                .ageRestriction(0)
                .shortDescription("Star Trek: Discovery ist eine US-amerikanische Science-Fiction-Fernsehserie und die sechste Realfilm-Fernsehserie, die im fiktiven Star-Trek-Universum spielt. In der auch mit DSC abgekürzten Serie geht es um das titelgebende Sternenflottenraumschiff Discovery. ")
                .averageLength(51)
                .releaseDate(releaseDate)
                .isCompleted(false)
                .build();

        Episode givenEpisode = Episode.builder()
                .mediumName("Leuchtfeuer")
                .episodeNumber(1)
                .shortDescription("[...]")
                .releaseDate(releaseDate)
                .length(51)
                .build();

        Season givenSeason = Season.builder()
                .seasonTitle("Season")
                .seasonNumber(1)
                .build();

        givenEpisode.setSeason(givenSeason);
        givenSeason.getEpisodes().add(givenEpisode);
        givenSeries.getSeasons().add(givenSeason);


        //When
        seriesRepository.save(givenSeries);

        //Then
        org.junit.jupiter.api.Assertions.assertTrue(episodeService.isValidEpisode(givenEpisode));
    }
}
