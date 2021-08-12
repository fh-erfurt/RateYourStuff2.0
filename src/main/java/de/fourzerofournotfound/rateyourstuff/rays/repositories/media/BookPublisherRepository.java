package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookPublisherRepository extends JpaRepository<BookPublisher, Long> {
    Optional<BookPublisher> findByBookPublisherTitle(String bookPublisherTitle);
}
