package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.NetworkDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("networkService")
public class NetworkService {
    private final ModelMapper modelMapper;

    @Autowired
    public NetworkService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NetworkDto convertToDto(Network network) {
        NetworkDto networkDto = modelMapper.map(network, NetworkDto.class);
        return networkDto;
    }
}
