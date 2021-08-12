package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeasonDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SeasonService")
public class SeasonService {

    private final ModelMapper modelMapper;

    @Autowired
    public SeasonService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SeasonDto convertToDto(Season season) {
        return modelMapper.map(season, SeasonDto.class);
    }
}
