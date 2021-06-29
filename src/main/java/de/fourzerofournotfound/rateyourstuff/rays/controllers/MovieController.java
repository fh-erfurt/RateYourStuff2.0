package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.MovieNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
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
@RequestMapping("/rest/movies")
public class MovieController {
    @Autowired
    private MovieRepository repository;

    @Autowired
    private FileUploadService fus;

    @GetMapping("/all")
    ResponseEntity<Page<Movie>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable;
        if(!orderBy.equals("")) {
            Sort sort;
            if(order.toLowerCase().equals("asc")) {
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
    ResponseEntity<Movie> getById (@PathVariable Long id) throws MovieNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new MovieNotFoundException("No Movie found for id " + id)));
    }

    @GetMapping()
    ResponseEntity<Movie> findByTitle(@RequestParam(value="title") String title) throws MovieNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new MovieNotFoundException("No Movie with title " +title )));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Movie> add(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.repository.save(movie));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Movie> update(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.repository.save(movie));
    }

    @DeleteMapping("/{id}")
    void deleteMovie (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PutMapping("/images/{id}")
    ResponseEntity<Movie> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Optional<Movie> movie = this.repository.findById(id);
        //check if the given movie exists
        if(movie.isPresent()) {
            movie.get().setPicturePath(movie.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Movie.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(movie.get()));
        }
        return ResponseEntity.badRequest().build();
    }

}