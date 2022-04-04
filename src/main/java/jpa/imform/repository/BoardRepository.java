package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

  List<Board> findBoardByMember(final Member member);

  @Query(value = "select b from Board b where b.member = :member")
  List<Board> findAllWithDevelop(@Param("member") final Member member);

  @Query(value = "select b from Board b where b.id = :id")
  Board findIdWithDevelop(@Param("id") final Long id);


}
