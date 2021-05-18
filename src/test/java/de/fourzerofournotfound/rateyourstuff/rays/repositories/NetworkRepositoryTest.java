package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=test")
public class NetworkRepositoryTest {
    @Autowired
    private NetworkRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    void should_save_network() {
        //Given
        Network given = new Network("CBS");

        //When
        Network result = repository.save(given);

        //Then
        Assertions.assertThat(result.getNetworkId()).isNotNull().isGreaterThan(0);
    }

    @Test
    void should_find_network_by_title () {
        //Given
        Network given1 = new Network("CBS");
        Network given2 = new Network("ABC");

        repository.save(given1);
        repository.save(given2);

        //When
        Optional<Network> result = repository.findByNetworkTitle("CBS");

        //Then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getNetworkTitle()).isEqualTo("CBS");
    }

    @Test
    void should_find_all_networks() {
        //Given
        Network given1 = new Network("CBS");
        Network given2 = new Network("ABC");

        List<Network> persisted = new ArrayList<>();
        persisted.add(repository.save(given1));
        persisted.add(repository.save(given2));

        //When
        List<Network> results = repository.findAll();

        //Then
        Assertions.assertThat(results).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(persisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    public void should_update_network() {
        //Given
        Network given = new Network("DBS");
        Network saved = repository.save(given);

        //When
        saved.setNetworkTitle("CBS");
        Network result = repository.save(saved);
        //Then
        Assertions.assertThat(result.getNetworkId()).isEqualTo(saved.getNetworkId());
        Assertions.assertThat(result.getNetworkTitle()).isEqualTo("CBS");
    }

    @Test
    public void should_delete_network () {
        //Given
        Network given = new Network("CBS");
        repository.save(given);

        //When
        repository.delete(given);
        Optional<Network> result = repository.findByNetworkTitle("CBS");

        //Then
        Assertions.assertThat(result).isNotPresent();
    }

}
