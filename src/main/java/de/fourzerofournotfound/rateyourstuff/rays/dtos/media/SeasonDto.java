package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeasonDto {
    private Long id;
    private Long mediumId;
    private String seasonTitle;
    private Integer seasonNumber;
}
