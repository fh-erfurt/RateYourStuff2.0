package de.fourzerofournotfound.rateyourstuff.rays.services.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.series.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series.EpisodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>This Service is used to provide methods for the {@link EpisodeController EpisodeController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
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
     *
     * @param episode the episode that should be converted
     * @return the corresponding dtoObject
     */
    public EpisodeDto convertToDto(Episode episode) {
        EpisodeDto episodeDto = modelMapper.map(episode, EpisodeDto.class);
        episodeDto.setAverageRating(episode.getMediumRatings());
        episodeDto.setNumberOfRatings(episode.getMediumRatings());
        episodeDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        episodeDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        episodeDto.setNumberOfComments(episode.getComments());
        episodeDto.setNumberOfCollections(episode.getCollections());
        episodeDto.setSeriesId(episode.getSeason());
        episodeDto.setSeriesTitle(episode.getSeason());
        return episodeDto;
    }

    /**
     * Checks if a episode can be stored within the database without causing duplicates
     *
     * @param episode the episode that should be checked
     * @return true if the episode is not already stored in the database
     */
    public boolean isValidEpisode(Episode episode) {
        Optional<Episode> optionalEpisode;
        if (Objects.nonNull(episode.getId()) && Objects.nonNull(episode.getSeasonMappingId())) {
            optionalEpisode = episodeRepository.findEpisodeByIdNotAndEpisodeNumberAndSeasonId(episode.getId(), episode.getEpisodeNumber(), episode.getSeasonMappingId());
        } else {
            optionalEpisode = episodeRepository.findEpisodeByEpisodeNumberAndSeasonId(episode.getEpisodeNumber(), episode.getSeasonMappingId());
        }
        return optionalEpisode.isEmpty();
    }
}
