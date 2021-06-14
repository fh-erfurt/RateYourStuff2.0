package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active = test")
class MediaServiceTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    MediaService mediaService;

    @BeforeEach
    void beforeEach()
    {
        bookRepository.deleteAll();
    }

    @Test
    void shouldFindDuplicatesOfGivenBook()
    {
        //Given
        LocalDate release = LocalDate.of(2005,02,07);

        Book testMedia = Book.builder()
                .mediumName("Halo - The fall of reach")
                .releaseDate(release)
                .shortDescription("The official prequel to the award-winning Xbox(TM) game, one of the bestselling computer games of recent years.")
                .isEBook(false)
                .isPrint(true)
                .isbn("9781841494203")
                .numberOfPages(352)
                .build();

        //When
        Book saved = bookRepository.save(testMedia);
        mediaService.isValidBook(saved);


        //Then
        /**
         * @Test shows that isValidBook finds a duplicate of the given book already in saved database
         */
        Assertions.assertTrue(mediaService.isValidBook(testMedia));
        /**
         * @Test shows that isValidBook is detecting duplicates of a book which is already saved in database
         */
        Assertions.assertTrue(mediaService.isValidBook(saved));
    }

    //TODO: TestCase for isValidGame -> shouldFindDuplicationOfGivenGame
    //TODO: TestCase for isValidMovie -> shouldFindDuplicationOfGivenMovie
    //TODO: TestCase for isValidSeries -> shouldFindDuplicationOfGivenSeries

}