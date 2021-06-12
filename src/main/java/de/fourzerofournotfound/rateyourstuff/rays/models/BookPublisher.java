package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="bookPublishers")
public class BookPublisher extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookPublisherId;

    @Column
    private String publisherTitle;

    @OneToMany (mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

    public Publisher(String publisherTitle) {
        this.publisherTitle = publisherTitle;
    }
}
