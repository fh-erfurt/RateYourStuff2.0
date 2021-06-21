package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.ISBNCheckService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(properties = "spring.profiles.active = test")
public class ISBNCheckServiceTest {
    @Autowired
    ISBNCheckService isbnCheckService;

    @Test
    public void shouldAcceptGivenBookWithISBN10 () {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3-551-35405-7", true, true, 1021);

        //When
        boolean isValidISBN = isbnCheckService.checkIfISBNisValid(given);

        //Then
        Assertions.assertThat(isValidISBN).isTrue();
    }

    @Test
    public void shouldAcceptGivenBookWithISBN13 () {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "978-3-5513-5405-1", true, true, 1021);

        //When
        boolean isValidISBN = isbnCheckService.checkIfISBNisValid(given);

        //Then
        Assertions.assertThat(isValidISBN).isTrue();
    }

    @Test
    public void shouldNotAcceptGivenBookWithInvalidISBNFormatting() {
        //Given
        Book given = new Book("Harry Potter und der Orden des Phönix", "bestes Buch der Reihe", LocalDate.now(), "3551555559", true, true, 1021);

        //When
        boolean isValidISBN = isbnCheckService.checkIfISBNisValid(given);

        //Then
        Assertions.assertThat(isValidISBN).isFalse();
    }
}
