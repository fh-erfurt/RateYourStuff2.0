package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.SeriesNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.SeriesRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
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
@RequestMapping("/rest/series")
public class SeriesController {

    @Autowired
    SeriesRepository repository;

    @Autowired
    FileUploadService fus;


    @GetMapping("/all")
    ResponseEntity<Page<Series>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable;
        if (!orderBy.equals("")) {
            Sort sort;
            if (order.toLowerCase().equals("asc")) {
                sort = Sort.by(Sort.Direction.ASC, orderBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, orderBy);
            }
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }
        return ResponseEntity.ok(this.repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<Series> getById (@PathVariable Long id) throws SeriesNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new SeriesNotFoundException("No Series found for id " + id)));
    }

    @GetMapping()
    ResponseEntity<Series> findByTitle(@RequestParam(value="title") String title) throws SeriesNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new SeriesNotFoundException("No Series with title " +title )));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Series> add(@RequestBody Series series) {
        return ResponseEntity.ok(this.repository.save(series));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Series> update(@RequestBody Series series) {
        return ResponseEntity.ok(this.repository.save(series));
    }

    @DeleteMapping("/{id}")
    void deleteSeries (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PutMapping("/images/{id}")
    ResponseEntity<Series> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Optional<Series> series = this.repository.findById(id);
        //check if the given movie exists
        if(series.isPresent()) {
            series.get().setPicturePath(series.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Series.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(series.get()));
        }
        return ResponseEntity.badRequest().build();
    }

}
