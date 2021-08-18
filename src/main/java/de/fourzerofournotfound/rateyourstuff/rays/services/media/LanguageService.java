package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.LanguageDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Language;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/**
 * LanguageService
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.LanguageController LanguageController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("languageService")
public class LanguageService {
    private final ModelMapper modelMapper;
    private final LanguageRepository languageRepository;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public LanguageService(ModelMapper modelMapper,
                           LanguageRepository languageRepository) {
        this.modelMapper = modelMapper;
        this.languageRepository = languageRepository;
    }

    /**
     * Converts a given Language to a LanguageDTO
     *
     * @param language the Language that should be converted
     * @return the converted LanguageDTO
     */
    public LanguageDto convertToDto(Language language) {
        return modelMapper.map(language, LanguageDto.class);
    }

    /**
     * Returns references to the given languages. Creates languages that do not exist
     *
     * @param languageStrings the list of language names that should be searched within the database
     * @return the list of language entities
     */
    public Set<Language> getLanguageSet(List<String> languageStrings) {
        Set<Language> languages = new HashSet<>();

        for (String language : languageStrings) {
            Optional<Language> foundLanguage = languageRepository.findLanguageByLanguage(language);
            if (foundLanguage.isPresent()) {
                languages.add(foundLanguage.get());
            } else {
                Language newLanguage = new Language();
                newLanguage.setLanguage(language);
                Language savedLanguage = languageRepository.save(newLanguage);
                logger.info("Added " + Language.class.getSimpleName() + " with id " + savedLanguage.getId());
                languages.add(savedLanguage);
            }
        }
        return languages;
    }
}
