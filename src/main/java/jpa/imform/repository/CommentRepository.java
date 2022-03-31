package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query(value = "select distinct c from Comment c " +
      " join fetch c.member m" +
      " join fetch c.board b")
  List<Comment> findAllWithCommentThem();

  List<Comment> findByMemberAndBoard(final Member member, final Board board);

}
