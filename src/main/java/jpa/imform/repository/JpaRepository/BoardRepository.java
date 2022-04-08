package jpa.imform.repository.JpaRepository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import jpa.imform.dto.BoardSimpleDto;
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

  @Query(value = "select b from Board b where b.id = :id and b.member = :member")
  Optional<Board> findIdWithDevelop(@Param("id") Long id,
                                    @Param("member") Member member);

  @Query(value = "SELECT new jpa.imform.dto.BoardSimpleDto(b.title, b.content, m.name) FROM Board b join b.member m")
  List<BoardSimpleDto> findBoardDto();

//  //BoardRepositoryV1 detail
//  @Query(value = "select b from Board b where b.id = :id")
//  Optional<Board> findIdWithDevelop(@Param("id") Long id);


}
