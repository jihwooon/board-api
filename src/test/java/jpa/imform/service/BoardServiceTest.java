

package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BoardService 클래스")
@SpringBootTest
@Transactional
class BoardServiceTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private BoardService boardService;

  private Board board;

  @Test
  void getBoards() {
    //given
    board = Board.builder().build();

    //when
    List<Board> boards = boardRepository.findAll();

    //then
    assertThat(boards).hasSizeLessThan(5);
  }
}
