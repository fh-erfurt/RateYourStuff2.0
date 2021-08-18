package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.NetworkDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.NetworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * NetworkService
 * <p>This service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.NetworkController NetworkController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("networkService")
public class NetworkService {
    private final ModelMapper modelMapper;
    private final NetworkRepository networkRepository;

    @Autowired
    public NetworkService(ModelMapper modelMapper, NetworkRepository networkRepository) {
        this.modelMapper = modelMapper;
        this.networkRepository = networkRepository;
    }

    /**
     * Converts a given Network to a NetworkDTO
     *
     * @param network the Network that should be converted
     * @return the converted NetworkDTO
     */
    public NetworkDto convertToDto(Network network) {
        return modelMapper.map(network, NetworkDto.class);
    }

    /**
     * Returns a reference to the given network by its title. Creates the network, if it does not exist.
     *
     * @param networkTitle the title that should be searched within the database
     * @return the entity of the network
     */
    public Network getNetwork(String networkTitle) {
        Optional<Network> network = networkRepository.findByNetworkTitle(networkTitle);
        if (network.isPresent()) {
            return network.get();
        } else {
            Network newNetwork = new Network();
            newNetwork.setNetworkTitle(networkTitle);
            return networkRepository.save(newNetwork);
        }
    }
}
