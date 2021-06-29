package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.EpisodeNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.EpisodeRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
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

@RestController
@RequestMapping("/rest/episodes")
public class EpisodeController {

    @Autowired
    EpisodeRepository repository;

    @Autowired
    FileUploadService fus;

    @Autowired
    PageableService pageableService;

    @GetMapping("/all")
    ResponseEntity<Page<Episode>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        return ResponseEntity.ok(this.repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<Episode> getById(@PathVariable Long id) throws EpisodeNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new EpisodeNotFoundException("No Episode found for id " + id)));
    }

    @GetMapping()
    ResponseEntity<Episode> findByTitle(@RequestParam(value = "title") String title) throws EpisodeNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new EpisodeNotFoundException("No Episode with title " + title)));
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
