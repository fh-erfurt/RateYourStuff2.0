package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class CommentNotFoundException extends Exception{

    public CommentNotFoundException() {
        super();
    }

    public CommentNotFoundException(String message) {
        super(message);
    }

}