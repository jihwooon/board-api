package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

  @Autowired
  private CommentRepository commentRepository;
  private Comment comment;

  @BeforeEach
  public void SetUp() {
     comment = Comment.builder()
         .content("content")
//         .member(new Member())
//         .board(new Board())
         .build();
  }

  @Test
  @Transactional
  @Rollback(false)
  public void CommentTest() {
    Comment save = commentRepository.save(comment);

    assertThat(save.getContent()).isEqualTo("content");
  }

}
