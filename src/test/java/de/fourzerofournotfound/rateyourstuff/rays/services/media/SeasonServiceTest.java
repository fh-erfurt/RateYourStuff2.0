package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class SeasonServiceTest {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @BeforeEach
    void beforeEach() {
        episodeRepository.deleteAll();
        seasonRepository.deleteAll();
    }

    @Test
    void shouldDetectDuplicationOfGivenSeason() {
        // Given
        LocalDate releaseDate0 = LocalDate.of(2017, 9, 26);

        Episode givenEpisodeOne = Episode.builder()
                .mediumName("Leuchtfeuer")
                .episodeNumber(1)
                .shortDescription("[...]")
                .releaseDate(releaseDate0)
                .length(51)
                .build();

        Season givenSeason = Season.builder()
                .seasonTitle("Season")
                .seasonNumber(1)
                .build();

        givenSeason.getEpisodes().add(givenEpisodeOne);

        // When
        Season result = seasonRepository.save(givenSeason);

        // Then
        Assertions.assertTrue(seasonService.isValidSeason(givenSeason));

        Assertions.assertTrue(seasonService.isValidSeason(result));
    }
}
