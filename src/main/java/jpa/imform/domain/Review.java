package jpa.imform.domain;

import jpa.imform.dto.ReviewDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  public void setMember(Member member) {
    this.member = member;
    member.getReviews().add(this);
  }

  @Builder
  public Review(String title, String content, Member member) {
    this.title = title;
    this.content = content;
    this.member = member;
  }

  public void changeWith(ReviewDto.ReviewRequestUpdate update) {
    this.title = update.getTitle();
    this.content = update.getContent();
  }
}
