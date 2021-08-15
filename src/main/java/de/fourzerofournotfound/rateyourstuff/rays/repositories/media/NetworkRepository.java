package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1>NetworkRepository</h1>
 * <p>This Interface can be used to find Network entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 */
@Repository
public interface NetworkRepository extends JpaRepository<Network, Long> {
    Optional<Network> findByNetworkTitle(String networkTitle);
}
