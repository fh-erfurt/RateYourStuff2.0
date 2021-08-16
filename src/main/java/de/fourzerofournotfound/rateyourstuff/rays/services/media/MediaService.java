package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.*;
import net.minidev.json.JSONUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.MediaList;

import java.util.*;

/**
 * MediaService
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
    private final MediaRepository mediaRepository;
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
        this.mediaRepository = mediaRepository;
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

    /**
     * Returns a list containing all media that match the input by ignoring the Case and except also almost matching words(like)
     * @param givenInput taken from the SearchBar call altered through dividing and sorting out short words followed by adjusting to the "likefuntion" aspect with '%...%'
     * @return              an ArrayList of matching Media
     */
    public ArrayList<Medium> getSearchResult(String givenInput){
        ArrayList<String> separatedInput = new ArrayList<>();
        Collections.addAll(separatedInput,givenInput.split(" "));
        int minLengthForValidWord = 4;
        separatedInput.removeIf(currentString -> currentString.length() < minLengthForValidWord);
        ArrayList<String> alteredInputList = new ArrayList<>();

        for(String s: separatedInput)
        {
            String forLikeliness = "%";
            String alteredInput = forLikeliness+s+forLikeliness;
            alteredInputList.add(alteredInput);
        }

        HashSet<Medium> allMediaMatches = new HashSet<>();
        for(String a: alteredInputList)
        {
            List<Medium> results = mediaRepository.findByMediumNameLikeIgnoreCase(a);
            for(Medium match: results)
            {
                boolean mediumIsPresent = allMediaMatches.stream().map(Medium::getId).anyMatch(match.getId()::equals);
                if(!mediumIsPresent) {
                    allMediaMatches.add(match);
                }
            }
        }
        return new ArrayList<>(allMediaMatches);
    }

}

