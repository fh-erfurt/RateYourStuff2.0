package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@SpringBootTest(properties = "spring.profiles.active=test")
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    public void afterEach() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldSaveBook() {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);

        //When
        Book result = bookRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldSaveBookWithPublisher() {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        BookPublisher publisher = new BookPublisher("Carlsen");
        given.setBookPublisher(publisher);

        //When
        Book result = bookRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getBookPublisher().getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldUpdateDescriptionOfBook() {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Book saved = bookRepository.save(given);

        //When
        String updatedShortDescription = "Ok, zweitbestes Halbblutprinz hat gerockt";
        saved.setShortDescription(updatedShortDescription);
        Book result = bookRepository.save(saved);

        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedShortDescription);

    }

    @Test
    void shouldFindBookByTitle() {
        //Given
        Book given1 = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Book given2 = new Book("Harry Potter und die Kammer des Schreckens", "schlechtestes Buch der Reihe", LocalDate.now(), "3551551685", true, true, 351);

        bookRepository.save(given1);
        bookRepository.save(given2);

        //When
        Optional<Book> result = bookRepository.findByMediumName("Harry Potter und der Orden des Phönix");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Harry Potter und der Orden des Phönix");

    }

    @Test
    void shouldFindBookByIsbn() {
        //Given
        Book given1 = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Book given2 = new Book("Harry Potter und die Kammer des Schreckens", "schlechtestes Buch der Reihe", LocalDate.now(), "3551551685", true, true, 351);

        bookRepository.save(given1);
        bookRepository.save(given2);

        //When
        Optional<Book> result = bookRepository.findByIsbn("3551551685");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getIsbn()).isEqualTo("3551551685");

    }


    @Test
    void shouldFindBookWithPublisher() {
        //Given
        Book given1 = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        BookPublisher publisher1 = new BookPublisher("Carlsen");
        given1.setBookPublisher(publisher1);

        Book given2 = new Book("Ready Player One", "Film ist gut, aber das Buch ist besser", LocalDate.now(), "9783596296590", true, true, 538);
        BookPublisher publisher2 = new BookPublisher("Tor");

        given1.setBookPublisher(publisher1);
        given2.setBookPublisher(publisher2);

        List<Book> persisted = new ArrayList<>();
        persisted.add(bookRepository.save(given1));
        persisted.add(bookRepository.save(given2));

        //When
        List<Book> results = bookRepository.findAllByBookPublisher(publisher2);

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    void shouldDeleteBookFromDatabase() {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Book saved = bookRepository.save(given);

        //When
        bookRepository.delete(saved);
        Optional<Book> result = bookRepository.findByMediumName("Harry Potter und der Orden des Phönix");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}
