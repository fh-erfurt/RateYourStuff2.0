package de.fourzerofournotfound.rateyourstuff.rays.repositories;


import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class RatingRepositoryTest {

    @Autowired
    RatingRepository ratingRepository;

    @AfterEach
    public void afterEach(){ratingRepository.deleteAll();}

    @Test
    public void should_save_rating(){
        //Given

        LocalDateTime localDate = LocalDateTime.now();


//        Rating given = new Rating();
//        given.setCreatedAt(localDate);
//        given.setMinimumPoints(0);
//        given.setMaximumPoints(10);
//        given.setGivenPoints(7);
//        given.setDesscription(description);

        Rating given = Rating.builder()
                        .minimumPoints(0)
                        .maximumPoints(10)
                        .givenPoints(5)
                        .desscription("Heute wird es nicht so lustig")
                        .build();

        //When
        Rating result = ratingRepository.save(given);

        //Then
        Assertions.assertThat(result.getRatingId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void should_find_rating_by_givenPoints(){

        //Given
        LocalDateTime localDate = LocalDateTime.now();

        Rating.builder()
                .minimumPoints(0)
                .maximumPoints(10)
                .givenPoints(5)
                .desscription("Heute wird es nicht so lustig")
                .build();

        ratingRepository.save(Rating.builder()
                .minimumPoints(0)
                .maximumPoints(10)
                .givenPoints(5)
                .desscription("Heute wird es nicht so lustig")
                .build());

        //When
        Optional<Rating> result = ratingRepository.findByGivenPoints(5);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getGivenPoints()).isEqualTo(5);
    }
}
