package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeasonDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.InvalidSeriesException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeriesRepository;
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

@CrossOrigin("*")
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

    @GetMapping("/{id}")
    ResponseEntity<SeasonDto> getById (@PathVariable Long id) throws SeasonNotFoundException {
        Optional<Season> season = this.seasonRepository.findById(id);
        if(season.isPresent()) {
            return ResponseEntity.ok(this.seasonService.convertToDto(season.get()));
        }
        throw new SeasonNotFoundException("No Season found for id " + id);
    }

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

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    @CrossOrigin
    ResponseEntity<Season> add(@RequestBody Season season) throws InvalidSeriesException, MediumAlreadyExistsException {
        if(seasonService.isValidSeason(season)) {
            Optional<Series> targetSeries = seriesRepository.findById(season.getSeriesMappingId());
            if(targetSeries.isPresent()) {
                season.setMedium(targetSeries.get());
                return ResponseEntity.ok(this.seasonRepository.save(season));
            } else {
                throw new InvalidSeriesException("There is no series with Id " + season.getSeriesMappingId());
            }
        }
        throw new MediumAlreadyExistsException("The Season " + season.getSeasonTitle() + " with number " + season.getSeasonNumber() + " already exists!");
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Season> update(@RequestBody Season season) throws InvalidSeriesException, MediumAlreadyExistsException {
        if(seasonService.isValidSeason(season)) {
            Optional<Series> targetSeries = seriesRepository.findById(season.getSeriesMappingId());
            if(targetSeries.isPresent()) {
                season.setMedium(targetSeries.get());
                return ResponseEntity.ok(this.seasonRepository.save(season));
            } else {
                throw new InvalidSeriesException("There is no series with Id " + season.getSeriesMappingId());
            }
        }
        throw new MediumAlreadyExistsException("The Season " + season.getSeasonTitle() + " with number " + season.getSeasonNumber() + " already exists!");
    }

    @DeleteMapping("/{id}")
    void deleteSeason (@PathVariable Long id) {
        this.seasonRepository.deleteById(id);
    }

}
