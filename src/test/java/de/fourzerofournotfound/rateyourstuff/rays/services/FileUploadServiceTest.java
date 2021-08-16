package de.fourzerofournotfound.rateyourstuff.rays.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest(properties = "spring.profiles.active = test")
public class FileUploadServiceTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    void shouldDetectCorrectFileExtension() {
        //Given
        String fileName = "image.jpg";
        byte[] content = null;
        MultipartFile given = new MockMultipartFile(fileName, fileName, "image/jpeg", content);

        //When
        String extension = fileUploadService.getFileExtension(given);

        //Then
        Assertions.assertThat(extension).isEqualTo("jpg");
    }

}
