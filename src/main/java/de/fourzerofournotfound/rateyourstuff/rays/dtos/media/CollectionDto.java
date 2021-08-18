package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Collection DTO
 * <p>The Collection DTO is used to provide less information to the client. It also contains a list of all media within the collection.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Setter
@Getter
public class CollectionDto {
    private Long id;
    private String title;
    //list of all media that is included in the collection
    private List<MediumDto> media;
    //name of the user which created the collection
    private String userUserName;
    private Long userId;
}
