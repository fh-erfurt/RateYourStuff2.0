package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1>SeasonRepository</h1>
 * <p>This Interface can be used to find Season entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 */
@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Page<Season> findAllByMediumId(Long id, Pageable pageable);

    Optional<Season> findSeasonByIdNotAndSeasonNumberAndMediumId(Long id, Integer seasonNumber, Long mediumId);
    Optional<Season> findSeasonBySeasonNumberAndMediumId(Integer seasonNumber, Long mediumId);
}
