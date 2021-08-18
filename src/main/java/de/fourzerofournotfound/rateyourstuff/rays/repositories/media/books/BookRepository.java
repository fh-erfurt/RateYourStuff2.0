package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * BookRepository
 * <p>This Interface can be used to find Book entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByMediumName(String bookName);

    Optional<Book> findByIsbn(String bookIsbn);

    List<Book> findAllByBookPublisher(BookPublisher bookPublisher);

    Optional<Book> findBookByMediumNameIgnoreCaseAndReleaseDateOrIsbn(String mediumName, LocalDate releaseDate, String isbn);

    Optional<Book> findBookByIdNotAndIsbn(Long id, String isbn);

}
