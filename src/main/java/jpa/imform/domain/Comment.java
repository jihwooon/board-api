package jpa.imform.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Comment {

  @Id
  @GeneratedValue
  @Column(name = "comment_id")
  private Long id;

  private String content;

  @CreationTimestamp
  @Column(nullable = false, length = 20, updatable = false)
  private LocalDate saveDate;

  @UpdateTimestamp
  @Column(length = 20)
  private LocalDate updateDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  @Builder
  public Comment(Long id, String content) {
    this.id = id;
    this.content = content;
  }
}
