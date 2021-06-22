package de.fourzerofournotfound.rateyourstuff.rays.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service("fus")
public class FileUploadService {
    /**
     * This method uploads the given file to the given path
     * @param uploadDir     the target directory
     * @param fileName      the desired name of the file
     * @param multipartFile the file that should be uploaded
     * @throws IOException
     */
    public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        //check if the target-path already exists
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        //try to upload the file
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + fileName, e);
        }
    }
}
