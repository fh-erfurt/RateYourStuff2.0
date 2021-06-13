package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

/**
 *
 * Invalid ISBN Exception
 * <p>This class is used to throw an exception, if an ISBN is not valid</p>
 * @author Robin Beck, Christoph Frischmuth, Mickey Knop, John Klippstein
 *
 * */
public class InvalidISBNException extends Exception{
    public InvalidISBNException() {
        super();
    }

    public InvalidISBNException(String exceptionMessage) {
        super(exceptionMessage);
    }
}