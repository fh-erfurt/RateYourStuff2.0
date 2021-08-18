package de.fourzerofournotfound.rateyourstuff.rays.repositories.media.collections;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.collections.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.movies.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active = test")
public class CollectionRepositoryTest {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private MovieRepository movieRepository;

    private User testUser;

    @BeforeEach
    void beforeEach() {
        collectionRepository.deleteAll();
        movieRepository.deleteAll();
        createTestUser();
    }

    void createTestUser() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        loginRepository.deleteAll();

        //Given
        Login givenLogin = Login.builder()
                .email("mickey.knop@fh-erfurt.de")
                .passwordHash("1234")
                .isEnabled(false)
                .build();

        Role userRole = Role.builder().roleName("User").build();
        roleRepository.save(userRole);

        testUser = User.builder()
                .firstName("Mickey")
                .lastName("Knop")
                .secondName("noe")
                .gender("m")
                .userName("mikmin")
                .login(givenLogin)
                .role(userRole)
                .build();
        testUser = userRepository.save(testUser);
    }

    @Test
    void shouldSaveNewCollection() {
        //Given
        Collection given = Collection.builder().title("TestCollection").user(testUser).build();

        //When
        Collection result = collectionRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull();
    }

    @Test
    void shouldFindCollectionThatIncludesMedia() {
        //Given
        Collection given = Collection.builder().title("TestCollection").media(new HashSet<>()).user(testUser).build();
        Collection otherCollection = Collection.builder().title("Test Two Collection").media(new HashSet<>()).user(testUser).build();
        Movie movie1 = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();
        movieRepository.save(movie1);

        given.getMedia().add(movie1);
        collectionRepository.save(given);
        collectionRepository.save(otherCollection);

        //When
        List<Collection> result = collectionRepository.findAllByMediaId(movie1.getId(), Pageable.unpaged()).getContent();

        //Then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void shouldFindCollectionFromUser() {
        //Given
        Collection given = Collection.builder().title("TestCollection").media(new HashSet<>()).user(testUser).build();
        Collection otherCollection = Collection.builder().title("Test Two Collection").media(new HashSet<>()).user(testUser).build();

        collectionRepository.save(given);
        collectionRepository.save(otherCollection);

        //When
        List<Collection> result = collectionRepository.findAllByUserId(testUser.getId(), Pageable.unpaged()).getContent();

        //Then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void shouldUpdateCollectionTitle() {
        //Given
        Collection given = Collection.builder().title("TestCollection").media(new HashSet<>()).user(testUser).build();
        collectionRepository.save(given);

        //When
        String updatedTitle = "updatedTitle";
        given.setTitle(updatedTitle);
        Collection result = collectionRepository.save(given);

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTitle()).isEqualTo(updatedTitle);
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
    }

    @Test
    void shouldRemoveCollection() {
        //Given
        Collection given = Collection.builder().title("TestCollection").media(new HashSet<>()).user(testUser).build();
        Movie movie1 = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();
        movieRepository.save(movie1);

        given.getMedia().add(movie1);
        collectionRepository.save(given);

        //When
        Long mediumId = movie1.getId();
        Long collectionId = given.getId();

        collectionRepository.delete(given);
        Optional<Collection> foundCollection = collectionRepository.findById(collectionId);
        Optional<Movie> foundMovie = movieRepository.findById(mediumId);

        //Then
        Assertions.assertThat(foundCollection).isNotPresent();
        Assertions.assertThat(foundMovie).isPresent();
        Assertions.assertThat(foundMovie.get().getId()).isEqualTo(mediumId);
    }
}
