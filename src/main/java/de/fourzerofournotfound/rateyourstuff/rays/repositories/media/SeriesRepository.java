package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Series> findAll(Pageable pageable);
    Optional<Series> findByMediumName(String mediumName);

    Optional<Series> findSeriesByMediumNameIgnoreCaseAndReleaseDate(String mediumName, LocalDate releaseDate);

    Optional<Series> findSeriesByIdNotAndMediumNameIgnoreCaseAndReleaseDate(Long id, String mediumName, LocalDate releaseDate);
}
