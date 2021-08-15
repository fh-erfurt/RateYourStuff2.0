package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeasonDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.SeriesNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeriesRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>Season Controller</h1>
 * <p>This Controller provides basic REST Interfaces to interact with Season entities from the database</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/seasons")
public class SeasonController {

    private final SeasonRepository seasonRepository;
    private final MediaService mediaService;
    private final SeriesRepository seriesRepository;
    private final PageableService pageableService;
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonRepository seasonRepository,
                            MediaService mediaService,
                            SeriesRepository seriesRepository, PageableService pageableService, SeasonService seasonService) {
        this.seasonRepository = seasonRepository;
        this.mediaService = mediaService;
        this.seriesRepository = seriesRepository;
        this.pageableService = pageableService;
        this.seasonService = seasonService;
    }

    /**
     * This method is used to get a single season by its id
     * @param id    the id of the season
     * @return      the found seasonDTO
     * @throws SeasonNotFoundException if there is no season with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<SeasonDto> getById (@PathVariable Long id) throws SeasonNotFoundException {
        Optional<Season> season = this.seasonRepository.findById(id);
        if(season.isPresent()) {
            return ResponseEntity.ok(this.seasonService.convertToDto(season.get()));
        }
        throw new SeasonNotFoundException("No Season found for id " + id);
    }

    /**
     * This Method returns all seasons that belong to a series from the database
     * @param page      the current page (optional)
     * @param size      the number of items per page
     * @param orderBy   the attributed that should be ordered
     * @param order     the order (asc, desc)
     * @return          a list of SeasonDTOs
     */
    @GetMapping("/series/{id}")
    ResponseEntity<List<SeasonDto>> getAll(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "seasonNumber") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Season> seasons = this.seasonRepository.findAllByMediumId(id, pageable).getContent();

        return ResponseEntity.ok(
                seasons.stream()
                        .map(seasonService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This method is used to add a new season to a series
     * @param season    the season that should be added
     * @return          the newly added season
     * @throws SeriesNotFoundException  if there is no series with the given id
     * @throws DuplicateMediumException if there is already the same season in the database
     */
    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Season> add(@RequestBody Season season) throws SeriesNotFoundException, DuplicateMediumException {
        if(seasonService.isValidSeason(season)) {
            Optional<Series> targetSeries = seriesRepository.findById(season.getSeriesMappingId());
            if(targetSeries.isPresent()) {
                season.setMedium(targetSeries.get());
                return ResponseEntity.ok(this.seasonRepository.save(season));
            } else {
                throw new SeriesNotFoundException("There is no series with Id " + season.getSeriesMappingId());
            }
        }
        throw new DuplicateMediumException("The Season " + season.getSeasonTitle() + " with number " + season.getSeasonNumber() + " already exists!");
    }

    /**
     * This method is used to update an existing season
     * @param season    the season that should be updated
     * @return          the updated season
     * @throws SeriesNotFoundException  if there is no series with the given id
     * @throws DuplicateMediumException if there is already the same season in the database
     */
    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Season> update(@RequestBody Season season) throws SeriesNotFoundException, DuplicateMediumException {
        if(seasonService.isValidSeason(season)) {
            Optional<Series> targetSeries = seriesRepository.findById(season.getSeriesMappingId());
            if(targetSeries.isPresent()) {
                season.setMedium(targetSeries.get());
                return ResponseEntity.ok(this.seasonRepository.save(season));
            } else {
                throw new SeriesNotFoundException("There is no series with Id " + season.getSeriesMappingId());
            }
        }
        throw new DuplicateMediumException("The Season " + season.getSeasonTitle() + " with number " + season.getSeasonNumber() + " already exists!");
    }
}
