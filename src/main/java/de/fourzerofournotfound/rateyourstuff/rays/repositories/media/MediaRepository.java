package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>MediaRepository</h1>
 * <p>This Interface can be used to find Media entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface MediaRepository extends JpaRepository<Medium, Long> {

    Page<Medium> findAllByCollectionsId(Long id, Pageable pageable);
}
