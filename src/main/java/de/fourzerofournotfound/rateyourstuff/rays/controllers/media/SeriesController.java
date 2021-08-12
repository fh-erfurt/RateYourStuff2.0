package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.SeriesDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.SeriesNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.SeriesRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.SeriesService;
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
@RequestMapping("/rest/series")
public class SeriesController {

    private final SeriesRepository seriesRepository;
    private final FileUploadService fileUploadService;
    private final PageableService pageableService;
    private final SeriesService seriesService;
    private final MediaService mediaService;

    @Autowired
    public SeriesController(SeriesRepository seriesRepository, FileUploadService fileUploadService, PageableService pageableService, SeriesService seriesService, MediaService mediaService) {
        this.seriesRepository = seriesRepository;
        this.fileUploadService = fileUploadService;
        this.pageableService = pageableService;
        this.seriesService = seriesService;
        this.mediaService = mediaService;
    }

    @GetMapping("/all")
    ResponseEntity<List<SeriesDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Series> series = this.seriesRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                series.stream().map(seriesService::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<SeriesDto> getById (@PathVariable Long id) throws SeriesNotFoundException {
        Optional<Series> series = this.seriesRepository.findById(id);
        if(series.isPresent()) {
            SeriesDto seriesDto = seriesService.convertToDto(series.get());
            return ResponseEntity.ok(seriesDto);
        } else {
            throw new SeriesNotFoundException("No Series found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<SeriesDto> findByTitle(@RequestParam(value="title") String title) throws SeriesNotFoundException {
        Optional<Series> series = this.seriesRepository.findByMediumName(title);
        if(series.isPresent()) {
            SeriesDto seriesDto = seriesService.convertToDto(series.get());
            return ResponseEntity.ok(seriesDto);
        } else {
            throw new SeriesNotFoundException("No Series with title " +title );
        }
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Series> add(@RequestBody Series series) throws DuplicateMediumException {
        if(this.mediaService.isValidSeries(series)) {
            this.seriesRepository.save(series);
            series.setGenres(this.mediaService.getGenresSet(series.getGenreStrings(), series));
            series.setLanguages(this.mediaService.getLanguageSet(series.getLanguageStrings(), series));
            series.setNetwork(this.seriesService.getNetwork(series.getNetworkTitle(), series));
            return ResponseEntity.ok(this.seriesRepository.save(series));
        } else {
            throw new DuplicateMediumException("The Series " + series.getMediumName() + " already exists.");
        }
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Series> update(@RequestBody Series series) {
        return ResponseEntity.ok(this.seriesRepository.save(series));
    }

    @DeleteMapping("/{id}")
    void deleteSeries (@PathVariable Long id) {
        this.seriesRepository.deleteById(id);
    }

    @PostMapping("/images/{id}")
    ResponseEntity<Series> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Optional<Series> series = this.seriesRepository.findById(id);
        //check if the given movie exists
        if(series.isPresent()) {
            series.get().setPicturePath(series.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Series.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.seriesRepository.save(series.get()));
        }
        return ResponseEntity.badRequest().build();
    }

}
