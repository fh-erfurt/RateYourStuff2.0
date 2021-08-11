package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.Language;
import de.fourzerofournotfound.rateyourstuff.rays.models.Platform;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GameDto extends MediumDto{

    private Float averagePlaytime;

    private Integer minNumberOfGamers;

    private Integer maxNumberOfGamers;

    private List<String> platforms;

    private GamePublisher gamePublisher;

    private Integer ageRestriction;

    public void setPlatforms(Set<Platform> platforms) {
        if(platforms != null) {
            this.platforms = platforms.stream().map(Platform::getPlatformTitle).collect(Collectors.toList());
        } else {
            this.platforms = new ArrayList<>();
        }

    }
}
