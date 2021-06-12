package de.fourzerofournotfound.rateyourstuff.rays.repositories;


import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class GameRepositoryTest
{
    @Autowired
    GameRepository gameRepository;

    @AfterEach
    public void afterEach (){gameRepository.deleteAll();}

    @Test
    void should_save_game(){
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8,  1, 4, 12);

        //When
        Game result = gameRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_save_game_with_gamePublisher(){
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8,  1, 4, 12);
        GamePublisher gamePublisher = new GamePublisher("Electronic Arts");
        given.setGamePublisher(gamePublisher);

        //When
        Game result = gameRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getGamePublisher().getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_update_game_shortDescription(){
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8,  1, 4, 12);
        Game saved = gameRepository.save(given);
        String initialDescription = saved.getShortDescription();

        //When
        String updatedSortDescription = "It really sucks that I paid 60€ for this piece of crap";
        saved.setShortDescription(updatedSortDescription);
        Game result = gameRepository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedSortDescription);
    }

    @Test
    void should_delete_game_from_database() {
        //Given
        Game given = new Game("Anthem", "Worst Game ever", LocalDate.now(), (float) 0.8,  1, 4, 12);
        Game saved = gameRepository.save(given);

        //When
        gameRepository.delete(saved);
        Optional<Game> result = gameRepository.findByMediumName("Anthem");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

    @Test
    void should_find_Game_by_title(){
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
    void should_find_games_with_platform() {
        //Given
        Game given1 = new Game("Anthem", "Worst Game ever", LocalDate.now(), 0.8f,  1, 4, 12);
        Platform platform1  = new Platform("PC");

        given1.setPlatform(platform1);

        Game given2 = new Game("Landwirtschaftssimulator", "Best Farmingsimulator", LocalDate.now(), 170.7f,  1, 1, 0);
        Platform platform2  = new Platform("PC");

        given1.setPlatform(platform1);
        given2.setPlatform(platform2);

        List<Game> persisted = new ArrayList<>();
        persisted.add(gameRepository.save(given1));
        persisted.add(gameRepository.save(given2));

        //When
        List<Game> results = gameRepository.findAllByPlatform(platform2);

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

}
