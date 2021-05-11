package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;

public interface SeriesRepository extends CrudRepository<HttpStatus.Series, Long> {
}
