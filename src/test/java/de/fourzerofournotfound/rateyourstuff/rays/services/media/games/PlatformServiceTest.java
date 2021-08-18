package de.fourzerofournotfound.rateyourstuff.rays.services.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.games.PlatformDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.games.PlatformRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.games.PlatformService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest(properties = "spring.profiles.active = test")
public class PlatformServiceTest {
    @Autowired
    private PlatformService platformService;

    @Autowired
    private PlatformRepository platformRepository;

    @BeforeEach
    void beforeEach() {
        platformRepository.deleteAll();
    }

    @Test
    void platformDtoShouldMatchPlatform() {
        //Given
        Platform given = Platform
                .builder()
                .platformTitle("Playstation")
                .build();
        platformRepository.save(given);

        //When
        PlatformDto platformDto = platformService.convertToDto(given);

        //Then
        Assertions.assertThat(platformDto.getId()).isEqualTo(given.getId());
        Assertions.assertThat(platformDto.getId()).isNotNull();
        Assertions.assertThat(platformDto.getPlatformTitle()).isEqualTo(given.getPlatformTitle());

    }

    @Test
    void shouldGetPlatformEntitiesFromDatabaseWithoutCreatingANewPlatform() {
        //Given
        Platform platform1 = Platform.builder().platformTitle("PS Vita").build();
        Platform platform2 = Platform.builder().platformTitle("Nintendo GameCube").build();
        platformRepository.save(platform1);
        platformRepository.save(platform2);
        Long numberOfSavedPlatforms = platformRepository.count();

        //When
        List<String> platformStrings = new ArrayList<>();
        platformStrings.add("PS Vita");
        platformStrings.add("Nintendo GameCube");

        Set<Platform> result = platformService.getPlatformSet(platformStrings);
        Long updatedNumberOfSavedPlatforms = platformRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(platformStrings.size());
        Assertions.assertThat(updatedNumberOfSavedPlatforms).isEqualTo(numberOfSavedPlatforms);
    }

    @Test
    void shouldGetPlatformEntitiesFromDatabaseAndCreateANewPlatform() {
        //Given
        Platform platform1 = Platform.builder().platformTitle("PS Vita").build();
        Platform platform2 = Platform.builder().platformTitle("Nintendo GameCube").build();
        platformRepository.save(platform1);
        platformRepository.save(platform2);
        long numberOfSavedPlatforms = platformRepository.count();

        //When
        List<String> platformStrings = new ArrayList<>();
        platformStrings.add("Nintendo Switch");
        platformStrings.add("Nintendo GameCube");

        Set<Platform> result = platformService.getPlatformSet(platformStrings);
        long updatedNumberOfSavedPlatforms = platformRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(platformStrings.size());
        Assertions.assertThat(updatedNumberOfSavedPlatforms).isEqualTo(numberOfSavedPlatforms + 1);
    }
}
