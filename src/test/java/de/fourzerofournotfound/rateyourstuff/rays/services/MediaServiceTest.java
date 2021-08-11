package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
    SeasonRepository seasonRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    MediaService mediaService;

    @BeforeEach
    void beforeEach()
    {
        bookRepository.deleteAll();
        gameRepository.deleteAll();
        episodeRepository.deleteAll();
        seasonRepository.deleteAll();
        seriesRepository.deleteAll();
        movieRepository.deleteAll();
    }


    @Test
    void shouldFindDuplicatesOfGivenBook()
    {
        //Given
        LocalDate release = LocalDate.of(2005,2,7);

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

        //false if media is already existent
        Assertions.assertFalse(mediaService.isValidBook(testMedia));
        //false if media is already existent
        Assertions.assertFalse(mediaService.isValidBook(saved));
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

    @Test
    void shouldDetectDuplicationOfGivenSeries()
    {
        //Given
        LocalDate releaseDate0 = LocalDate.of(2017, 9, 24);

        Series givenSeries = Series.builder()
                .mediumName("Star Trek: Discovery")
                .ageRestriction(0)
                .shortDescription("Star Trek: Discovery ist eine US-amerikanische Science-Fiction-Fernsehserie und die sechste Realfilm-Fernsehserie, die im fiktiven Star-Trek-Universum spielt. In der auch mit DSC abgekürzten Serie geht es um das titelgebende Sternenflottenraumschiff Discovery. ")
                .averageLength(51)
                .releaseDate(releaseDate0)
                .isCompleted(false)
                .build();

        Series givenSeries2 = Series.builder()
                .mediumName("Star Trek: Discovery")
                .ageRestriction(0)
                .shortDescription("StarTrek: Discovery ist eine US-amerikanische Science-Fiction-Fernsehserie und die sechste Realfilm-Fernsehserie, die im fiktiven Star-Trek-Universum spielt. In der auch mit DSC abgekürzten Serie geht es um das titelgebende Sternenflottenraumschiff Discovery. ")
                .averageLength(51)
                .releaseDate(releaseDate0)
                .isCompleted(false)
                .build();

        Series givenSeriesNotToStore = Series.builder()
                .mediumName("How i met your Mother")
                .releaseDate(LocalDate.of(2005,9,19))
                .shortDescription("[...]")
                .ageRestriction(0)
                .isCompleted(true)
                .build();

        Episode givenEpisodeOne = Episode.builder()
                .mediumName("Leuchtfeuer")
                .episodeNumber(1)
                .shortDescription("[...]")
                .releaseDate(releaseDate0)
                .length(51)
                .build();

        Season starTrekDiscoverySeasonOne = Season.builder()
                .seasonNumber(1)
                .seasonTitle("Season 1")
                .build();


        givenEpisodeOne.setSeason(starTrekDiscoverySeasonOne);
        starTrekDiscoverySeasonOne.getEpisodes().add(givenEpisodeOne);
        givenSeries.getSeasons().add(starTrekDiscoverySeasonOne);


        // When
        Series result = seriesRepository.save(givenSeries);


        //Then
        Assertions.assertTrue(mediaService.isValidSeries(givenSeries));

        Assertions.assertTrue(mediaService.isValidSeries(result));

        Assertions.assertTrue(mediaService.isValidSeries(givenSeriesNotToStore));
        //TODO: Outsource to an unique test
        Assertions.assertFalse(mediaService.isValidSeries(givenSeries2));
    }

    @Test
    void shouldDetectDuplicationOfGivenSeason()
    {
        // Given
        LocalDate releaseDate0 = LocalDate.of(2017, 9, 26);

        Episode givenEpisodeOne = Episode.builder()
                .mediumName("Leuchtfeuer")
                .episodeNumber(1)
                .shortDescription("[...]")
                .releaseDate(releaseDate0)
                .length(51)
                .build();

        Season givenSeason = Season.builder()
                .seasonTitle("Season")
                .seasonNumber(1)
                .build();

        givenSeason.getEpisodes().add(givenEpisodeOne);

        // When
        Season result = seasonRepository.save(givenSeason);

        // Then
        Assertions.assertTrue(mediaService.isValidSeason(givenSeason));

        Assertions.assertTrue(mediaService.isValidSeason(result));
    }

    @Test
    void shouldDetectDuplicationOfGivenEpisode()
    {
        //Given
        LocalDate releaseDate = LocalDate.of(2017, 9, 26);

        Series givenSeries = Series.builder()
                .mediumName("Star Trek: Discovery")
                .ageRestriction(0)
                .shortDescription("Star Trek: Discovery ist eine US-amerikanische Science-Fiction-Fernsehserie und die sechste Realfilm-Fernsehserie, die im fiktiven Star-Trek-Universum spielt. In der auch mit DSC abgekürzten Serie geht es um das titelgebende Sternenflottenraumschiff Discovery. ")
                .averageLength(51)
                .releaseDate(releaseDate)
                .isCompleted(false)
                .build();

        Episode givenEpisode = Episode.builder()
                .mediumName("Leuchtfeuer")
                .episodeNumber(1)
                .shortDescription("[...]")
                .releaseDate(releaseDate)
                .length(51)
                .build();

        Season givenSeason = Season.builder()
                .seasonTitle("Season")
                .seasonNumber(1)
                .build();

        givenEpisode.setSeason(givenSeason);
        givenSeason.getEpisodes().add(givenEpisode);
        givenSeries.getSeasons().add(givenSeason);


        //When
        seriesRepository.save(givenSeries);

        //Then
        Assertions.assertTrue(mediaService.isValidEpisode(givenEpisode));
    }

}