package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GamePublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GamePublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active = test")
public class GamePublisherServiceTest {
    @Autowired
    private GamePublisherService gamePublisherService;

    @Autowired
    private GamePublisherRepository gamePublisherRepository;

    @BeforeEach
    void beforeEach() {
        gamePublisherRepository.deleteAll();
    }

    @Test
    void gamePublisherDtoShouldMatchGamePublisher() {
        //Given
        GamePublisher given = GamePublisher
                .builder()
                .gamePublisherTitle("Bandai Namco")
                .build();
        gamePublisherRepository.save(given);

        //When
        GamePublisherDto publisherDto = gamePublisherService.convertToDto(given);

        //Then
        Assertions.assertThat(publisherDto.getId()).isEqualTo(given.getId());
        Assertions.assertThat(publisherDto.getGamePublisherTitle()).isEqualTo(given.getGamePublisherTitle());

    }

    @Test
    void shouldGetPublisherEntityFromDatabaseWithoutCreatingNewPublisher() {
        //Given
        GamePublisher publisher1 = GamePublisher
                .builder()
                .gamePublisherTitle("Nintendo")
                .build();

        GamePublisher publisher2 = GamePublisher
                .builder()
                .gamePublisherTitle("Sony")
                .build();

        gamePublisherRepository.save(publisher1);
        gamePublisherRepository.save(publisher2);
        Long numberOfSavedGamePublishers = gamePublisherRepository.count();

        //When
        String newPublisher = "Nintendo";
        GamePublisher result = gamePublisherService.getPublisher(newPublisher);
        Long updatedNumberOfSavedGamePublishers = gamePublisherRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(publisher1.getId());
        Assertions.assertThat(updatedNumberOfSavedGamePublishers).isEqualTo(numberOfSavedGamePublishers);
    }

    @Test
    void shouldGetPublisherEntityFromDatabaseAndCreateANewPublisher() {
        //Given
        GamePublisher publisher1 = GamePublisher
                .builder()
                .gamePublisherTitle("Nintendo")
                .build();

        GamePublisher publisher2 = GamePublisher
                .builder()
                .gamePublisherTitle("Sony Interactive")
                .build();

        gamePublisherRepository.save(publisher1);
        gamePublisherRepository.save(publisher2);
        long numberOfSavedGamePublishers = gamePublisherRepository.count();

        //When
        String newPublisher = "Bandai Namco";
        GamePublisher result = gamePublisherService.getPublisher(newPublisher);
        long updatedNumberOfSavedGamePublishers = gamePublisherRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isNotEqualTo(publisher1.getId());
        Assertions.assertThat(result.getId()).isNotEqualTo(publisher2.getId());
        Assertions.assertThat(updatedNumberOfSavedGamePublishers).isEqualTo(numberOfSavedGamePublishers + 1);
    }
}
