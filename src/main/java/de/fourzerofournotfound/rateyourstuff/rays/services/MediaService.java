package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
                             SeriesRepository seriesRepository, EpisodeRepository episodeRepository)
    {
        this.bookRepository = bookRepository;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.seriesRepository = seriesRepository;
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;

    }

    public boolean isValidBook(Book book)
    {
        Optional<Book> optionalBook;
        if(Objects.nonNull(book.getId()))
        {
            optionalBook = bookRepository.findBookByIdNotAndMediumNameIgnoreCaseAndReleaseDate(book.getId(), book.getMediumName(), book.getReleaseDate());
        } else {
            optionalBook = bookRepository.findBookByMediumNameIgnoreCaseAndReleaseDate(book.getMediumName(), book.getReleaseDate());
        }
        //TODO: Shouldn´t be values inside if the repository detects a match?
        return optionalBook.isEmpty();
    }

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

    public boolean isValidEpisode(Episode episode)
    {
        Optional<Episode> optionalEpisode;
        if(Objects.nonNull(episode.getId()))
        {
            //TODO: How can i get a permission for seasonID?
            optionalEpisode = episodeRepository.findEpisodeByIdNotAndMediumNameIgnoreCaseAndReleaseDateAndSeasonIdNot(episode.getId(), episode.getMediumName(), episode.getReleaseDate(), episode.getSeason().getId());
        }
        else
        {
            optionalEpisode = episodeRepository.findEpisodeByMediumNameIgnoreCaseAndReleaseDateAndSeasonIdNot( episode.getMediumName(), episode.getReleaseDate(), episode.getSeason().getId());
        }
        return optionalEpisode.isEmpty();
    }

}

