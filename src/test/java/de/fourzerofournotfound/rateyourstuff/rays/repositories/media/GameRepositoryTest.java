package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;


import de.fourzerofournotfound.rateyourstuff.rays.models.media.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest(properties = "spring.profiles.active=test")
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlatformRepository platformRepository;

    @AfterEach
    public void afterEach() {
        gameRepository.deleteAll();
        platformRepository.deleteAll();
    }

    @Test
    void shouldSaveGame() {
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8, 1, 4, 12);

        //When
        Game result = gameRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldSaveGameWithGamePublisher() {
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8, 1, 4, 12);
        GamePublisher gamePublisher = new GamePublisher("Electronic Arts");
        given.setGamePublisher(gamePublisher);

        //When
        Game result = gameRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getGamePublisher().getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldUpdateGameShortDescription() {
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8, 1, 4, 12);
        Game saved = gameRepository.save(given);

        //When
        String updatedSortDescription = "It really sucks that I paid 60€ for this piece of crap";
        saved.setShortDescription(updatedSortDescription);
        Game result = gameRepository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedSortDescription);
    }

    @Test
    void shouldDeleteGameFromDatabase() {
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8, 1, 4, 12);
        Game saved = gameRepository.save(given);

        //When
        gameRepository.delete(saved);
        Optional<Game> result = gameRepository.findByMediumName("Anthem");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

    @Test
    void shouldFindGameByTitle() {
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8, 1, 4, 12);
        gameRepository.save(given);

        //When
        Optional<Game> result = gameRepository.findByMediumName("Anthem");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Anthem");
    }

    @Test
    void shouldFindGameWithPublisher() {
        //Given
        Game given1 = new Game("Anthem", "Worst Game ever", LocalDate.now(), 0.8f, 1, 4, 12);
        GamePublisher publisher1 = new GamePublisher("Electronic Arts");
        given1.setGamePublisher(publisher1);

        Game given2 = new Game("Pokemon Snap", "Boring", LocalDate.now(), 0.8f, 1, 1, 0);
        GamePublisher publisher2 = new GamePublisher("Nintendo");
        given1.setGamePublisher(publisher2);

        List<Game> persisted = new ArrayList<>();
        persisted.add(gameRepository.save(given1));
        persisted.add(gameRepository.save(given2));

        //When
        List<Game> results = gameRepository.findAllByGamePublisher(publisher2);

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    void shouldFindGamesWithPlatform() {
        //Given
        Game given1 = new Game("Anthem", "Worst Game ever", LocalDate.now(), 0.8f, 1, 4, 12);

        Game given2 = new Game("Pokemon Snap", "Boring", LocalDate.now(), 0.8f, 1, 1, 12);

        HashSet<Platform> platformSet1 = new HashSet<>();
        HashSet<Platform> platformSet2 = new HashSet<>();

        Platform platform1 = Platform.builder()
                .platformTitle("PC")
                .build();
        Platform platform2 = Platform.builder()
                .platformTitle("Nintendo Switch")
                .build();
        Platform platform3 = Platform.builder()
                .platformTitle("PS5")
                .build();

        platformRepository.save(platform1);
        platformRepository.save(platform2);
        platformRepository.save(platform3);

        platformSet1.add(platform1);
        platformSet1.add(platform2);

        platformSet2.add(platform2);
        platformSet2.add(platform3);

        given1.setPlatforms(platformSet1);
        given2.setPlatforms(platformSet2);

        List<Game> persisted = new ArrayList<>();
        persisted.add(gameRepository.save(given1));
        persisted.add(gameRepository.save(given2));

        //When
        List<Game> results = gameRepository.findAllByPlatforms(platform2);

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }
}
