package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.SeriesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active = test")
class MediaServiceTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SeriesRepository seriesRepository;

    @Autowired
    MediaService mediaService;

    @BeforeEach
    void beforeEach()
    {
        bookRepository.deleteAll();
        gameRepository.deleteAll();
        movieRepository.deleteAll();
    }


    @Test
    void shouldFindDuplicatesOfGivenBook()
    {
        //Given
        LocalDate release = LocalDate.of(2005,02,07);

        Book testMedia = Book.builder()
                .mediumName("Halo - The fall of reach")
                .releaseDate(release)
                .shortDescription("The official prequel to the award-winning Xbox(TM) game, one of the bestselling computer games of recent years.")
                .isEBook(false)
                .isPrint(true)
                .isbn("9781841494203")
                .numberOfPages(352)
                .build();

        //When
        Book saved = bookRepository.save(testMedia);
        mediaService.isValidBook(saved);


        //Then
        /**
         * @Test shows that isValidBook finds a duplicate of the given book already in saved database
         */
        Assertions.assertTrue(mediaService.isValidBook(testMedia));
        /**
         * @Test shows that isValidBook is detecting duplicates of a book which is already saved in database
         */
        Assertions.assertTrue(mediaService.isValidBook(saved));
    }

    @Test
    void shouldDetectDuplicationsOfGames()
    {
        // Given
        LocalDate releaseDate = LocalDate.of(2001, 11, 15);
        Game testGame = Game.builder()
                .mediumName("Halo - Combat Evolved")
                .releaseDate(releaseDate)
                .shortDescription("Halo: Combat Evolved, also known as Halo: CE, is a first-person shooter game developed by Bungie and published by Microsoft Game Studios.")
                .ageRestriction(16)
                .maxNumberOfGamers(16)
                .minNumberOfGamers(1)
                .build();

        // When
        Game result = gameRepository.save(testGame);

        // Then
        Assertions.assertTrue(mediaService.isValidGame(testGame));

        Assertions.assertTrue(mediaService.isValidGame(result));
    }

    //TODO: TestCase for isValidMovie -> shouldFindDuplicationOfGivenMovie
    @Test
    void shouldDetectDuplicationOfGivenMovie()
    {
        // Given
        LocalDate releaseDate = LocalDate.of(2009, 12, 17);
        Movie testMovie = Movie.builder()
                .mediumName("Avatar - Aufbruch nach Pandora")
                .releaseDate(releaseDate)
                .shortDescription("Avatar – Aufbruch nach Pandora (Originaltitel: Avatar, auch bekannt als James Cameron’s Avatar) ist ein US-amerikanischer Science-Fiction-Film des Regisseurs James Cameron, der weltweit am 17. und 18. Dezember 2009 startete.")
                .ageRestriction(12)
                .length(162)
                .build();

        // When
        Movie result = movieRepository.save(testMovie);

        // Then
        Assertions.assertTrue(mediaService.isValidMovie(testMovie));

        Assertions.assertTrue(mediaService.isValidMovie(result));
    }

    //TODO: TestCase for isValidSeries -> shouldFindDuplicationOfGivenSeries

}