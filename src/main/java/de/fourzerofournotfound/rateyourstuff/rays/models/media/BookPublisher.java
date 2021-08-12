package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>BookPublisher</h1>
 * <p>This Model represents a book publisher. Each book can have one publisher</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="bookPublishers")
public class BookPublisher extends BaseModel {

    @Column
    private String bookPublisherTitle;

    //@JsonBackReference
    @OneToMany (mappedBy = "bookPublisher")
    private List<Book> books = new ArrayList<>();

    @Builder
    public BookPublisher(String bookPublisherTitle) {
        this.bookPublisherTitle = bookPublisherTitle;
    }
}
