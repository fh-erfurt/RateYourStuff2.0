package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <h1>CollectionRepository</h1>
 * <p>This Interface can be used to find colelction entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 */
@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Page<Collection> findAllByUserId(Long id, Pageable pageable);
    Set<Collection> findAllByUserId(Long id);
    Page<Collection> findAllByMediaId(Long id, Pageable pageable);
}
