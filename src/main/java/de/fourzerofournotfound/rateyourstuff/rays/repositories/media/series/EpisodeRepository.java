package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>This Interface can be used to find Episode entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Page<Episode> findAll(Pageable pageable);

    Page<Episode> findAllBySeasonId(Long id, Pageable pageable);

    Optional<Episode> findByMediumName(String episodeTitle);

    Optional<Episode> findEpisodeByIdNotAndEpisodeNumberAndSeasonId(Long id, Integer episodeNumber, Long seasonId);

    Optional<Episode> findEpisodeByEpisodeNumberAndSeasonId(Integer episodeNumber, Long seasonId);
}
