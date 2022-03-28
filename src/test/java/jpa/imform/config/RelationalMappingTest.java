
package jpa.imform.config;

import jpa.imform.domain.Address;
import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import jpa.imform.repository.BoardRepository;
import jpa.imform.repository.CommentRepository;
import jpa.imform.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@DisplayName("연관관계 매핑 조건")
class RelationalMappingTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CommentRepository commentRepository;

  private Board board1;
  private Board board2;
  private Member member1;
  private Member member2;
  private Comment comment1;
  private Comment comment2;

  @BeforeEach
  void setUp() {
    board1 = Board.builder()
        .title("제목")
        .content("설명")
        .build();

    member1 = Member.builder()
        .name("홍길동")
        .email("hong@gmail.com")
        .birth(810502)
        .password("1234")
        .address(new Address("서울", "경기", "1234"))
        .build();

    comment1 = Comment.builder()
        .content("content")
        .build();

     board2 = Board.builder()
        .title("제목")
        .content("설명")
        .build();

    member2 = Member.builder()
        .name("젠장")
        .email("jihwooon@gmail.com")
        .birth(920505)
        .password("1234")
        .address(new Address("부산", "해운대", "1234"))
        .build();

    comment2 = Comment.builder()
        .content("ㅇㅇㅇㅇ")
        .build();

  }

  @Nested
  @DisplayName("세 조건이 모든 조건이 성립 되는 경우")
  class Describe_testcase_collect {
    @Test
    @DisplayName("정상적으로 작동 합니다.")
    void it_id_relation() {
      Member memberId = memberRepository.save(member1);
      Comment commentId = commentRepository.save(comment1);
      Board boardId = boardRepository.save(board1);

      commentId.setMember(member1);
      commentId.setBoard(board1);
      boardId.setMember(member1);

      memberId.addComment(comment1);
      boardId.addComment(comment1);

      assertThat(commentId.getId()).isEqualTo(boardId.getId());
      assertThat(commentId.getId()).isEqualTo(memberId.getId());
      assertThat(boardId.getId()).isEqualTo(memberId.getId());
    }

    @Test
    @DisplayName("정상적으로 작동 합니다.")
    void it_id_relation_data() {
      Member memberId = memberRepository.save(member2);
      Comment commentId = commentRepository.save(comment2);
      Board boardId = boardRepository.save(board2);

      commentId.setMember(member2);
      commentId.setBoard(board2);
      boardId.setMember(member2);

      memberId.addComment(comment2);
      boardId.addComment(comment2);

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
    void it_board_member_class_with_test1() {
      Member memberId = memberRepository.save(member1);
      Board boardId = boardRepository.save(board1);

      boardId.setMember(memberId);

      assertThat(memberId.getId()).isEqualTo(boardId.getId());
    }

    @Test
    @DisplayName("BoardClass와 MemberClass 연관관계 매핑")
    void it_board_member_class_with_test2() {
      Member memberId = memberRepository.save(member2);
      Board boardId = boardRepository.save(board2);

      boardId.setMember(memberId);

      assertThat(memberId.getId()).isEqualTo(boardId.getId());
    }

    @Test
    @DisplayName("CommentClass와 MemberClass 연관관계 매핑")
    void testSave5() {
      Member memberId = memberRepository.save(member1);
      Comment commentId = commentRepository.save(comment1);

      commentId.setMember(memberId);
      memberId.addComment(commentId);

      assertThat(memberId.getId()).isEqualTo(commentId.getId());
      assertThat(commentId.getId()).isLessThan(2);
      assertThat(memberId.getId()).isLessThan(2);
    }

    @Test
    @DisplayName("CommentClass와 MemberClass 연관관계 매핑")
    void testSave6() {
      Member memberId = memberRepository.save(member2);
      Comment commentId = commentRepository.save(comment2);

      commentId.setMember(memberId);
      memberId.addComment(commentId);

      assertThat(memberId.getId()).isEqualTo(commentId.getId());
      assertThat(commentId.getId()).isLessThan(2);
      assertThat(memberId.getId()).isLessThan(2);
    }

    @Test
    @DisplayName("CommentClass와 BoardClass 연관관개 매핑")
    void testSave7() {
      Board boardId = boardRepository.save(board1);
      Comment commentId = commentRepository.save(comment1);

      commentId.setBoard(boardId);

      assertThat(boardId.getId()).isEqualTo(commentId.getId());
    }

    @Test
    @DisplayName("CommentClass와 BoardClass 연관관개 매핑")
    void testSave8() {
      Board boardId = boardRepository.save(board2);
      Comment commentId = commentRepository.save(comment2);

      commentId.setBoard(boardId);

      assertThat(boardId.getId()).isEqualTo(commentId.getId());
    }
  }
}
