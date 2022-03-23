package jpa.imform.repository;

import jpa.imform.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback(false)
@SpringBootTest
class CommentRepositoryTest {

  @Autowired
  private CommentRepository commentRepository;

  @Test
  public void db_comment_1() {
    Comment comment = Comment.builder()
        .content("content")
        .build();
    Comment save = commentRepository.save(comment);

    assertThat(save.getContent()).isEqualTo("content");
  }

  @Test
  public void db_comment_2() {
    Comment comment = Comment.builder()
        .content("댓글")
        .build();
    Comment save = commentRepository.save(comment);

    assertThat(save.getContent()).isEqualTo("댓글");
  }
}
