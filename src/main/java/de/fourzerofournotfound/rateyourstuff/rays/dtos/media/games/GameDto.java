package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.Platform;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>The GameDTO is used to provide less information to the client</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
public class GameDto extends MediumDto {
    //average time that is needed to finish the game (in hours)
    private Float averagePlaytime;
    //minimum number of players that are needed in order to play the game
    private Integer minNumberOfGamers;
    //maximum number of players that can play the game together
    private Integer maxNumberOfGamers;
    //all platform names the game is available for
    private List<String> platforms;
    //name of the publisher
    private String gamePublisherGamePublisherTitle;

    private Integer ageRestriction;

    public void setPlatforms(Set<Platform> platforms) {
        if (platforms != null) {
            this.platforms = platforms.stream().map(Platform::getPlatformTitle).collect(Collectors.toList());
        } else {
            this.platforms = new ArrayList<>();
        }
    }
}
