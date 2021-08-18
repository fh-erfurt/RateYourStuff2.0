package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Season;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Series DTO
 * <p>The Series DTO is used to provide less information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter

public class EpisodeDto extends MediumDto {

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
        if (Objects.nonNull(season) && Objects.nonNull(season.getMedium())) {
            this.seriesId = season.getMedium().getId();
        }
    }

    public void setSeriesTitle(Season season) {
        if (Objects.nonNull(season) && Objects.nonNull(season.getMedium())) {
            this.seriesTitle = season.getMedium().getMediumName();
        }
    }

}
