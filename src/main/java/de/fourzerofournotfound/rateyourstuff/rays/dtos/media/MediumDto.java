package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Genre;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Medium DTO
 * <p>The Medium DTO is used to provide reduced information to the client</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Setter
@Getter
public class MediumDto {
    Long id;
    String mediumName;

    private String releaseDate;

    private String shortDescription;

    //path of the image file for the media poster
    private String picturePath;

    private float averageRating;
    private int numberOfRatings;

    //written in screaming snake case because it is a final attribute in the model
    private int MAX_RATING_POINTS;

    //written in screaming snake case because it is a final attribute in the model
    private int MIN_RATING_POINTS;

    private int numberOfComments;

    private int numberOfCollections;

    //names of all genres the medium is related to
    List<String> genres;

    //names of all languages the medium is related to
    List<String> languages;

    //used to determine which type of media has been sent to the client
    private String mediaType;

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
