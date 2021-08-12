package de.fourzerofournotfound.rateyourstuff.rays.repositories.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.PlatformRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class PlatformRepositoryTest {
    @Autowired
    private PlatformRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    void should_save_platform() {
        //Given
        Platform given = Platform.builder()
                .platformTitle("Nintendo Switch")
                .build();


        //When
        Platform result = repository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_find_platform_by_title () {
        //Given
        Platform given1 = Platform.builder()
                .platformTitle("Nintendo Switch")
                .build();
        Platform given2 = Platform.builder()
                .platformTitle("PS5")
                .build();

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Platform> result = repository.findByPlatformTitle("Nintendo Switch");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getPlatformTitle()).isEqualTo("Nintendo Switch");
    }

    @Test
    void should_find_all_platforms() {
        //Given
        Platform given1 = Platform.builder()
                .platformTitle("Nintendo Switch")
                .build();
        Platform given2 = Platform.builder()
                .platformTitle("PS5")
                .build();

        List<Platform> persisted = new ArrayList<>();
        persisted.add(repository.save(given1));
        persisted.add(repository.save(given2));

        //When
        List<Platform> results = repository.findAll();

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    public void should_update_platform() {
        //Given
        Platform given = Platform.builder()
                .platformTitle("Nintendo Switch")
                .build();
        Platform saved = repository.save(given);

        //When
        saved.setPlatformTitle("CBS");
        Platform result = repository.save(saved);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(result.getPlatformTitle()).isEqualTo("CBS");
    }

    @Test
    public void should_delete_platform () {
        //Given
        Platform given = Platform.builder()
                .platformTitle("Nintendo Switch")
                .build();
        Platform saved = repository.save(given);

        //When
        repository.delete(given);
        Optional<Platform> result = repository.findById(saved.getId());

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}
