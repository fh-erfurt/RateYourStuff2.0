package de.fourzerofournotfound.rateyourstuff.rays.dtos;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Setter
public class CommentDto {private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private Long id;
    private String textOfComment;
    private Long userId;
    private String userUserName;
    private Long mediumId;
    private String mediumMediumName;
    private Long commentParentId;
    private String createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = dateFormat.format(createdAt);
    }

}
