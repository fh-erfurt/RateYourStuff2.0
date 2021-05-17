package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest(properties = "spring.profiles.active=test")
public class SeriesRepositoryTest {
    @Autowired
    SeriesRepository repository;

    @AfterEach
    public void afterEach() {
        //repository.deleteAll();
    }

    @Test
    void saveSeries() {
        //Given
        Series given = new Series("Ein Käfig voller Helden",
                "[...]",
                LocalDate.of(1965,9,17),
                20,
                12,
                true);

        //When
        Series result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void findSeriesByName() {
        //Given
        Series given1 = new Series("Ein Käfig voller Helden",
                "[...]",
                LocalDate.of(1965,9,17),
                20,
                12,
                true);

        Series given2 = new Series("M*A*S*H",
                "[...]",
                LocalDate.of(1965,9,17),
                20,
                12,
                true);

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Series> result = repository.findByMediumName("Ein Käfig voller Helden");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Ein Käfig voller Helden");
    }

    @Test
    void saveSeriesWithSeasons() {
        //Given
        Series given = new Series("Ein Käfig voller Helden",
                "[...]",
                LocalDate.of(1965,9,17),
                20,
                12,
                true);

        Season season1 = new Season(1, "");
        //TODO: Does not work, "saveSeriesWithSeasons" cannot be found
        given.getSeasons().add(season1);

        //When
        Series result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getSeasons()).isNotNull().isNotEmpty().allMatch(Objects::nonNull);

    }
}
