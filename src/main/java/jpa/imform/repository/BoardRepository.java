package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

  List<Board> findBoardByMember(Member member);
}
