package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.PlatformDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>PlatformService</h1>
 * <p>This Service provides methods for the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.PlatformController PlatformController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("platformService")
public class PlatformService {
    private final ModelMapper modelMapper;

    @Autowired
    public PlatformService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given Platform to a PlatformDTO
     * @param platform  the platform that should be converted
     * @return          the converted PlatformDTO
     */
    public PlatformDto convertToDto(Platform platform) {
        return modelMapper.map(platform, PlatformDto.class);
    }
}
