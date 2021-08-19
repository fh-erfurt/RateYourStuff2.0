package de.fourzerofournotfound.rateyourstuff.rays.models.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This Model represents a book publisher. Each book can have one publisher</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "bookPublishers")
public class BookPublisher extends BaseModel {

    @Column
    private String bookPublisherTitle;

    @OneToMany(mappedBy = "bookPublisher")
    private List<Book> books = new ArrayList<>();

    @Builder
    public BookPublisher(String bookPublisherTitle) {
        this.bookPublisherTitle = bookPublisherTitle;
    }
}
