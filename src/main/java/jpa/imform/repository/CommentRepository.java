package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import jpa.imform.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query(value = "select distinct c from Comment c " +
      " join fetch c.member m" +
      " join fetch c.board b")
  List<Comment> findAllWithCommentThem();

  List<Comment> findByMemberAndBoard(Member member, Board board);

  @Query(value = "select c from Comment c " +
      "where c.member = :member and c.board = :board")
  List<Comment> findAllWithDevelop(@Param("member") Member member,
                                   @Param("board") Board board);

//  @Query(value = "select c from Comment c where c.id = :id")
//  Optional<Comment> findIdWithDevelop(@Param("id") Long id);

  @Query(value = "select c from Comment c where c.id = :id and c.member = :member and c.board = :board")
  Optional<Comment> findIdWithDevelop(@Param("id") Long id,
                                      @Param("member") Member member,
                                      @Param("board") Board board);

  @Query(value = "select count(c) from Comment c")
  long findCount();

  //Dto 전달 객체 받기
//  @Query(value = "select new jpa.imform.dto.CommentDto.ListCommentResponse(c.content) from Comment c")
//  List<CommentDto.ListCommentResponse> findCommentDto();

}
