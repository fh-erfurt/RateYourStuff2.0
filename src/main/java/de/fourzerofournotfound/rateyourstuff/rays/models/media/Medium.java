package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.collections.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>This Model represents a medium.
 * The medium contains all attributes that are important for books, games, series, episodes, movies.</p>
 *
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
public abstract class Medium extends BaseModel {

    @ManyToMany(mappedBy = "media")
    Set<Collection> collections;
    @ManyToMany
    Set<Genre> genres = new HashSet<>();
    @JsonInclude
    @Transient
    List<String> genreStrings;
    @ManyToMany
    Set<Language> languages = new HashSet<>();
    @JsonInclude
    @Transient
    List<String> languageStrings;
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
}


