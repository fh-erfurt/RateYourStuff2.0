package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeasonDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service("SeasonService")
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
     * This service is used to check if a given season-object(checked by its attributes) is already stored in database
     * @param season - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this season-object is valid)
     */
    public boolean isValidSeason(Season season)
    {
        Optional<Season> optionalSeason;
        if(Objects.nonNull(season.getId()))
        {
            optionalSeason = seasonRepository.findSeasonSeasonByIdNotAndSeasonNumberAndMediumId(season.getId(), season.getSeasonNumber(), season.getSeriesMappingId());
        }
        else
        {
            optionalSeason = seasonRepository.findSeasonBySeasonNumberAndMediumId(season.getSeasonNumber(), season.getSeriesMappingId());
        }
        return optionalSeason.isEmpty();
    }
}
