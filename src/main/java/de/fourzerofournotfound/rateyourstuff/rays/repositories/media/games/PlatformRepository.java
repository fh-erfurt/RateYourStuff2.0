package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PlatformRepository
 * <p>This Interface can be used to find Platform entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findByPlatformTitle(String platformTitle);
}
