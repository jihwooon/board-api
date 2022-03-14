package jpa.imform.repository;

import jpa.imform.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

  List<Board> findAllByUserId(String userId);

}
