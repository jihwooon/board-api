package jpa.imform.repository;

import jpa.imform.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query(value = "select c from Comment c " +
      " join fetch c.member m" +
      " join fetch c.board b")
  List<Comment> findAllWithCommentThem();

}
