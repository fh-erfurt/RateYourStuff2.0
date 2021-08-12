package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String message) {
        super(message);
    }
}
