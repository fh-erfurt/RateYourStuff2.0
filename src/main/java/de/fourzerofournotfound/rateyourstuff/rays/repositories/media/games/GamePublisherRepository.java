package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.GamePublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * GamePublisherRepository
 * <p>This Interface can be used to find GamePublisher entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface GamePublisherRepository extends JpaRepository<GamePublisher, Long> {
    Optional<GamePublisher> findByGamePublisherTitle(String gamePublisherTitle);
}
