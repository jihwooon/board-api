package jpa.imform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @Column(name = "comment_content")
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
  public Comment(Long id, String content, Member member, Board board) {
    this.id = id;
    this.content = content;
    this.member = member;
    this.board = board;
  }

  public void change(Comment source) {
    this.content = source.getContent();
    this.board = source.getBoard();
    this.member = source.getMember();
  }

}
