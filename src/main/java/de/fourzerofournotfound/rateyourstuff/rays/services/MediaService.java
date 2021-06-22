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

    @Autowired
    public void MediaService(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.seriesRepository = seriesRepository;
        this.episodeRepository = episodeRepository;

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
        return optionalGame.isPresent();
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
        return optionalMovie.isPresent();
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
        return optionalSeries.isPresent();
    }

    //TODO: Function isValidSeason
    //TODO: Function isValidEpisode
}

