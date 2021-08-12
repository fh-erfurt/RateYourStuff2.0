package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamePublisherRepository extends JpaRepository<GamePublisher, Long> {
    Optional<GamePublisher> findByGamePublisherTitle(String gamePublisherTitle);
}
