package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1>BookPublisherRepository</h1>
 * <p>This Interface can be used to find BookPublisher entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 */
@Repository
public interface BookPublisherRepository extends JpaRepository<BookPublisher, Long> {
    Optional<BookPublisher> findByBookPublisherTitle(String bookPublisherTitle);
}
