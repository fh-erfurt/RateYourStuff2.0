package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>LanguageDTO</h1>
 * <p>The Language DTO is used to provide reduced information to the client.</p>
 */
@Getter
@Setter
public class LanguageDto {
    private Long id;
    //name of the language
    private String language;
}
