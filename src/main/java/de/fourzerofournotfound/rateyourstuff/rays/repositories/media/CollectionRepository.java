package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Page<Collection> findAllByUserId(Long id, Pageable pageable);
    Page<Collection> findAllByMediaId(Long id, Pageable pageable);
    Set<Collection> findAllByUserIdAndMediaIdNot(Long userId, Long mediaId);
    Set<Collection> findAllByMediaIdNot(Long mediaId);
    Set<Collection> findAllByUserIdAndMediaNot(Long userId, Medium medium);
}
