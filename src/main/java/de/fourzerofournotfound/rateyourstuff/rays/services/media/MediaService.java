package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This Services are used to validate some media data which comes via rest api.
 */

@Service("ms")
public class MediaService {

    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(BookRepository bookRepository,
                        GameRepository gameRepository,
                        MovieRepository movieRepository,
                        SeriesRepository seriesRepository,
                        EpisodeRepository episodeRepository,
                        SeasonRepository seasonRepository,
                        GenreRepository genreRepository,
                        LanguageRepository languageRepository, MediaRepository mediaRepository)
    {
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
        this.mediaRepository = mediaRepository;
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
                languages.add(languageRepository.save(newLanguage));
            }
        }
        return languages;
    }

}

