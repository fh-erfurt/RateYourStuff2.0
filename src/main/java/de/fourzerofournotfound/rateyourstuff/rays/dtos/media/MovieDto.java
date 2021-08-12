package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieDto extends MediumDto{
    private Integer length;

    private Integer ageRestriction;

    private Network network;
}
