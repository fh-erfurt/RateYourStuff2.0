package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.mapping.List;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Books")
public class Book extends Medium{


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long bookId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String isbn;

    @Column
    private Boolean isEBook;

    @Column
    private Boolean isPrint;

    @Column(nullable = false)
    private Integer numberOfPages;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisherId", referencedColumnName = "publisherId")
    private Publisher publisher;
    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;

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
