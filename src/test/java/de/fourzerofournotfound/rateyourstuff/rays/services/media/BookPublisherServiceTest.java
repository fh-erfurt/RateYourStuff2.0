package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookPublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookPublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active = test")
public class BookPublisherServiceTest {
    @Autowired
    private BookPublisherService bookPublisherService;

    @Autowired
    private BookPublisherRepository bookPublisherRepository;

    @BeforeEach
    void beforeEach() {
        bookPublisherRepository.deleteAll();
    }

    @Test
    void bookPublisherDtoShouldMatchBookPublisher() {
        //Given
        BookPublisher given = BookPublisher
                .builder()
                .bookPublisherTitle("Hanser")
                .build();
        bookPublisherRepository.save(given);

        //When
        BookPublisherDto publisherDto = bookPublisherService.convertToDto(given);

        //Then
        Assertions.assertThat(publisherDto.getId()).isNotNull();
        Assertions.assertThat(publisherDto.getId()).isEqualTo(given.getId());
        Assertions.assertThat(publisherDto.getBookPublisherTitle()).isEqualTo(given.getBookPublisherTitle());

    }

    @Test
    void shouldGetPublisherEntitiesFromDatabaseWithoutCreatingNewPublisher() {
        //Given
        BookPublisher publisher1 = BookPublisher
                .builder()
                .bookPublisherTitle("Heyne")
                .build();

        BookPublisher publisher2 = BookPublisher
                .builder()
                .bookPublisherTitle("Galileo Computing")
                .build();

        bookPublisherRepository.save(publisher1);
        bookPublisherRepository.save(publisher2);
        Long numberOfSavedBookPublishers = bookPublisherRepository.count();

        //When
        String newPublisher = "Heyne";
        BookPublisher result = bookPublisherService.getPublisher(newPublisher);
        Long updatedNumberOfSavedBookPublishers = bookPublisherRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(publisher1.getId());
        Assertions.assertThat(updatedNumberOfSavedBookPublishers).isEqualTo(numberOfSavedBookPublishers);
    }

    @Test
    void shouldGetPublisherEntitiesFromDatabaseAndCreateANewPublisher() {
        //Given
        BookPublisher publisher1 = BookPublisher
                .builder()
                .bookPublisherTitle("Heyne")
                .build();

        BookPublisher publisher2 = BookPublisher
                .builder()
                .bookPublisherTitle("Galileo Computing")
                .build();

        bookPublisherRepository.save(publisher1);
        bookPublisherRepository.save(publisher2);
        long numberOfSavedBookPublishers = bookPublisherRepository.count();

        //When
        String newPublisher = "Hanser";
        BookPublisher result = bookPublisherService.getPublisher(newPublisher);
        long updatedNumberOfSavedBookPublishers = bookPublisherRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isNotEqualTo(publisher1.getId());
        Assertions.assertThat(result.getId()).isNotEqualTo(publisher2.getId());
        Assertions.assertThat(updatedNumberOfSavedBookPublishers).isEqualTo(numberOfSavedBookPublishers + 1);
    }

}
