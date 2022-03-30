package jpa.imform.domain;

import jpa.imform.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "board_id")
  private Long id;

  @Column(name = "board_title")
  private String title;

  @Column(name = "board_content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "board")
  private List<Comment> comments = new ArrayList<>();

//  public void setMember(Member member) {
//    this.member = member;
//    member.getBoards().add(this);
//  }

  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setBoard(this);
  }

  @Builder
  public Board(Long id, String title, String content, Member member) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.member = member;
  }

  public void change(Board update) {
    this.title = update.getTitle();
    this.content = update.getContent();
  }

  public void changeRequest(Member member, BoardDto.UpdateBoardRequest update) {
    this.title = update.getTitle();
    this.content = update.getContent();
    this.member = member;

  }
}
