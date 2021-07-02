package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.EpisodeNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/episodes")
public class EpisodeController {

    @Autowired
    private EpisodeRepository repository;

    @Autowired
    private FileUploadService fus;

    @Autowired
    private PageableService pageableService;

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/all")
    ResponseEntity<List<EpisodeDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Episode> episodes = this.repository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                episodes.stream().map(episodeService::convertToDto).collect(Collectors.toList()));
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
    ResponseEntity<Episode> add(@RequestBody Episode episode) {
        return ResponseEntity.ok(this.repository.save(episode));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Episode> update(@RequestBody Episode episode) {
        return ResponseEntity.ok(this.repository.save(episode));
    }

    @DeleteMapping("/{id}")
    void deleteEpisode(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PutMapping("/images/{id}")
    ResponseEntity<Episode> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Optional<Episode> episode = this.repository.findById(id);
        //check if the given movie exists
        if (episode.isPresent()) {
            episode.get().setPicturePath(episode.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Episode.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(episode.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}
