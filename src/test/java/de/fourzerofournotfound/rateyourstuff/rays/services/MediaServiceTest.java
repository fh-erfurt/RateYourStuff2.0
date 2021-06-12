package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MediaRepository;
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

    @BeforeEach
    void beforeEach()
    {
        bookRepository.deleteAll();
    }

    @Test
    void shouldFindDuplicates()
    {
        //Given
        MediaService mediaService = new MediaService();

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
        Medium saved = bookRepository.save(testMedia);
        mediaService.isValidMediaName(saved);


        //Then
        assertTrue(mediaService.isValidMediaName(saved));
    }

}