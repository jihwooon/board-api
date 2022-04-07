package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.repository.JpaRepository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoardRepositoryTest {
  private String BOARD_TITLE = "title";
  private String BOARD_CONTENT = "content";

  @Autowired
  private BoardRepository boardRepository;

  private Board board;

  @BeforeEach
  void setUp() {
    board = Board.builder()
        .title("title")
        .content("content")
        .build();
  }

  @Test
  void 게시판_Null_아닐_때() {
    assertThat(boardRepository).isNotNull();
  }

  @Nested
  @DisplayName("게시판을 저장할 때")
  class Describe_save_boards {
    @Nested
    @DisplayName("값을 세팅하고")
    class it_Context_has_board_info {

      @Test
      @Transactional
      @Rollback(false)
      @DisplayName("저장된 값을 확인한다.")
      void it_return_save() {
        Board result = boardRepository.save(board);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo(BOARD_TITLE);
        assertThat(result.getContent()).isEqualTo(BOARD_CONTENT);
      }
    }
  }

  @Test
  void 게시판_등록() {
    Board result = boardRepository.save(board);

    assertThat(result.getId()).isNotNull();
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
            .title(BOARD_TITLE)
            .content(BOARD_CONTENT)
            .build());
      }

      @Test
      @Transactional
      @Rollback(false)
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

        assertThat(boards.getCreateDate()).isAfter(now);
        assertThat(boards.getModifiedDate()).isAfter(now);
      }
    }
  }
}
