package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * <h1>GameRepository</h1>
 * <p>This Interface can be used to find Game entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findAll(Pageable pageable);
    Optional<Game> findByMediumName(String gameName);
    Optional<Game> findGameByMediumNameIgnoreCaseAndReleaseDate(String mediumName, LocalDate releaseDate);
    Optional<Game> findGameByIdNotAndMediumNameIgnoreCaseAndReleaseDate(Long id, String mediumName, LocalDate releaseDate);
}

