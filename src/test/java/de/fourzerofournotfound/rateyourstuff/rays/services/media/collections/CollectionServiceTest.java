package de.fourzerofournotfound.rateyourstuff.rays.services.media.collections;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.collections.CollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.collections.ReducedCollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.collections.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.movies.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.collections.CollectionRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest(properties = "spring.profiles.active = test")
public class CollectionServiceTest {

    @Autowired
    private CollectionService collectionService;
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
    void collectionShouldHaveUserReference() throws UserNotFoundException {
        //Given
        Collection given = Collection.builder()
                .title("Test-Collection")
                .userMappingId(testUser.getId())
                .build();

        //When
        Throwable thrown = Assertions.catchThrowable(() -> collectionService.addReferencesToCollection(given, testUser.getId()));
        Collection result = null;
        if (Objects.isNull(thrown)) {
            result = collectionService.addReferencesToCollection(given, testUser.getId());
        }

        //Then
        Assertions.assertThat(thrown).isNull();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getUser()).isNotNull();
        Assertions.assertThat(result.getUser().getId()).isEqualTo(testUser.getId());
    }

    @Test
    void shouldNotFindUserAndThrowException() throws UserNotFoundException {
        //Given
        Collection given = Collection.builder()
                .title("Test-Collection")
                .build();

        //When
        Long nonExistingUserId = testUser.getId() + 1;
        Throwable thrown = Assertions.catchThrowable(() -> collectionService.addReferencesToCollection(given, nonExistingUserId));
        Collection result = null;
        if (Objects.isNull(thrown)) {
            result = collectionService.addReferencesToCollection(given, nonExistingUserId);
        }

        //Then
        Assertions.assertThat(thrown).isInstanceOf(UserNotFoundException.class);
        Assertions.assertThat(result).isNull();
    }

    @Test
    void collectionDtoShouldMatchCollection() {
        //Given
        Collection given = Collection.builder()
                .title("TestCollection")
                .user(testUser)
                .media(new HashSet<>())
                .build();

        Movie movie1 = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        Movie movie2 = Movie.builder()
                .mediumName("Zurück in die Zukunft 2")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        given.getMedia().add(movie1);
        given.getMedia().add(movie2);

        collectionRepository.save(given);

        //When
        CollectionDto result = collectionService.convertToDto(given);

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getUserId()).isEqualTo(given.getUser().getId());
        Assertions.assertThat(result.getUserUserName()).isEqualTo(given.getUser().getUserName());
        Assertions.assertThat(result.getMedia().size()).isEqualTo(given.getMedia().size());
        Assertions.assertThat(result.getTitle()).isEqualTo(given.getTitle());

    }

    @Test
    void reducedCollectionDtoShouldMatchCollection() {
        //Given
        Collection given = Collection
                .builder()
                .title("TestCollection")
                .user(testUser)
                .build();
        collectionRepository.save(given);

        //When
        ReducedCollectionDto result = collectionService.convertToReducedDto(given);

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(given.getId());
        Assertions.assertThat(result.getTitle()).isEqualTo(given.getTitle());

    }

    @Test
    void shouldReturnCollectionsThatDoesNotIncludeGivenMedium() {
        //Given
        Collection collection1 = Collection.builder()
                .title("TestCollection")
                .user(testUser)
                .media(new HashSet<>())
                .build();

        Collection collection2 = Collection.builder()
                .title("OtherCollection")
                .user(testUser)
                .media(new HashSet<>())
                .build();

        Movie movie1 = Movie.builder()
                .mediumName("Zurück in die Zukunft")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        Movie movie2 = Movie.builder()
                .mediumName("Zurück in die Zukunft 2")
                .shortDescription("[...]")
                .releaseDate(LocalDate.now())
                .length(90)
                .ageRestriction(6)
                .build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        collection1.getMedia().add(movie1);
        collection1.getMedia().add(movie2);
        collection2.getMedia().add(movie1);

        List<Collection> given = new ArrayList<>();
        given.add(collection1);
        given.add(collection2);

        //When
        List<Collection> result = collectionService.removeCollectionsWithMediaId(given, movie2.getId());

        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result).contains(collection2);
    }
}
