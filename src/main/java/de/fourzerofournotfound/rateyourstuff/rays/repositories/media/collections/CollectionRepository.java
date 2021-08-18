package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.collections;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.collections.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>This Interface can be used to find colelction entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Page<Collection> findAllByUserId(Long id, Pageable pageable);
    Page<Collection> findAllByMediaId(Long id, Pageable pageable);
}
