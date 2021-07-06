package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findAllByMedium(Medium medium);

    List<Season> findAllByMediumId(Long id);

    Optional<Season> findSeasonByIdNotAndSeasonTitleIgnoreCaseAndSeasonNumber(Long id, String seasonTitle, int seasonNumber);

    Optional<Season> findSeasonBySeasonTitleIgnoreCaseAndSeasonNumber(String seasonTitle, int seasonNumber);
}
