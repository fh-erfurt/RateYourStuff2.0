package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public abstract class MediumDto {
    Long id;
    String mediumName;

    private String releaseDate;

    private String shortDescription;

    private String picturePath;

    private float averageRating;
    private int numberOfRatings;

    //written in screaming snake case because it is a final attribute in the model
    private int MAX_RATING_POINTS;

    //written in screaming snake case because it is a final attribute in the model
    private int MIN_RATING_POINTS;

    private int numberOfComments;

    private int numberOfCollections;

    List<String> genres;

    List<String> languages;

    private List<PersonAssignment> personAssignments;

    public void setLanguages(Set<Language> languages) {
        if(languages != null) {
            this.languages = languages.stream().map(Language::getLanguage).collect(Collectors.toList());
        } else {
            this.languages = new ArrayList<>();
        }

    }

    public void setGenres(Set<Genre> genres) {
        if(genres != null) {
            this.genres = genres.stream().map(Genre::getGenreName).collect(Collectors.toList());
        } else {
            this.genres = new ArrayList<>();
        }
    }

    public void setAverageRating(Set<Rating> mediumRatings) {
        if(mediumRatings != null) {
            int ratingSum = mediumRatings.stream().mapToInt(Rating::getGivenPoints).sum();
            if(mediumRatings.size() > 0) {
                this.averageRating = (float)ratingSum / mediumRatings.size();
            }
        } else {
            averageRating = 0;
        }
    }

    public void setNumberOfRatings(Set<Rating> ratings) {
        if(ratings != null) {
            this.numberOfRatings = ratings.size();
        } else {
            this.numberOfRatings = 0;
        }
    }

    public void setNumberOfCollections(Set<Collection> collections) {
        if(collections != null) {
            this.numberOfCollections = collections.size();
        } else {
            this.numberOfCollections = 0;
        }
    }

    public void setNumberOfComments(Set<Comment> comments) {
        if(comments != null) {
            this.numberOfComments = comments.size();
        } else {
            this.numberOfComments = 0;
        }

    }
}
