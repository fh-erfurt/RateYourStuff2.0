package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mediumId")
@Table(name = "Books")
public class Book extends Medium{

    @Column(nullable = false)
    private String isbn;

    @Column
    private Boolean isEBook;

    @Column
    private Boolean isPrint;

    @Column(nullable = false)
    private Integer numberOfPages;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bookPublisherId", referencedColumnName = "bookPublisherId")
    private BookPublisher bookPublisher;

    @Builder
    public Book(String mediumName,
                String shortDescription,
                LocalDate releaseDate,
                String isbn,
                Boolean isEBook,
                Boolean isPrint,
                int numberOfPages)
    {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.isbn = isbn;
        this.isEBook = isEBook;
        this.isPrint = isPrint;
        this.numberOfPages = numberOfPages;
    }
}
