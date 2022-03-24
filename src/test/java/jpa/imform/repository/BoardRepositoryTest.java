
package jpa.imform.repository;

import jpa.imform.domain.Address;
import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("연관관계 매핑 조")
@SpringBootTest
@Transactional
@Rollback(false)
class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CommentRepository commentRepository;

  private Board board;
  private Member member;
  private Comment comment;

  @BeforeEach
  void setUp() {
    board = Board.builder()
        .userId("userId")
        .title("제목")
        .content("설명")
        .build();

    member = Member.builder()
        .name("홍길동")
        .email("hong@gmail.com")
        .birth(810502)
        .password("1234")
        .address(new Address("서울", "경기", "1234"))
        .build();

    comment = Comment.builder()
        .content("content")
        .build();
  }

  @Nested
  @DisplayName("세 조건이 모든 조건이 성립 되는 경우")
  class Describe_testcase_collect {
    @Test
    @DisplayName("정상적으로 작동 합니다.")
    void it_id_relation() {
      Member memberId = memberRepository.save(member);
      Comment commentId = commentRepository.save(comment);
      Board boardId = boardRepository.save(board);

      commentId.setMember(member);
      commentId.setBoard(board);
      boardId.setMember(member);

      assertThat(commentId.getId()).isEqualTo(boardId.getId());
      assertThat(commentId.getId()).isEqualTo(memberId.getId());
      assertThat(boardId.getId()).isEqualTo(memberId.getId());
    }
  }

  @Nested
  @DisplayName("세 조건 중 한 조건이라도 빠지는 경우")
  class Describe_testcase_Not_collect {

    @Test
    @DisplayName("BoardClass와 MemberClass 연관관계 매핑")
    void it_board_member_class_with_test() {
      Member memberId = memberRepository.save(member);
      Board boardId = boardRepository.save(board);

      boardId.setMember(memberId);

      assertThat(memberId.getId()).isEqualTo(boardId.getId());
    }

    @Test
    @DisplayName("CommentClass와 MemberClass 연관관계 매핑")
    void testSave5() {
      Member memberId = memberRepository.save(member);
      Comment commentId = commentRepository.save(comment);

      commentId.setMember(memberId);

      assertThat(memberId.getId()).isEqualTo(commentId.getId());
    }

    @Test
    @DisplayName("CommentClass와 BoardClass 연관관개 매핑")
    void testSave4() {
      Board boardId = boardRepository.save(board);
      Comment commentId = commentRepository.save(comment);

      commentId.setBoard(boardId);

      assertThat(boardId.getId()).isEqualTo(commentId.getId());
    }
  }











//
//  @Test
//  void 게시판_Null_아닐_때() {
//    assertThat(boardRepository).isNotNull();
//  }
//
//  @Nested
//  @DisplayName("게시판을 저장할 때")
//  class Descripb_save_boards {
//    @Nested
//    @DisplayName("값을 세팅하고")
//    class it_Context_has_board_info {
//
//      @Test
//      @Transactional
//      @Rollback(false)
//      @DisplayName("저장된 값을 확인한다.")
//      void it_return_save() {
//        Board result = boardRepository.save(board);
//
//        assertThat(result.getId()).isNotNull();
//        assertThat(result.getUserId()).isEqualTo(BOARD_USERID);
//        assertThat(result.getTitle()).isEqualTo(BOARD_TITLE);
//        assertThat(result.getContent()).isEqualTo(BOARD_CONTENT);
//      }
//    }
//  }
//
//  @Test
//  void 게시판_등록() {
//    Board result = boardRepository.save(board);
//
//    assertThat(result.getId()).isNotNull();
//    assertThat(result.getUserId()).isEqualTo(BOARD_USERID);
//    assertThat(result.getTitle()).isEqualTo(BOARD_TITLE);
//    assertThat(result.getContent()).isEqualTo(BOARD_CONTENT);
//  }
//
//
//  @Nested
//  @DisplayName("게시판 저장하고 불러오기는")
//  class Describe_save_posts {
//      @Nested
//      @DisplayName("게시글 정보를 받아 저장하고")
//      class Context_has_post_info {
//        @BeforeEach
//        void setUp() {
//          boardRepository.save(Board.builder()
//              .userId(BOARD_USERID)
//              .title(BOARD_TITLE)
//              .content(BOARD_CONTENT)
//              .build());
//        }
//
//        @Test
//        @DisplayName("저장된 게시글 목록을 리턴한다.")
//        void it_return_list() {
//          List<Board> result = boardRepository.findAll();
//
//          Board boards = result.get(0);
//          assertThat(boards.getTitle()).isEqualTo(BOARD_TITLE);
//          assertThat(boards.getContent()).isEqualTo(BOARD_CONTENT);
//        }
//      }
//  }

//  @Test
//  void 게시판조회_사이즈0() {
//    List<Board> result = boardRepository.findAll();
//
//    assertThat(result.size()).isEqualTo(0);
//  }

//  @Test
//  void 게시판조회_사이즈2() {
//    Board List1 = Board.builder()
//        .userId(BOARD_USERID)
//        .title(BOARD_TITLE)
//        .content(BOARD_CONTENT)
//        .build();
//
//    Board List2 = Board.builder()
//        .userId(BOARD_USERID)
//        .title("자바")
//        .content("설명")
//        .build();
//
//    boardRepository.save(List1);
//    boardRepository.save(List2);
//
//    List<Board> result = boardRepository.findAllByUserId("userId");
//
//    assertThat(result.size()).isEqualTo(2);
//
//  }

//  @Nested
//  @DisplayName("글 등록시간은")
//  class Discribe_BaseTimeEntity {
//    @Nested
//    @DisplayName("글이 등록되면")
//    class Context_post {
//      LocalDateTime now;
//
//      @BeforeEach
//      void setUp() {
//        now = LocalDateTime.now();
//        boardRepository.save(Board.builder()
//            .userId(BOARD_USERID)
//            .title(BOARD_TITLE)
//            .content(BOARD_CONTENT)
//            .build());
//      }
//
//      @Test
//      @DisplayName("현재 시간이 저장된다.")
//      void it_return_current_time() {
//        List<Board> boardsList = boardRepository.findAll();
//
//        int index = boardsList.size() - 1;
//
//        Board boards = boardsList.get(index);
//
//        assertThat(boards.getSaveDate()).isAfter(now);
//        assertThat(boards.getUpdateDate()).isAfter(now);
//      }
//    }
//  }
}
