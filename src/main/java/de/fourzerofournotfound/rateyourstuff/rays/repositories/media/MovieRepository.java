package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;


import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;




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

    List<Movie> findByMediumNameLike(String mediumName);

    List<Movie> findByMediumNameContaining(String mediumName);

    List<Movie> findByMediumNameContains(String mediumName);

    List<Movie> findByMediumNameIsContaining(String mediumName);

    List<Movie> findByMediumNameContainingIgnoreCase(String mediumName);

    List<Movie> findByMediumNameLikeIgnoreCase(String mediumName);

    @Query("SELECT m FROM Movie m WHERE m.mediumName LIKE %:mediumName%")
    List<Movie> searchByMediumNameLike(@Param("mediumName") String mediumName);

    //-----------------------------------------------------------------------//



}
