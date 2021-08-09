package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.LanguageDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Language;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("languageService")
public class LanguageService {
    private final ModelMapper modelMapper;

    @Autowired
    public LanguageService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LanguageDto convertToDto(Language language) {
        LanguageDto languageDto = modelMapper.map(language, LanguageDto.class);
        return languageDto;
    }
}
