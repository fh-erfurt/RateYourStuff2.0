package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Movie DTO</h1>
 * <p>The Movie DTO is used to provide reduced information to the client.</p>
 */
@Setter
@Getter
public class MovieDto extends MediumDto{
    //length of the movie in full minutes
    private Integer length;

    private Integer ageRestriction;

    //name of the studio which made the movie
    private String networkNetworkTitle;
}
