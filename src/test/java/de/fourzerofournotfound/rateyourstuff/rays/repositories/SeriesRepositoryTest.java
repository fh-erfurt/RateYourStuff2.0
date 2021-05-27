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

    @Autowired
    SeasonRepository seasonRepository;

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    void should_save_series() {
        //Given
        Series given = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        //When
        Series result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_find_series_by_name() {
        //Given
        Series given1 = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        Series given2 = Series.builder()
                .mediumName("M*A*S*H")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Series> result = repository.findByMediumName("Ein Käfig voller Helden");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getMediumName()).isEqualTo("Ein Käfig voller Helden");
    }

    @Test
    void should_save_series_with_seasons() {
        //Given
        Series given = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        Season season1 = Season.builder().seasonNumber(1).seasonTitle("").build();
        season1.setMedium(given);

        given.getSeasons().add(season1);

        //When
        Series result = repository.save(given);

        //Then
        Assertions.assertThat(result.getMediumId()).isNotNull().isGreaterThan(0);
        Assertions.assertThat(result.getSeasons()).isNotNull().isNotEmpty().allMatch(Objects::nonNull);

    }

    @Test
    public void should_update_series() {
        //Given
        Series given = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        Series saved = repository.save(given);

        //When
        saved.setShortDescription("Test");
        Series result = repository.save(saved);

        //Then
        Assertions.assertThat(result.getMediumId()).isEqualTo(saved.getMediumId());
        Assertions.assertThat(result.getShortDescription()).isEqualTo("Test");
    }

    @Test
    public void should_delete_series() {
        //Given
        Series given = Series.builder()
                .mediumName("Ein Käfig voller Helden")
                .shortDescription("[...]")
                .releaseDate(LocalDate.of(1965,9,17))
                .averageLength(20)
                .ageRestriction(12)
                .isCompleted(true)
                .build();

        Series saved = repository.save(given);

        //When
        repository.delete(saved);
        Optional<Series> result = repository.findById(saved.getMediumId());

        //Then
        Assertions.assertThat(result).isNotPresent();
    }
}
