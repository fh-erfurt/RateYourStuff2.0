package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Page<Episode> findAll(Pageable pageable);
    Page<Episode> findAllBySeasonId(Long id, Pageable pageable);

    Optional<Episode> findByMediumName (String episodeTitle);

    Optional<Episode> findEpisodeByIdNotAndMediumNameIgnoreCaseAndReleaseDateAndSeasonIdNot(Long id, String mediumName, LocalDate releaseDate, Long seasonId);

    Optional<Episode> findEpisodeByMediumNameIgnoreCaseAndReleaseDateAndEpisodeNumber(String mediumName, LocalDate releaseDate, int episodeNumber);
}
