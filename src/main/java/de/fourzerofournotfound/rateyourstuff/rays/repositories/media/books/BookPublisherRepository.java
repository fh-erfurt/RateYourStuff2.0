package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * BookPublisherRepository
 * <p>This Interface can be used to find BookPublisher entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface BookPublisherRepository extends JpaRepository<BookPublisher, Long> {
    Optional<BookPublisher> findByBookPublisherTitle(String bookPublisherTitle);
}
