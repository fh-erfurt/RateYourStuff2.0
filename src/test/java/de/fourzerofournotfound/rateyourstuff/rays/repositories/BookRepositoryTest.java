package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
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
    public void afterEach(){bookRepository.deleteAll();}

    @Test
    void should_save_book(){
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);

        //When
        Book result = bookRepository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_save_book_with_publisher(){
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Publisher publisher = new Publisher("Carlsen");
        given.setPublisher(publisher);

        //When
        Book result = bookRepository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getPublisher().getPublisherId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_update_description_of_book(){
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Book saved = bookRepository.save(given);
        String initialDescription = saved.getShortDescription();

        //When
        String updatedShortDescription = "Ok, zweitbestes Halbblutprinz hat gerockt";
        saved.setShortDescription(updatedShortDescription);
        Book result = bookRepository.save(saved);

        //Then
        Assertions.assertThat(result.getMediumId()).isEqualTo(saved.getMediumId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo(updatedShortDescription);

    }

    @Test
    void should_find_book_by_title(){
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
    void should_find_book_by_isbn(){
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
    void should_find_book_with_publisher(){
        //Given
        //Given
        Book given1 = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);
        Publisher publisher1 = new Publisher("Carlsen");
        given1.setPublisher(publisher1);

        Book given2 = new Book("Ready Player One", "Film ist gut, aber das Buch ist besser", LocalDate.now(), "9783596296590", true, true, 538);
        Publisher publisher2 = new Publisher("Tor");

        given1.setPublisher(publisher1);
        given2.setPublisher(publisher2);

        List<Book> persisted = new ArrayList<>();
        persisted.add(bookRepository.save(given1));
        persisted.add(bookRepository.save(given2));

        //When
        List<Book> results = bookRepository.findAllByPublisher(publisher2);

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    void should_delete_book_from_database(){
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