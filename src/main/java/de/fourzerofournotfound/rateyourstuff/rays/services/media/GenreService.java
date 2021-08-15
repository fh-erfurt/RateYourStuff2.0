package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GenreDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>GenreService</h1>
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GenreController GenreController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("genreService")
public class GenreService {

    private final ModelMapper modelMapper;

    @Autowired
    public GenreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts the given Genre to a GenreDTO
     * @param genre the Genre that should be converted
     * @return      the converted GenreDTO
     */
    public GenreDto convertToDto(Genre genre) {
        return modelMapper.map(genre, GenreDto.class);
    }
}
