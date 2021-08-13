package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Book;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class BookServiceTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @BeforeEach
    void beforeEach()
    {
        bookRepository.deleteAll();
    }

    @Test
    void bookDtoShouldMatchBook() {
        //Given
        Book book = Book.builder()
                .mediumName("Halo - The fall of reach")
                .releaseDate(LocalDate.of(2005,2,7))
                .shortDescription("The official prequel to the award-winning Xbox(TM) game, one of the bestselling computer games of recent years.")
                .isEBook(false)
                .isPrint(true)
                .isbn("9781841494203")
                .numberOfPages(352)
                .build();

        Book given = bookRepository.save(book);
        System.out.println(given.getId());
        //When
        BookDto result = bookService.convertToDto(given);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getIsbn()).isEqualTo(given.getIsbn());
        Assertions.assertThat(result.getIsEBook()).isEqualTo(given.getIsEBook());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(given.getShortDescription());
        Assertions.assertThat(result.getMediumName()).isEqualTo(given.getMediumName());
        Assertions.assertThat(result.getReleaseDate()).isEqualTo(given.getReleaseDate().toString());
    }

}
