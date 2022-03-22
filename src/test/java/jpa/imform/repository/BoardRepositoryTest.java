
package jpa.imform.repository;

import jpa.imform.domain.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BoardRepository 클래스")
@DataJpaTest
class BoardRepositoryTest {

  private static final String BOARD_USERID = "userId";
  private static final String BOARD_TITLE = "제목";
  private static final String BOARD_CONTENT = "설명";

  @Autowired
  BoardRepository boardRepository;

  @AfterEach
  public void cleanUp() {
    boardRepository.deleteAll();
  }

  @Test
  void 게시판_Null_아닐_때() {
    assertThat(boardRepository).isNotNull();
  }

  @Nested
  @DisplayName("게시판을 저장할 때")
  class Descripb_save_boards {
    @Nested
    @DisplayName("값을 세팅하고")
    class it_Context_has_board_info {
      @Test
      @DisplayName("저장된 값을 확인한다.")
      void it_return_save() {
        Board result = boardRepository.save(Board.builder()
            .userId(BOARD_USERID)
            .title(BOARD_TITLE)
            .content(BOARD_CONTENT)
            .build());

        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo(BOARD_USERID);
        assertThat(result.getTitle()).isEqualTo(BOARD_TITLE);
        assertThat(result.getContent()).isEqualTo(BOARD_CONTENT);
      }
    }
  }

  @Test
  void 게시판_등록() {
     final Board board = Board.builder()
                .userId(BOARD_USERID)
                .title(BOARD_TITLE)
                .content(BOARD_CONTENT)
                .build();

    final Board result = boardRepository.save(board);

    assertThat(result.getId()).isNotNull();
    assertThat(result.getUserId()).isEqualTo(BOARD_USERID);
    assertThat(result.getTitle()).isEqualTo(BOARD_TITLE);
    assertThat(result.getContent()).isEqualTo(BOARD_CONTENT);
  }


  @Nested
  @DisplayName("게시판 저장하고 불러오기는")
  class Describe_save_posts {
      @Nested
      @DisplayName("게시글 정보를 받아 저장하고")
      class Context_has_post_info {
        @BeforeEach
        void setUp() {
          boardRepository.save(Board.builder()
              .userId(BOARD_USERID)
              .title(BOARD_TITLE)
              .content(BOARD_CONTENT)
              .build());
        }

        @Test
        @DisplayName("저장된 게시글 목록을 리턴한다.")
        void it_return_list() {
          List<Board> result = boardRepository.findAll();

          Board boards = result.get(0);
          assertThat(boards.getTitle()).isEqualTo(BOARD_TITLE);
          assertThat(boards.getContent()).isEqualTo(BOARD_CONTENT);
        }
      }
  }

  @Test
  void 게시판조회_사이즈0() {
    List<Board> result = boardRepository.findAll();

    assertThat(result.size()).isEqualTo(0);
  }

  @Test
  void 게시판조회_사이즈2() {
    Board List1 = Board.builder()
        .userId(BOARD_USERID)
        .title(BOARD_TITLE)
        .content(BOARD_CONTENT)
        .build();

    Board List2 = Board.builder()
        .userId(BOARD_USERID)
        .title("자바")
        .content("설명")
        .build();

    boardRepository.save(List1);
    boardRepository.save(List2);

    List<Board> result = boardRepository.findAllByUserId("userId");

    assertThat(result.size()).isEqualTo(2);

  }

  @Nested
  @DisplayName("글 등록시간은")
  class Discribe_BaseTimeEntity {
    @Nested
    @DisplayName("글이 등록되면")
    class Context_post {
      LocalDateTime now;

      @BeforeEach
      void setUp() {
        now = LocalDateTime.now();
        boardRepository.save(Board.builder()
            .userId(BOARD_USERID)
            .title(BOARD_TITLE)
            .content(BOARD_CONTENT)
            .build());
      }

      @Test
      @DisplayName("현재 시간이 저장된다.")
      void it_return_current_time() {
        List<Board> boardsList = boardRepository.findAll();

        int index = boardsList.size() - 1;

        Board boards = boardsList.get(index);

        assertThat(boards.getSaveDate()).isAfter(now);
        assertThat(boards.getUpdateDate()).isAfter(now);
      }
    }
  }
}
