package de.fourzerofournotfound.rateyourstuff.rays.dtos.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>The Book DTO is used to send book entities with less information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
public class BookDto extends MediumDto {

    private String isbn;
    //used to check if the book is available as ebook
    private Boolean isEBook;
    //used to check if the book is available as a printed version
    private Boolean isPrint;
    private Integer numberOfPages;
    //the name of the publisher
    private String bookPublisherBookPublisherTitle;
}
