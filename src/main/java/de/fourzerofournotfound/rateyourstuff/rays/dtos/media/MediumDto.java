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
            this.languages = languages.stream().map(o -> o.getLanguage()).collect(Collectors.toList());
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres.stream().map(o -> o.getGenreName()).collect(Collectors.toList());
    }

    public void setAverageRating(Set<Rating> mediumRatings) {
        int ratingSum = mediumRatings.stream().mapToInt(o -> o.getGivenPoints()).sum();
        if(mediumRatings.size() > 0) {
            this.averageRating = ratingSum / mediumRatings.size();
        }
    }
}
