package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("ms")
public class MediaService {
    @Autowired
    BookRepository bookRepository;

    public boolean isValidMediaName(Medium newMedium)
    {
        Optional<Book> optionalMedia = bookRepository.findBookByMediumName(newMedium.getMediumName());
        if(optionalMedia.isPresent())
        {
            return true;
        }
        return false;
    }
}
