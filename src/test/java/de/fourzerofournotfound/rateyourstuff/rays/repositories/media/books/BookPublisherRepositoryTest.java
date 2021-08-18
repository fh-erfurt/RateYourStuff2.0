package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books.BookPublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class BookPublisherRepositoryTest {
    @Autowired
    private BookPublisherRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveBookPublisher() {
        //Given
        BookPublisher given = new BookPublisher("TOR");

        //When
        BookPublisher result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void shouldFindBookPublisherByTitle() {
        //Given
        BookPublisher given1 = new BookPublisher("TOR");
        BookPublisher given2 = new BookPublisher("HEYNE");

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<BookPublisher> result = repository.findByBookPublisherTitle("TOR");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getBookPublisherTitle()).isEqualTo("TOR");
    }

    @Test
    void shouldFindAllBookPublishers() {
        //Given
        BookPublisher given1 = new BookPublisher("TOR");
        BookPublisher given2 = new BookPublisher("HEYNE");

        List<BookPublisher> persisted = new ArrayList<>();
        persisted.add(repository.save(given1));
        persisted.add(repository.save(given2));

        //When
        List<BookPublisher> results = repository.findAll();

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    public void shouldUpdateBookPublisher() {
        //Given
        BookPublisher given = new BookPublisher("TOR");
        BookPublisher saved = repository.save(given);

        //When
        saved.setBookPublisherTitle("HEYNE");
        BookPublisher result = repository.save(saved);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getBookPublisherTitle()).isEqualTo("HEYNE");
    }

    @Test
    public void shouldDeleteBookPublisher() {
        //Given
        BookPublisher given = new BookPublisher("TOR");
        BookPublisher saved = repository.save(given);

        //When
        repository.delete(given);
        Optional<BookPublisher> result = repository.findById(saved.getId());

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}
