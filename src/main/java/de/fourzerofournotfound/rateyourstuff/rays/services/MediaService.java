package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This Services are used to validate some media data which comes via rest api.
 */

@Service("ms")
public class MediaService {

    private final BookRepository bookRepository;
    private final GameRepository gameRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public MediaService(BookRepository bookRepository,
                        GameRepository gameRepository,
                        MovieRepository movieRepository,
                        SeriesRepository seriesRepository,
                        EpisodeRepository episodeRepository,
                        SeasonRepository seasonRepository,
                        GenreRepository genreRepository,
                        LanguageRepository languageRepository)
    {
        this.bookRepository = bookRepository;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.seriesRepository = seriesRepository;
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
    }

    /**
     * This service is used to check if a given book-object(checked by its attributes) is already stored in database
     * @param book - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this book-object is valid)
     */
    public boolean isValidBook(Book book)
    {
        Optional<Book> optionalBook;
        if(Objects.nonNull(book.getId()))
        {
            optionalBook = bookRepository.findBookByIdNotAndMediumNameIgnoreCaseAndReleaseDateOrIsbn(book.getId(), book.getMediumName(), book.getReleaseDate(), book.getIsbn());
        } else {
            optionalBook = bookRepository.findBookByMediumNameIgnoreCaseAndReleaseDateOrIsbn(book.getMediumName(), book.getReleaseDate(), book.getIsbn());
        }
        return optionalBook.isEmpty();
    }

    /**
     * This service is used to check if a given game-object(checked by its attributes) is already stored in database
     * @param game - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this game-object is valid)
     */
    public boolean isValidGame(Game game)
    {
        Optional<Game> optionalGame;
        if(Objects.nonNull(game.getId()))
        {
            optionalGame = gameRepository.findGameByIdNotAndMediumNameIgnoreCaseAndReleaseDate(game.getId(), game.getMediumName(), game.getReleaseDate());
        } else {
            optionalGame = gameRepository.findGameByMediumNameIgnoreCaseAndReleaseDate(game.getMediumName(), game.getReleaseDate());
        }
        return optionalGame.isEmpty();
    }

    /**
     * This service is used to check if a given movie-object(checked by its attributes) is already stored in database
     * @param movie - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this movie-object is valid)
     */
    public boolean isValidMovie(Movie movie)
    {
        Optional<Movie> optionalMovie;
        if(Objects.nonNull(movie.getId()))
        {
            optionalMovie = movieRepository.findMovieByIdNotAndMediumNameIgnoreCaseAndReleaseDate(movie.getId(), movie.getMediumName(), movie.getReleaseDate());
        } else {
            optionalMovie = movieRepository.findMovieByMediumNameIgnoreCaseAndReleaseDate(movie.getMediumName(), movie.getReleaseDate());
        }
        return optionalMovie.isEmpty();
    }

    /**
     * This service is used to check if a given series-object(checked by its attributes) is already stored in database
     * @param series - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this series-object is valid)
     */
    public boolean isValidSeries(Series series)
    {
        Optional<Series> optionalSeries;
        if(Objects.nonNull(series.getId()))
        {
            optionalSeries = seriesRepository.findSeriesByIdNotAndMediumNameIgnoreCaseAndReleaseDate(series.getId(), series.getMediumName(), series.getReleaseDate());
        } else {
            optionalSeries = seriesRepository.findSeriesByMediumNameIgnoreCaseAndReleaseDate(series.getMediumName(), series.getReleaseDate());
        }
        return optionalSeries.isEmpty();
    }

    /**
     * This service is used to check if a given season-object(checked by its attributes) is already stored in database
     * @param season - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this season-object is valid)
     */
    public boolean isValidSeason(Season season)
    {
        Optional<Season> optionalSeason;
        if(Objects.nonNull(season.getId()))
        {
            optionalSeason = seasonRepository.findSeasonSeasonByIdNotAndSeasonNumberAndMediumId(season.getId(), season.getSeasonNumber(), season.getSeriesMappingId());
        }
        else
        {
            optionalSeason = seasonRepository.findSeasonBySeasonNumberAndMediumId(season.getSeasonNumber(), season.getSeriesMappingId());
        }
        return optionalSeason.isEmpty();
    }

    /**
     * This service is used to check if a given episode-object(checked by its attributes) is already stored in database
     * @param episode - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this episode-object is valid)
     */
    public boolean isValidEpisode(Episode episode)
    {
        Optional<Episode> optionalEpisode;
        if(Objects.nonNull(episode.getId())&&Objects.nonNull(episode.getSeason()))
        {
            optionalEpisode = episodeRepository.findEpisodeByIdNotAndMediumNameIgnoreCaseAndReleaseDateAndSeasonIdNot(episode.getId(), episode.getMediumName(), episode.getReleaseDate(), episode.getSeason().getId());
        }
        else
        {
            optionalEpisode = episodeRepository.findEpisodeByMediumNameIgnoreCaseAndReleaseDateAndEpisodeNumber(episode.getMediumName(), episode.getReleaseDate(), episode.getEpisodeNumber());
        }
        return optionalEpisode.isEmpty();
    }

    public Set<Genre> getGenresSet(List<String> genreStrings, Medium medium) {
        Set<Genre> genres = new HashSet<>();
        for(String genre : genreStrings) {
            Optional<Genre> foundGenre = genreRepository.findGenreByGenreName(genre);
            if(foundGenre.isPresent()) {
                foundGenre.get().getMedia().add(medium);
                genres.add(foundGenre.get());
            } else {
                Genre newGenre = new Genre();
                newGenre.setGenreName(genre);
                newGenre.setMedia(new HashSet<>());
                newGenre.getMedia().add(medium);
                genres.add(genreRepository.save(newGenre));
            }
        }
        return genres;
    }

    public Set<Language> getLanguageSet(List<String> languageStrings, Medium medium) {
        Set<Language> languages = new HashSet<>();
        for(String language:  languageStrings) {
            Optional<Language> foundLanguage = languageRepository.findLanguageByLanguage(language);
            if(foundLanguage.isPresent()) {
                foundLanguage.get().getMedia().add(medium);
                languages.add(foundLanguage.get());
            } else {
                Language newLanguage = new Language();
                newLanguage.setLanguage(language);
                newLanguage.setMedia(new HashSet<>());
                newLanguage.getMedia().add(medium);
                languages.add(languageRepository.save(newLanguage));
            }
        }
        return languages;
    }

}

