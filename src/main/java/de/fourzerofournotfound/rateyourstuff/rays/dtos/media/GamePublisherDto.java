package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Game Publisher DTO</h1>
 * <p>The Game Publisher DTO is used to provide less information to the client.</p>
 */
@Setter
@Getter
public class GamePublisherDto {
    private Long id;
    private String gamePublisherTitle;
}
