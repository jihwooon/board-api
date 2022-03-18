// TODO :
//1. getBoards
//2. getBoard
//3. createBoard
//4. updateBoard
//5. deleteBoard

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
import static org.assertj.core.api.InstanceOfAssertFactories.PATH;
import static org.mockito.Mockito.verify;

@DisplayName("BoardService 클래스")
@SpringBootTest
@Transactional
class BoardServiceTest {
  private static final String BOARD_USERID = "userId";
  private static final String BOARD_TITLE = "제목";
  private static final String BOARD_CONTENT = "설명";

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private BoardService boardService;

  @Test
  void getBoards() {
    //given

    //when
    List<Board> boards = boardRepository.findAll();

    //then
    assertThat(boards).hasSize(0);
  }

  @Test
  void getBoardsWithNoBoard() {
    assertThat(boardService.getBoards()).isEmpty();
  }

  @Test
  void getBoardWithExistedId() {
    Board board = boardService.getBoard(1L);


  }

}
