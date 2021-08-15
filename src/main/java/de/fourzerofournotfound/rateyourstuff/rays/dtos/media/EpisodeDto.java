package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <h1>Series DTO</h1>
 * <p>The Series DTO is used to provide less information to the client.</p>
 */
@Getter
@Setter

public class EpisodeDto extends MediumDto{

    private Integer episodeNumber;

    //length of the episode in full minutes
    private Integer length;

    private Long seasonId;
    //name of the season the episode belongs to
    private String seasonTitle;

    private Long seriesId;
    //title of the series the episode belongs to
    private String seriesTitle;

    public void setSeriesId(Season season) {
        if(Objects.nonNull(season) && Objects.nonNull(season.getMedium())) {
            this.seriesId = season.getMedium().getId();
        }
    }

    public void setSeriesTitle(Season season) {
        if(Objects.nonNull(season) && Objects.nonNull(season.getMedium())) {
            this.seriesTitle = season.getMedium().getMediumName();
        }
    }

}
