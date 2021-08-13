package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CollectionDto {
    private Long id;
    private String title;
    private List<MediumDto> media;
    private String userUserName;
}
