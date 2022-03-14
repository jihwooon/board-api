// TODO :
//1. getBoards
//2. getBoard
//3. createBoard
//4. updateBoard
//5. deleteBoard

package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.error.BoardNotFoundException;
import jpa.imform.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

}
