package jpa.imform.domain;

import jpa.imform.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  public void setBoard(Board board) {
    this.board = board;
    board.getComments().add(this);
  }

  public void setMember(Member member) {
    this.member = member;
    member.getComments().add(this);
  }

  @Builder
  public Comment(String content, Member member, Board board) {
    this.content = content;
    this.member = member;
    this.board = board;
  }

  public void changeRequest(Member member, Board board, Comment comment, CommentDto.UpdateCommentRequest request) {
    this.content = request.getContent();
    this.member = member;
    this.board = board;
  }
}
