package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.LanguageDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Language;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.LanguageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active = test")
public class LanguageServiceTest {
    @Autowired
    private LanguageService languageService;

    @Autowired
    private LanguageRepository languageRepository;

    @BeforeEach
    void beforeEach()
    {
        languageRepository.deleteAll();
    }

    @Test
    void languageDtoShouldMatchLanguage() {
        //Given
        Language given = Language
                .builder()
                .language("Deutsch")
                .build();
        languageRepository.save(given);

        //When
        LanguageDto languageDto = languageService.convertToDto(given);

        //Then
        Assertions.assertThat(languageDto.getId()).isNotNull();
        Assertions.assertThat(languageDto.getId()).isEqualTo(given.getId());
        Assertions.assertThat(languageDto.getLanguage()).isEqualTo(given.getLanguage());

    }
}
