package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.PlatformDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("platformService")
public class PlatformService {
    private final ModelMapper modelMapper;

    public PlatformService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PlatformDto convertToDto(Platform platform) {
        return modelMapper.map(platform, PlatformDto.class);
    }
}
