package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeriesDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("seriesService")
public class SeriesService {
    @Autowired
    ModelMapper modelMapper;

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