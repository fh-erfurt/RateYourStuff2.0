package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByMediumName(String bookName);
    Optional<Book> findByIsbn(String bookIsbn);

    List<Book> findAllByBookPublisher(BookPublisher bookPublisher);

    Optional<Book> findBookByMediumNameIgnoreCaseAndReleaseDate(String mediumName, LocalDate releaseDate);

    Optional<Book> findBookByIdNotAndMediumNameIgnoreCaseAndReleaseDate(Long id, String mediaName, LocalDate releaseDate);

}
