package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeriesDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.NetworkRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * SeriesService
 * <p>This service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeriesController SeriesController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("seriesService")
public class SeriesService {
    private final ModelMapper modelMapper;
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(ModelMapper modelMapper,
                         SeriesRepository seriesRepository) {
        this.modelMapper = modelMapper;
        this.seriesRepository = seriesRepository;
    }

    /**
     * Checks if a series can be stored within the database without causing duplicates
     * @param series the series that should be checked
     * @return true if the series is not already stored in the database
     */
    public boolean isValidSeries(Series series)
    {
        Optional<Series> optionalSeries;
        if(Objects.nonNull(series.getId()))
        {
            optionalSeries = seriesRepository.findSeriesByIdNotAndMediumNameIgnoreCaseAndReleaseDate(series.getId(), series.getMediumName(), series.getReleaseDate());
        } else {
            optionalSeries = seriesRepository.findSeriesByMediumNameIgnoreCaseAndReleaseDate(series.getMediumName(), series.getReleaseDate());
        }
        return optionalSeries.isEmpty();
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
}