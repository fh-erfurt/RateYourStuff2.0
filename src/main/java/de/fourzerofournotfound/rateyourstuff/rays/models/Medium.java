package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    private Set<Progress> progresses;

    @OneToMany(mappedBy = "medium")
    private Set<Comment> comments;

    @JsonManagedReference
    @ManyToMany(mappedBy = "media")
    Set<Collection> collections;

    @JsonManagedReference
    @ManyToMany(mappedBy = "media")
    Set<Genre> genres;

    @JsonManagedReference
    @ManyToMany(mappedBy = "media")
    Set<Language> languages;

    @JsonManagedReference
    @ManyToMany(mappedBy = "media")
    Set<Platform> platforms;

    @JsonManagedReference
    @OneToMany (mappedBy = "medium")
    private List<PersonAssignment> personAssignments;
}

