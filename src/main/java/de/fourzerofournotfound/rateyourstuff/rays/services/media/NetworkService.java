package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.NetworkDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * NetworkService
 * <p>This service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.NetworkController NetworkController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("networkService")
public class NetworkService {
    private final ModelMapper modelMapper;

    @Autowired
    public NetworkService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given Network to a NetworkDTO
     * @param network   the Network that should be converted
     * @return          the converted NetworkDTO
     */
    public NetworkDto convertToDto(Network network) {
        return modelMapper.map(network, NetworkDto.class);
    }
}
