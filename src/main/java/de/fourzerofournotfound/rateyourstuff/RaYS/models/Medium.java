package de.fourzerofournotfound.rateyourstuff.RaYS.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "Media")
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediumId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column(nullable = false, length = 200)
    private String mediumName;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(nullable = false, length = 10000)
    private String shortDescription;


    @OneToMany(mappedBy = "medium")
    private Set<Raiting> mediumRatings;

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
}

