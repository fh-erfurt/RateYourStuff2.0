package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * This Services are used to validate some media data which comes via rest api.
 */

@Service("ms")
public class MediaService {

    private BookRepository bookRepository;
    private GameRepository gameRepository;
    private MovieRepository movieRepository;
    private SeriesRepository seriesRepository;
    private EpisodeRepository episodeRepository;
    private SeasonRepository seasonRepository;

    @Autowired
    public void MediaService(BookRepository bookRepository, GameRepository gameRepository, MovieRepository movieRepository,
                             SeriesRepository seriesRepository, EpisodeRepository episodeRepository, SeasonRepository seasonRepository)
    {
        this.bookRepository = bookRepository;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.seriesRepository = seriesRepository;
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;

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
            optionalBook = bookRepository.findBookByIdNotAndMediumNameIgnoreCaseAndReleaseDate(book.getId(), book.getMediumName(), book.getReleaseDate());
        } else {
            optionalBook = bookRepository.findBookByMediumNameIgnoreCaseAndReleaseDate(book.getMediumName(), book.getReleaseDate());
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
            optionalSeason = seasonRepository.findSeasonByIdNotAndSeasonTitleIgnoreCaseAndSeasonNumber(season.getId(), season.getSeasonTitle(), season.getSeasonNumber());
        }
        else
        {
            optionalSeason = seasonRepository.findSeasonBySeasonTitleIgnoreCaseAndSeasonNumber(season.getSeasonTitle(), season.getSeasonNumber());
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

}

