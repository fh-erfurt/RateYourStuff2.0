package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.mapping.List;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
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

    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;


}
