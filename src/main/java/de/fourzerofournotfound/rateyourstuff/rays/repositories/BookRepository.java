package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.BookPublisher;
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

    Optional<Book> findBookByMediumNameIgnoreCaseAndReleaseDateOrIsbn(String mediumName, LocalDate releaseDate, String isbn);

    Optional<Book> findBookByIdNotAndMediumNameIgnoreCaseAndReleaseDateOrIsbn(Long id, String mediaName, LocalDate releaseDate, String isbn);

}
