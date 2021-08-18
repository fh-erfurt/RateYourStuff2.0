package de.fourzerofournotfound.rateyourstuff.rays.repositories;


import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class RatingRepositoryTest {

    @Autowired
    RatingRepository ratingRepository;

    @AfterEach
    public void afterEach() {
        ratingRepository.deleteAll();
    }

    @Test
    public void shouldSaveRating() {
        //Given
        Rating given = Rating.builder()
                .givenPoints(5)
                .description("Heute wird es nicht so lustig")
                .build();

        //When
        Rating result = ratingRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void shouldFindRatingByGivenPoints() {

        //Given
        Rating.builder()
                .givenPoints(5)
                .description("Heute wird es nicht so lustig")
                .build();

        ratingRepository.save(Rating.builder()
                .givenPoints(5)
                .description("Heute wird es nicht so lustig")
                .build());

        //When
        Optional<Rating> result = ratingRepository.findByGivenPoints(5);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getGivenPoints()).isEqualTo(5);
    }
}
