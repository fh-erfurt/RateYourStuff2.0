package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
