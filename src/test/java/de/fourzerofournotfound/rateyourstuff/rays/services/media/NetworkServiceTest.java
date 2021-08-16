package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.NetworkDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.NetworkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active = test")
public class NetworkServiceTest {
    @Autowired
    private NetworkService networkService;

    @Autowired
    private NetworkRepository networkRepository;

    @BeforeEach
    void beforeEach()
    {
        networkRepository.deleteAll();
    }

    @Test
    void networkDtoShouldMatchNetwork() {
        //Given
        Network given = Network
                .builder()
                .networkTitle("CBS")
                .build();
        networkRepository.save(given);

        //When
        NetworkDto networkDto = networkService.convertToDto(given);

        //Then
        Assertions.assertThat(networkDto.getId()).isNotNull();
        Assertions.assertThat(networkDto.getId()).isEqualTo(given.getId());
        Assertions.assertThat(networkDto.getNetworkTitle()).isEqualTo(given.getNetworkTitle());

    }

    @Test
    void shouldReturnNetworkEntityWithoutCreatingANewNetwork() {
        //Given
        Network network1 = Network.builder().networkTitle("CBS").build();
        Network network2 = Network.builder().networkTitle("ABC").build();
        networkRepository.save(network1);
        networkRepository.save(network2);
        Long numberOfSavedNetworks = networkRepository.count();

        //When
        Network result = networkService.getNetwork("CBS");
        Long updatedNumberOfSavedNetworks = networkRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(network1.getId());
        Assertions.assertThat(updatedNumberOfSavedNetworks).isEqualTo(numberOfSavedNetworks);
    }

    @Test
    void shouldReturnNetworkEntityAndCreateANewNetwork() {
        //Given
        Network network1 = Network.builder().networkTitle("CBS").build();
        Network network2 = Network.builder().networkTitle("ABC").build();
        networkRepository.save(network1);
        networkRepository.save(network2);
        long numberOfSavedNetworks = networkRepository.count();

        //When
        Network result = networkService.getNetwork("CWS");
        long updatedNumberOfSavedNetworks = networkRepository.count();

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isNotEqualTo(network1.getId());
        Assertions.assertThat(result.getId()).isNotEqualTo(network2.getId());
        Assertions.assertThat(updatedNumberOfSavedNetworks).isEqualTo(numberOfSavedNetworks + 1);
    }
}
