package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import com.fasterxml.jackson.annotation.*;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import lombok.*;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>Medium</h1>
 * <p>This Model represents a medium.
 * The medium contains all attributes that are important for books, games, series, episodes, movies.</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Medium extends BaseModel {

    @Column(nullable = false, length = 200)
    private String mediumName;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false, length = 10000)
    private String shortDescription;

    @Column(length = 256)
    private String picturePath;


    @OneToMany(mappedBy = "medium")
    private Set<Rating> mediumRatings;

    @OneToMany(mappedBy = "medium")
    private Set<Comment> comments;

    //@JsonManagedReference ("media-collections")
    @ManyToMany(mappedBy = "media")
    Set<Collection> collections;

    //@JsonManagedReference ("media-genres")
    @ManyToMany(mappedBy="media", cascade = CascadeType.ALL)
    Set<Genre> genres = new HashSet<>();

    @JsonInclude
    @Transient
    List<String> genreStrings;

    //@JsonManagedReference ("media-languages")
    @ManyToMany(mappedBy="media", cascade = CascadeType.ALL)
    Set<Language> languages = new HashSet<>();

    @JsonInclude
    @Transient
    List<String> languageStrings;
}

