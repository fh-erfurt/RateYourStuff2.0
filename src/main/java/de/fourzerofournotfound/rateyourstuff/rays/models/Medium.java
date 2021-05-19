package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediumId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column(nullable = false, length = 200)
    private String mediumName;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false, length = 10000)
    private String shortDescription;


    @OneToMany(mappedBy = "medium")
    private Set<Rating> mediumRatings;

    @OneToMany(mappedBy = "medium")
    private Set<Progress> progresses;

    @OneToMany(mappedBy = "medium")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "media")
    Set<Collection> collections;

    @ManyToMany(mappedBy = "media")
    Set<Genre> genres;

    @ManyToMany(mappedBy = "media")
    Set<Language> languages;

    @ManyToMany(mappedBy = "media")
    Set<Platform> platforms;

    @OneToMany (mappedBy = "medium")
    private List<PersonAssignment> personAssignments;
}

