package jpa.imform.domain;

import jpa.imform.dto.BoardDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "board")
  private List<Comment> comments = new ArrayList<>();

  public void setMember(Member member) {
    this.member = member;
    member.getBoards().add(this);
  }

  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setBoard(this);
  }

  @Builder
  public Board(String title, String content, Member member) {
    this.title = title;
    this.content = content;
    this.member = member;
  }

  public void changeRequest(Member member, BoardDto.UpdateBoardRequest request) {
    this.title = request.getTitle();
    this.content = request.getContent();
    this.member = member;
  }
}
