package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query(value = "select distinct c from Comment c " +
      " join fetch c.member m" +
      " join fetch c.board b")
  List<Comment> findAllWithCommentThem();

  List<Comment> findByMemberAndBoard(final Member member, final Board board);

  @Query(value = "select c from Comment c " +
                "where c.member = :member and c.board = :board")
  List<Comment> findAllWithDevelop(@Param("member") final Member member,
                                   @Param("board") final Board board);

  @Query(value = "select c from Comment c where c.id = :id")
  Comment findIdWithDevelop(@Param("id") final Long id);

}
