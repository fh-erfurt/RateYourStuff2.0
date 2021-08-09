package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GenreDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("genreService")
public class GenreService {

    private final ModelMapper modelMapper;

    @Autowired
    public GenreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GenreDto convertToDto(Genre genre) {
        GenreDto genreDto = modelMapper.map(genre, GenreDto.class);
        return genreDto;
    }
}
