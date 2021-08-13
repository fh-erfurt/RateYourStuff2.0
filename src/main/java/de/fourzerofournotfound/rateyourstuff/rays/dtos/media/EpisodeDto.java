package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class EpisodeDto extends MediumDto{

    private Integer episodeNumber;

    private Integer length;

    private Long seasonId;

    private String seasonTitle;

    private Long seriesId;

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
