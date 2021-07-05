package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class EpisodeDto extends MediumDto{

    private Integer episodeNumber;

    private Integer length;

    private Long seasonId;

    private String seasonTitle;

    private Long seriesId;

    public void setSeriesId(Season season) {
        if(season != null) {
            this.seriesId = season.getMedium().getId();
        }
    }
}
