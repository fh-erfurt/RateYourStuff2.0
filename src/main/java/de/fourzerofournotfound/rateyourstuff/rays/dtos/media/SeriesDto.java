package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Series DTO</h1>
 * <p>The Series DTO is used to provide reduced information to the client.</p>
 */
@Getter
@Setter
public class SeriesDto extends MediumDto {
    private Integer averageLength;

    private String networkNetworkTitle;

    private Integer ageRestriction;

    private Boolean isCompleted;

    private int numberOfSeasons;
}
