package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import jpa.imform.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

  //BoardRepository
  List<Board> findBoardByMember(Member member);

  //BoardRepositoryV1
  @Query(value = "select b from Board b where b.member = :member")
  List<Board> findAllWithDevelop(@Param("member") Member member);

  //BoardRepositoryV1 detail
  @Query(value = "select b from Board b where b.id = :id")
  Optional<Board> findIdWithDevelop(@Param("id") Long id);

}
