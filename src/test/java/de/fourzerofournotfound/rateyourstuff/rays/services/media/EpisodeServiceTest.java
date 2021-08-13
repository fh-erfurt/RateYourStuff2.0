package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.EpisodeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class EpisodeServiceTest {

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    EpisodeService episodeService;

    @BeforeEach
    void beforeEach()
    {
        episodeRepository.deleteAll();
    }

    @Test
    void episodeDtoShouldMatchEpisode() {
        //Given
        Episode episode = Episode.builder()
                .mediumName("Fasse dich, Kurtz!")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
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
}
