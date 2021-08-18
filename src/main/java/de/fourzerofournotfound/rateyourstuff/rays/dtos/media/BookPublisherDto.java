package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * Book Publisher DTO
 * <p>The Book Publisher DTO is used to provide only the name and the id of a publisher to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
public class BookPublisherDto {
    private Long id;
    private String bookPublisherTitle;
}
