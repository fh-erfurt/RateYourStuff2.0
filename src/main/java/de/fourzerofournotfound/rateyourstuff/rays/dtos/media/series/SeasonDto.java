package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.series;

import lombok.Getter;
import lombok.Setter;

/**
 * Season DTO
 * <p>The Season DTO is used to provide reduced data to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Setter
@Getter
public class SeasonDto {
    private Long id;
    private Long mediumId;
    private String seasonTitle;
    private Integer seasonNumber;
}
