package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.collections;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>The Reduced Collection DTO is used to provide reduced information to the client.</p>
 * <p>It does not contain a list of media that is associated to it. Therefore it can be used if the client only needs a list of collections without any detail of its contents.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Setter
@Getter
public class ReducedCollectionDto {
    private Long id;
    private String title;
    private Long userId;
}