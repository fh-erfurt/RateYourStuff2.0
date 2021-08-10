package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Page<Rating> findAll(Pageable pageable);
    Page<Rating> findAllByMediumId(Long mediumId, Pageable pageable);
    Page<Rating> findAllByUserId(Long userId, Pageable pageable);

    Long countAllByMediumId(Long mediumId );

    Optional<Rating> findByGivenPoints(Integer givenPoints);
}


