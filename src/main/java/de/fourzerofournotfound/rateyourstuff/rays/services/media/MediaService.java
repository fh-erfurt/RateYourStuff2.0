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
 * This Services are used to validate some media data which comes via rest api.
 */

@Service("ms")
public class MediaService {

    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;
    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MediaService(BookRepository bookRepository,
                        GameRepository gameRepository,
                        MovieRepository movieRepository,
                        SeriesRepository seriesRepository,
                        EpisodeRepository episodeRepository,
                        SeasonRepository seasonRepository,
                        GenreRepository genreRepository,
                        LanguageRepository languageRepository, MediaRepository mediaRepository, ModelMapper modelMapper)
    {
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
        this.mediaRepository = mediaRepository;
        this.modelMapper = modelMapper;
    }

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

    public ArrayList<Medium> getSearchResult(String givenInput){
        ArrayList<String> separatedInput = new ArrayList<String>();
        Collections.addAll(separatedInput,givenInput.split(" "));
        int minLengthForValidWord = 4;
        Iterator<String> iterator = separatedInput.iterator();
        while (iterator.hasNext()) {
            String currentString = iterator.next();
            if(currentString.length() < minLengthForValidWord)
            {
                iterator.remove();
            }
        }
        ArrayList<String> alteredInputList = new ArrayList<String>();

        for(String s: separatedInput)
        {
            //System.out.println("test: " + s);
            String forLikeliness = "%";
            String alteredInput = forLikeliness+s+forLikeliness;
            //System.out.println("test: " + alteredInput);
            alteredInputList.add(alteredInput);
        }

        HashSet<Medium> allMediaMatches = new HashSet<>();
        //HashSet mediaListMatchingSearchParams = new HashSet();
        for(String a: alteredInputList)
        {
            List<Medium> results = mediaRepository.findByMediumNameLikeIgnoreCase(a);
            System.out.println(results.size());
            for(Medium match: results)
            {
                boolean mediumIsPresent = allMediaMatches.stream().map(Medium::getId).filter(match.getId()::equals).findFirst().isPresent();
                if(!mediumIsPresent) {
                    allMediaMatches.add(match);
                    //mediaListMatchingSearchParams.add(match);
                }
            }
        }
        ArrayList<Medium> mediaListMatchingSearch = new ArrayList<Medium>(allMediaMatches);
        System.out.println(mediaListMatchingSearch.size());
        return mediaListMatchingSearch;
        //return mediaListMatchingSearchParams;
    }

}

