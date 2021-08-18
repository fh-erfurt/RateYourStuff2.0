package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.games;

import lombok.Getter;
import lombok.Setter;

/**
 * Game Publisher DTO
 * <p>The Game Publisher DTO is used to provide less information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Setter
@Getter
public class GamePublisherDto {
    private Long id;
    private String gamePublisherTitle;
}
