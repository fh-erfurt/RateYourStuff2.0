package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeriesDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.NetworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("seriesService")
public class SeriesService {
    private final ModelMapper modelMapper;
    private final NetworkRepository networkRepository;

    @Autowired
    public SeriesService(ModelMapper modelMapper, NetworkRepository networkRepository) {
        this.modelMapper = modelMapper;
        this.networkRepository = networkRepository;
    }

    /**
     * Converts a given movie to a movieDTO object to limit the data that gets sent to the client
     * @param series   the series that should be converted
     * @return          the corresponding dtoObject
     */
    public SeriesDto convertToDto(Series series) {
        SeriesDto seriesDto= modelMapper.map(series, SeriesDto.class);
        seriesDto.setAverageRating(series.getMediumRatings());
        seriesDto.setNumberOfRatings(series.getMediumRatings());
        seriesDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        seriesDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        seriesDto.setNumberOfComments(series.getComments());
        seriesDto.setNumberOfCollections(series.getCollections());
        seriesDto.setNumberOfSeasons(series.getSeasons().size());
        return seriesDto;
    }

    public Network getNetwork(String networkTitle, Series series) {
        Optional<Network> network = networkRepository.findByNetworkTitle(networkTitle);
        if(network.isPresent()) {
            return network.get();
        } else {
            Network newNetwork = new Network();
            newNetwork.setNetworkTitle(networkTitle);
            return networkRepository.save(newNetwork);
        }
    }
}