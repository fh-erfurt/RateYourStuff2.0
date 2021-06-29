package de.fourzerofournotfound.rateyourstuff.rays.services.errors;

public class InvalidCommentException extends Exception {
    public InvalidCommentException() {
        super();
    }

    public InvalidCommentException(String message) {
        super(message);
    }
}
