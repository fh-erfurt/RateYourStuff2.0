package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.movies;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import lombok.Getter;
import lombok.Setter;

/**
 * Movie DTO
 * <p>The Movie DTO is used to provide reduced information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Setter
@Getter
public class MovieDto extends MediumDto {
    //length of the movie in full minutes
    private Integer length;

    private Integer ageRestriction;

    //name of the studio which made the movie
    private String networkNetworkTitle;
}
