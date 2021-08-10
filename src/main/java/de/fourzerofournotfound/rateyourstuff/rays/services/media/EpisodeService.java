package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("episodeService")
public class EpisodeService {
    private final ModelMapper modelMapper;

    @Autowired
    public EpisodeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given episode to a episodeDTO object to limit the data that gets sent to the client
     * @param episode   the episode that should be converted
     * @return          the corresponding dtoObject
     */
    public EpisodeDto convertToDto(Episode episode) {
        EpisodeDto episodeDto= modelMapper.map(episode, EpisodeDto.class);
        episodeDto.setAverageRating(episode.getMediumRatings());
        episodeDto.setNumberOfRatings(episode.getMediumRatings());
        episodeDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        episodeDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        episodeDto.setNumberOfComments(episode.getComments());
        episodeDto.setNumberOfCollections(episode.getCollections());
        episodeDto.setSeriesId(episode.getSeason());
        return episodeDto;
    }
}