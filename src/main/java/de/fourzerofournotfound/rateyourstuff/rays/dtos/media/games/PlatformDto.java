package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.games;

import lombok.Getter;
import lombok.Setter;

/**
 * Platform DTO
 * <p>The Platform DTO is used to provide reduced information to the client.</p>
 * <p>A platform describes a device on which games can be played of.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
public class PlatformDto {
    private Long id;
    private String platformTitle;
}
