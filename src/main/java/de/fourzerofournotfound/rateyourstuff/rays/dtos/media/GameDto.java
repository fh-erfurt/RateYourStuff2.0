package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.Platform;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GameDto extends MediumDto{

    private Float averagePlaytime;

    private Integer minNumberOfGamers;

    private Integer maxNumberOfGamers;

    private Set<Platform> platforms;

    private GamePublisher gamePublisher;

    private Integer ageRestriction;
}
