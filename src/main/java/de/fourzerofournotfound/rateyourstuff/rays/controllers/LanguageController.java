package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.LanguageDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Language;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LanguageRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/languages")
public class LanguageController {

    private final PageableService pageableService;
    private final LanguageRepository languageRepository;
    private final LanguageService languageService;

    @Autowired
    public LanguageController(PageableService pageableService, LanguageRepository languageRepository, LanguageService languageService) {
        this.pageableService = pageableService;
        this.languageRepository = languageRepository;
        this.languageService = languageService;
    }


    @GetMapping("/all")
    ResponseEntity<List<LanguageDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Language> languages = this.languageRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(languages.stream()
                .map(languageService::convertToDto)
                .collect(Collectors.toList()));
    }
}