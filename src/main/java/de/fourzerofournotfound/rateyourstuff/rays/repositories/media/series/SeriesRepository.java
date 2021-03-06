package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * <p>This Interface can be used to find Series entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Series> findAll(Pageable pageable);

    Optional<Series> findByMediumName(String mediumName);

    Optional<Series> findSeriesByMediumNameIgnoreCaseAndReleaseDate(String mediumName, LocalDate releaseDate);

    Optional<Series> findSeriesByIdNotAndMediumNameIgnoreCaseAndReleaseDate(Long id, String mediumName, LocalDate releaseDate);
}
