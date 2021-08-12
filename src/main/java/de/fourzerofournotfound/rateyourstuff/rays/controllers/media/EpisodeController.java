package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.EpisodeNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeasonRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/episodes")
public class EpisodeController {

    private final EpisodeRepository repository;
    private final FileUploadService fus;
    private final PageableService pageableService;
    private final EpisodeService episodeService;
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeController(EpisodeRepository repository,
                             FileUploadService fus,
                             PageableService pageableService,
                             EpisodeService episodeService, SeasonRepository seasonRepository, EpisodeRepository episodeRepository) {
        this.repository = repository;
        this.fus = fus;
        this.pageableService = pageableService;
        this.episodeService = episodeService;
        this.seasonRepository = seasonRepository;
        this.episodeRepository = episodeRepository;
    }

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

    @GetMapping("/{id}")
    ResponseEntity<EpisodeDto> getById(@PathVariable Long id) throws EpisodeNotFoundException {
        Optional<Episode> episode = this.repository.findById(id);
        if(episode.isPresent()) {
            EpisodeDto episodeDto = episodeService.convertToDto(episode.get());
            return ResponseEntity.ok(episodeDto);
        } else {
            throw new EpisodeNotFoundException("No Episode found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<EpisodeDto> findByTitle(@RequestParam(value = "title") String title) throws EpisodeNotFoundException {
        Optional<Episode> episode = this.repository.findByMediumName(title);
        if(episode.isPresent()) {
            EpisodeDto episodeDto = episodeService.convertToDto(episode.get());
            return ResponseEntity.ok(episodeDto);
        } else {
            throw new EpisodeNotFoundException("No Episode with title " + title);
        }
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<Episode> add(@RequestBody Episode episode) throws DuplicateMediumException, SeasonNotFoundException {
        if(episodeService.isValidEpisode(episode)) {
            Optional<Season> targetSeason = seasonRepository.findById(episode.getSeasonMappingId());
            if(targetSeason.isPresent()) {
                episode.setSeason(targetSeason.get());
                return ResponseEntity.ok(this.episodeRepository.save(episode));
            } else {
                throw new SeasonNotFoundException("There is no season with Id " + episode.getSeasonMappingId());
            }
        }
        throw new DuplicateMediumException("The Episode " + episode.getMediumName() + " with number " + episode.getEpisodeNumber() + " already exists!");
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Episode> update(@RequestBody Episode episode) {
        return ResponseEntity.ok(this.repository.save(episode));
    }

    @DeleteMapping("/{id}")
    void deleteEpisode(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PostMapping("/images/{id}")
    ResponseEntity<Episode> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull("poster.jpg"));
        Optional<Episode> episode = this.repository.findById(id);
        //check if the given movie exists
        if (episode.isPresent()) {
            episode.get().setPicturePath(episode.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Episode.IMAGE_PATH_PREFIX + id;
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(episode.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}
