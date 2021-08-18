package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * Network DTO
 * <p>The Network DTO is used to provide reduced information to the client</p>
 * <p>A network can be either a tv channel or a studio that released a movie</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
public class NetworkDto {
    private Long id;
    private String networkTitle;
}
