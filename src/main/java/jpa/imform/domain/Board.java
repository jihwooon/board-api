//TODO
//1. board userid 제거 -> 연관성 제거
//2. LocalDateTime 공통 엔티티로 옮기
package jpa.imform.domain;

import jpa.imform.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

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

  @CreationTimestamp
  @Column(name = "board_save_date", nullable = false, length = 20, updatable = false)
  private LocalDateTime saveDate;

  @UpdateTimestamp
  @Column(name = "board_update_date", length = 20)
  private LocalDateTime updateDate;

  public void setMember(Member member) {
    this.member = member;
    member.getBoards().add(this);
  }

  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setBoard(this);
  }

  @Builder
  public Board(Long id, String title, String content, LocalDateTime saveDate, LocalDateTime updateDate) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.saveDate = saveDate;
    this.updateDate = updateDate;
  }

  public void change(Board update) {
    this.title = update.getTitle();
    this.content = update.getContent();
  }

  public void changeWith(BoardDto.BoardRequest update) {
    this.id = update.getId();
  }
}
