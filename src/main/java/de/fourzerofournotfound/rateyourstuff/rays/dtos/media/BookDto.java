package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto extends MediumDto{
    private String isbn;

    private Boolean isEBook;

    private Boolean isPrint;

    private Integer numberOfPages;

    private BookPublisher bookPublisher;
}
