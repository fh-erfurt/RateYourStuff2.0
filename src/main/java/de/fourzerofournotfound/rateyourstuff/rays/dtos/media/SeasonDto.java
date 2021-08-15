package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Season DTO</h1>
 * <p>The Season DTO is used to provide reduced data to the client.</p>
 */
@Setter
@Getter
public class SeasonDto {
    private Long id;
    private Long mediumId;
    private String seasonTitle;
    private Integer seasonNumber;
}
