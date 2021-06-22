package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="bookPublishers")
public class BookPublisher extends BaseModel {

    @Column
    private String bookPublisherTitle;

    @OneToMany (mappedBy = "bookPublisher")
    private List<Book> books = new ArrayList<>();

    @Builder
    public BookPublisher(String bookPublisherTitle) {
        this.bookPublisherTitle = bookPublisherTitle;
    }
}
