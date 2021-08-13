package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findAllByMedium(Medium medium);

    Page<Season> findAllByMediumId(Long id, Pageable pageable);

    Optional<Season> findSeasonByIdNotAndSeasonTitleIgnoreCaseAndSeasonNumber(Long id, String seasonTitle, int seasonNumber);
    Optional<Season> findSeasonSeasonByIdNotAndSeasonNumberAndMediumId(Long id, Integer seasonNumber, Long mediumId);

    Optional<Season> findSeasonBySeasonTitleIgnoreCaseAndSeasonNumber(String seasonTitle, int seasonNumber);
    Optional<Season> findSeasonBySeasonNumberAndMediumId(Integer seasonNumber, Long mediumId);
}
