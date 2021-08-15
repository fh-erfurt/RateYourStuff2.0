package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * <h1>MovieRepository</h1>
 * <p>This Interface can be used to find Movie entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAll(Pageable pageable);

    Optional<Movie> findByMediumName(String movieName);

    List<Movie> findAllByNetwork(Network network);

    Optional<Movie> findMovieByMediumNameIgnoreCaseAndReleaseDate(String mediumName, LocalDate releaseDate);

    Optional<Movie> findMovieByIdNotAndMediumNameIgnoreCaseAndReleaseDate(Long id, String mediumName, LocalDate releaseDate);

}
