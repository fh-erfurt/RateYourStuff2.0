package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>The Series DTO is used to provide reduced information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
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
