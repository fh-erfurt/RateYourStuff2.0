package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Page<Episode> findAll(Pageable pageable);
    Optional<Episode> findByMediumName (String episodeTitle);

    Optional<Episode> findEpisodeByIdNotAndMediumNameIgnoreCaseAndReleaseDateAndSeasonIdNot(Long id, String mediumName, LocalDate releaseDate, Long seasonId);

    Optional<Episode> findEpisodeByMediumNameIgnoreCaseAndReleaseDateAndSeasonIdNot(String mediumName, LocalDate releaseDate, Long seasonId);
}
