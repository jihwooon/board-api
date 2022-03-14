package jpa.imform.domain;

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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Long id;

  private String userId;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @CreationTimestamp
  @Column(nullable = false, length = 20, updatable = false)
  private LocalDateTime saveDate;

  @UpdateTimestamp
  @Column(length = 20)
  private LocalDateTime updateDate;

  @Builder
  public Board(String userId, String title, String content) {
    this.userId = userId;
    this.title = title;
    this.content = content;
  }

  public void change(Board update) {
    this.userId = update.getUserId();
    this.title = update.getTitle();
    this.content = update.getContent();
  }
}
