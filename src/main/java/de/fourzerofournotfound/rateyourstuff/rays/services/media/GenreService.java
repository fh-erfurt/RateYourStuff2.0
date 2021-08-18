package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GenreDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Genre;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * GenreService
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GenreController GenreController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("genreService")
public class GenreService {

    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(ModelMapper modelMapper,
                        GenreRepository genreRepository) {
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
    }

    /**
     * Converts the given Genre to a GenreDTO
     *
     * @param genre the Genre that should be converted
     * @return the converted GenreDTO
     */
    public GenreDto convertToDto(Genre genre) {
        return modelMapper.map(genre, GenreDto.class);
    }

    /**
     * Returns references to the given genres. Creates genres that do not exist
     *
     * @param genreStrings the list of genre names that should be searched within the database
     * @return the list of genre entities
     */
    public Set<Genre> getGenresSet(List<String> genreStrings) {
        Set<Genre> genres = new HashSet<>();

        for (String genre : genreStrings) {
            Optional<Genre> foundGenre = genreRepository.findGenreByGenreName(genre);
            if (foundGenre.isPresent()) {
                genres.add(foundGenre.get());
            } else {
                Genre newGenre = new Genre();
                newGenre.setGenreName(genre);
                genres.add(genreRepository.save(newGenre));
            }
        }
        return genres;
    }
}
