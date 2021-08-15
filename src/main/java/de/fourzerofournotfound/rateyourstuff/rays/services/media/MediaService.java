package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <h1>MediaService</h1>
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MediumController MediumController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("mediaService")
public class MediaService {

    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MediaService(GenreRepository genreRepository,
                        LanguageRepository languageRepository,
                        MediaRepository mediaRepository,
                        ModelMapper modelMapper)
    {
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given Medium to a MediumDTO. It also adds various needed information such the average rating of the medium
     * @param medium    the Medium that should be converted
     * @return          the converted MediumDTO
     */
    public MediumDto convertToDto(Medium medium) {
        MediumDto mediumDto = modelMapper.map(medium, MediumDto.class);
        mediumDto.setMediaType(medium.getClass().getSimpleName().toLowerCase());
        mediumDto.setAverageRating(medium.getMediumRatings());
        mediumDto.setNumberOfRatings(medium.getMediumRatings());
        mediumDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        mediumDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        mediumDto.setNumberOfComments(medium.getComments());
        mediumDto.setNumberOfCollections(medium.getCollections());
        return mediumDto;
    }

    /**
     * Returns references to the given genres. Creates genres that do not exist
     * @param genreStrings  the list of genre names that should be searched within the database
     * @return              the list of genre entities
     */
    public Set<Genre> getGenresSet(List<String> genreStrings) {
        Set<Genre> genres = new HashSet<>();

        for(String genre : genreStrings) {
            Optional<Genre> foundGenre = genreRepository.findGenreByGenreName(genre);
            if(foundGenre.isPresent()) {
                genres.add(foundGenre.get());
            } else {
                Genre newGenre = new Genre();
                newGenre.setGenreName(genre);
                genres.add(genreRepository.save(newGenre));
            }
        }
        return genres;
    }

    /**
     * Returns references to the given languages. Creates languages that do not exist
     * @param languageStrings  the list of language names that should be searched within the database
     * @return              the list of language entities
     */
    public Set<Language> getLanguageSet(List<String> languageStrings) {
        Set<Language> languages = new HashSet<>();

        for(String language:  languageStrings) {
            Optional<Language> foundLanguage = languageRepository.findLanguageByLanguage(language);
            if(foundLanguage.isPresent()) {
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

