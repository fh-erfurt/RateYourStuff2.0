package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class GameServiceTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

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
}
