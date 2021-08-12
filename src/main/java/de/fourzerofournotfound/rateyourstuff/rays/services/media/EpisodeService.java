package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.EpisodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service("episodeService")
public class EpisodeService {
    private final ModelMapper modelMapper;
    private final EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeService(ModelMapper modelMapper, EpisodeRepository episodeRepository) {
        this.modelMapper = modelMapper;
        this.episodeRepository = episodeRepository;
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

    /**
     * This service is used to check if a given episode-object(checked by its attributes) is already stored in database
     * @param episode - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this episode-object is valid)
     */
    public boolean isValidEpisode(Episode episode) {
        Optional<Episode> optionalEpisode;
        if(Objects.nonNull(episode.getId()) && Objects.nonNull(episode.getSeasonMappingId())) {
            optionalEpisode = episodeRepository.findEpisodeByIdNotAndEpisodeNumberAndSeasonId(episode.getId(), episode.getEpisodeNumber(), episode.getSeasonMappingId());
        } else {
            optionalEpisode = episodeRepository.findEpisodeByEpisodeNumberAndSeasonId(episode.getEpisodeNumber(), episode.getSeasonMappingId());
        }
        return optionalEpisode.isEmpty();
    }
}
