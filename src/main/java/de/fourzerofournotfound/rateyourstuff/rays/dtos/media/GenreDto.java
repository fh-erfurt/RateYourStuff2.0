package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Genre DTO</h1>
 * <p>The Genre DTO is used to provide reduced information to the client.</p>
 */
@Getter
@Setter
public class GenreDto {
    private Long id;
    private String genreName;
}
