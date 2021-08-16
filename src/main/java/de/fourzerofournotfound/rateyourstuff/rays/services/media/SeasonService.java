package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeasonDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * SeasonService
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeasonController SeasonController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("seasonService")
public class SeasonService {

    private final ModelMapper modelMapper;
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(ModelMapper modelMapper, SeasonRepository seasonRepository) {
        this.modelMapper = modelMapper;
        this.seasonRepository = seasonRepository;
    }

    public SeasonDto convertToDto(Season season) {
        return modelMapper.map(season, SeasonDto.class);
    }

    /**
     * Checks if a given season can be stored within the database without causing duplicates
     * @param season the season that should be checked
     * @return true if there is not a similar season in the database
     */
    public boolean isValidSeason(Season season)
    {
        Optional<Season> optionalSeason;
        if(Objects.nonNull(season.getId()))
        {
            optionalSeason = seasonRepository.findSeasonByIdNotAndSeasonNumberAndMediumId(season.getId(), season.getSeasonNumber(), season.getSeriesMappingId());
        }
        else
        {
            optionalSeason = seasonRepository.findSeasonBySeasonNumberAndMediumId(season.getSeasonNumber(), season.getSeriesMappingId());
        }
        return optionalSeason.isEmpty();
    }
}
