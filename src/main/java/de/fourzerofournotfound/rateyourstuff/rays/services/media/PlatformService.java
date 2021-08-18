package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.PlatformDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.PlatformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * PlatformService
 * <p>This Service provides methods for the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.PlatformController PlatformController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("platformService")
public class PlatformService {
    private final ModelMapper modelMapper;
    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformService(ModelMapper modelMapper,
                           PlatformRepository platformRepository) {
        this.modelMapper = modelMapper;
        this.platformRepository = platformRepository;
    }

    /**
     * Converts a given Platform to a PlatformDTO
     *
     * @param platform the platform that should be converted
     * @return the converted PlatformDTO
     */
    public PlatformDto convertToDto(Platform platform) {
        return modelMapper.map(platform, PlatformDto.class);
    }

    /**
     * Returns references to the given platform names. Creates the platforms, if they do not exist
     *
     * @param platformStrings the names of the platforms that should be references
     * @return the entities of the platforms
     */
    public Set<Platform> getPlatformSet(List<String> platformStrings) {
        Set<Platform> platforms = new HashSet<>();
        for (String platform : platformStrings) {
            Optional<Platform> foundPlatform = platformRepository.findByPlatformTitle(platform);
            if (foundPlatform.isPresent()) {
                platforms.add(foundPlatform.get());
            } else {
                Platform newPlatform = new Platform();
                newPlatform.setPlatformTitle(platform);
                platforms.add(platformRepository.save(newPlatform));
            }
        }
        return platforms;
    }
}
