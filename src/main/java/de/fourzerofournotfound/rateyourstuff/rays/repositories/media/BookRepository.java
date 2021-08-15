package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * <h1>BookRepository</h1>
 * <p>This Interface can be used to find Book entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByMediumName(String bookName);
    Optional<Book> findByIsbn(String bookIsbn);

    List<Book> findAllByBookPublisher(BookPublisher bookPublisher);

    Optional<Book> findBookByMediumNameIgnoreCaseAndReleaseDateOrIsbn(String mediumName, LocalDate releaseDate, String isbn);

    Optional<Book> findBookByIdNotAndIsbn(Long id, String isbn);

}
