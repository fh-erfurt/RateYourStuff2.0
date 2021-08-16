package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GamePublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.PlatformRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest(properties = "spring.profiles.active = test")
public class GameServiceTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @BeforeEach
    void beforeEach() {
        gameRepository.deleteAll();
    }

    @Test
    void gameDtoShouldMatchGame() {
        //Given
        Game game = Game.builder()
                        .mediumName("Anthem")
                        .shortDescription("Worst Game ever")
                        .releaseDate(LocalDate.now())
                        .averagePlaytime(0.8f)
                        .minNumberOfGamers(1)
                        .maxNumberOfGamers(4)
                        .ageRestriction(12)
                        .build();
        Game given = gameRepository.save(game);
        //When
        GameDto result = gameService.convertToDto(given);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getMediumName()).isEqualTo(given.getMediumName());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(given.getShortDescription());
        Assertions.assertThat(result.getReleaseDate()).isEqualTo(given.getReleaseDate().toString());

        Assertions.assertThat(result.getAveragePlaytime()).isEqualTo(given.getAveragePlaytime());
        Assertions.assertThat(result.getMinNumberOfGamers()).isEqualTo(given.getMinNumberOfGamers());
        Assertions.assertThat(result.getMaxNumberOfGamers()).isEqualTo(given.getMaxNumberOfGamers());
        Assertions.assertThat(result.getAgeRestriction()).isEqualTo(given.getAgeRestriction());

    }

    @Test
    void shouldDetectDuplicatesOfGames()
    {
        // Given
        LocalDate releaseDate = LocalDate.of(2001, 11, 15);
        Game testGame = Game.builder()
                .mediumName("Halo - Combat Evolved")
                .releaseDate(releaseDate)
                .shortDescription("Halo: Combat Evolved, also known as Halo: CE, is a first-person shooter game developed by Bungie and published by Microsoft Game Studios.")
                .ageRestriction(16)
                .maxNumberOfGamers(16)
                .minNumberOfGamers(1)
                .build();

        // When
        Game result = gameRepository.save(testGame);

        // Then
        org.junit.jupiter.api.Assertions.assertTrue(gameService.isValidGame(testGame));

        org.junit.jupiter.api.Assertions.assertTrue(gameService.isValidGame(result));
    }




}
