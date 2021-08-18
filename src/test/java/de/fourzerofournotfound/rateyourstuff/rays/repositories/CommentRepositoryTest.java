package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @AfterEach
    public void afterEach() {
        commentRepository.deleteAll();
    }

    @Test
    public void shouldSaveComment() {

        //Given
        Comment given = Comment.builder()
                .textOfComment("Ich bin ein blöder Kommentar den eh keiner liest.")
                .build();

        //When
        Comment result = commentRepository.save(given);

        //Then
        Assertions.assertThat(result.getId()).isNotNull().isGreaterThan(0);
    }
}
