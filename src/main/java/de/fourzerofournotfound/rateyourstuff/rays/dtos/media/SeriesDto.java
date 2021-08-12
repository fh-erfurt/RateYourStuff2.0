package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesDto extends MediumDto {
    private Integer averageLength;

    private Network network;

    private Integer ageRestriction;

    private Boolean isCompleted;

    private int numberOfSeasons;
}
