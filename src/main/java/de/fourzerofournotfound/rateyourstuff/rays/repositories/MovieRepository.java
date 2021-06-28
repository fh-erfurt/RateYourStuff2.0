package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAll(Pageable pageable);

    Optional<Movie> findByMediumName(String movieName);

    List<Movie> findAllByNetwork(Network network);

    Optional<Movie> findMovieByMediumNameIgnoreCaseAndReleaseDate(String mediumName, LocalDate releaseDate);

    Optional<Movie> findMovieByIdNotAndMediumNameIgnoreCaseAndReleaseDate(Long id, String mediumName, LocalDate releaseDate);

}
