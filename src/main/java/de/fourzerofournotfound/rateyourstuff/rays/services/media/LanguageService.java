package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.LanguageDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Language;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>LanguageService</h1>
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.LanguageController LanguageController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("languageService")
public class LanguageService {
    private final ModelMapper modelMapper;

    @Autowired
    public LanguageService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given Language to a LanguageDTO
     * @param language  the Language that should be converted
     * @return          the converted LanguageDTO
     */
    public LanguageDto convertToDto(Language language) {
        return modelMapper.map(language, LanguageDto.class);
    }
}
