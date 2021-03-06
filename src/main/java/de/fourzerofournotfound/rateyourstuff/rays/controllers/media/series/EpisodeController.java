package de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.series.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.series.EpisodeNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.series.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.series.SeasonRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.series.EpisodeService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GenreService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Episode entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/episodes")
public class EpisodeController {

    private final EpisodeRepository repository;
    private final FileUploadService fileUploadService;
    private final PageableService pageableService;
    private final EpisodeService episodeService;
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;
    private final LanguageService languageService;
    private final GenreService genreService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public EpisodeController(EpisodeRepository repository,
                             FileUploadService fileUploadService,
                             PageableService pageableService,
                             EpisodeService episodeService,
                             SeasonRepository seasonRepository,
                             EpisodeRepository episodeRepository,
                             LanguageService languageService,
                             GenreService genreService) {
        this.repository = repository;
        this.fileUploadService = fileUploadService;
        this.pageableService = pageableService;
        this.episodeService = episodeService;
        this.seasonRepository = seasonRepository;
        this.episodeRepository = episodeRepository;
        this.languageService = languageService;
        this.genreService = genreService;
    }

    /**
     * This Method returns all episodes thet belong to a certain season from the database
     *
     * @param id      the id of the season
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of EpisodeDTOs
     */
    @GetMapping("/season/{id}")
    ResponseEntity<List<EpisodeDto>> getAllBySeason(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order,
            @PathVariable Long id
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Episode> episodes = this.repository.findAllBySeasonId(id, pageable).getContent();
        return ResponseEntity.ok(
                episodes.stream().map(episodeService::convertToDto).collect(Collectors.toList()));
    }

    /**
     * This method is used to return a single episode
     *
     * @param id the id of the episode that should be searched
     * @return the found episodeDTO
     * @throws EpisodeNotFoundException if there is no episode with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<EpisodeDto> getById(@PathVariable Long id) throws EpisodeNotFoundException {
        Optional<Episode> episode = this.repository.findById(id);
        if (episode.isPresent()) {
            EpisodeDto episodeDto = episodeService.convertToDto(episode.get());
            return ResponseEntity.ok(episodeDto);
        } else {
            throw new EpisodeNotFoundException("No " + Episode.class.getSimpleName() + " found for id " + id);
        }
    }

    /**
     * This method is used to add a new episode.
     *
     * @param episode the episode that should be added
     * @return the EpisodeDTO of the new episode
     * @throws DuplicateMediumException if the episode is already saved
     * @throws SeasonNotFoundException  if there is no season with the given season number
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<EpisodeDto> add(@RequestBody Episode episode) throws DuplicateMediumException, SeasonNotFoundException {
        if (episodeService.isValidEpisode(episode)) {
            Optional<Season> targetSeason = seasonRepository.findById(episode.getSeasonMappingId());
            if (targetSeason.isPresent()) {
                episode.setSeason(targetSeason.get());
                episode.setGenres(genreService.getGenresSet(episode.getGenreStrings()));
                episode.setLanguages(languageService.getLanguageSet(episode.getLanguageStrings()));
                Episode savedEpisode = this.episodeRepository.save(episode);
                logger.info("Added " + Episode.class.getSimpleName() + " with id " + savedEpisode.getId());
                return ResponseEntity.ok(episodeService.convertToDto(savedEpisode));
            } else {
                throw new SeasonNotFoundException("There is no " + Season.class.getSimpleName() + " with Id " + episode.getSeasonMappingId());
            }
        }
        throw new DuplicateMediumException("The " + Episode.class.getSimpleName() + " " + episode.getMediumName() + " with number " + episode.getEpisodeNumber() + " already exists!");
    }

    /**
     * This method is used to update a given episode
     *
     * @param episode the episode that should be updated
     * @return the EpisodeDTO of the given episode
     * @throws SeasonNotFoundException  if there is no season with the given id
     * @throws DuplicateMediumException if the update would match another existing episode
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<EpisodeDto> update(@RequestBody Episode episode) throws SeasonNotFoundException, DuplicateMediumException, EpisodeNotFoundException {
        if (episodeService.isValidEpisode(episode)) {
            Optional<Episode> targetEpisode = episodeRepository.findById(episode.getId());
            if (targetEpisode.isPresent()) {
                Optional<Season> targetSeason = seasonRepository.findById(episode.getSeasonMappingId());
                if (targetSeason.isPresent()) {
                    episode.setSeason(targetSeason.get());
                    episode.setGenres(genreService.getGenresSet(episode.getGenreStrings()));
                    episode.setLanguages(languageService.getLanguageSet(episode.getLanguageStrings()));
                    Episode savedEpisode = this.episodeRepository.save(episode);
                    logger.info("Updated " + Episode.class.getSimpleName() + " with id " + episode.getId());
                    return ResponseEntity.ok(episodeService.convertToDto(savedEpisode));
                }
                throw new SeasonNotFoundException("There is no " + Season.class.getSimpleName() + " with Id " + episode.getSeasonMappingId());
            }
            throw new EpisodeNotFoundException("There is no " + Episode.class.getSimpleName() + " with Id " + episode.getId());
        }
        throw new DuplicateMediumException("The " + Episode.class.getSimpleName() + " " + episode.getMediumName() + " with number " + episode.getEpisodeNumber() + " already exists!");
    }


    /**
     * This method is used to add a thumbnail to an episode.
     *
     * @param multipartFile the poster that should be added
     * @param id            the id of the episode that should be updated
     * @return the updated episode
     * @throws IOException              if the upload fails
     * @throws EpisodeNotFoundException if there is no episode with the given id
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/images/{id}")
    ResponseEntity<EpisodeDto> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException, EpisodeNotFoundException {
        String fileName = StringUtils.cleanPath("poster." + fileUploadService.getFileExtension(multipartFile));
        Optional<Episode> episode = this.repository.findById(id);
        //check if the given movie exists
        if (episode.isPresent()) {
            episode.get().setPicturePath(episode.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Episode.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);
            Episode savedEpisode = this.repository.save(episode.get());
            logger.info("Added image \"" + savedEpisode.getPicturePath() + "\" for " + Episode.class.getSimpleName() + " with id " + savedEpisode.getId());
            return ResponseEntity.ok(episodeService.convertToDto(savedEpisode));
        }
        throw new EpisodeNotFoundException("There is no episode with the id " + id);
    }
}
