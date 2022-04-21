package jpa.imform.dto;

import jpa.imform.domain.Member;
import jpa.imform.domain.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewDto {

  @Getter
  public static class ReviewResponseList {

    private String name;
    private String title;
    private LocalDateTime createDate;

    public ReviewResponseList(Review review) {
      this.name = review.getMember().getName();
      this.title = review.getTitle();
      this.createDate = review.getCreateDate();
    }

    public static List<ReviewResponseList> of(List<Review> reviews) {
      return reviews.stream()
          .map(o -> new ReviewResponseList(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  @Setter
  public static class ReviewRequestCreate {
    private String title;
    private String content;
  }

  @Getter
  public static class ReviewResponseCreate {
    private String title;
    private String content;
    private LocalDateTime createDate;

    public ReviewResponseCreate(Review review) {
      this.title = review.getTitle();
      this.content = review.getContent();
      this.createDate = review.getCreateDate();
    }

    public ReviewResponseCreate(Member member, Review review) {
      this.title = review.getTitle();
      this.content = review.getContent();
      this.createDate = review.getCreateDate();
    }

    public static ReviewResponseCreate of(final Review review) {
      return new ReviewResponseCreate(review);
    }

    public static ReviewResponseCreate of(final Member member, final Review review) {
      return new ReviewResponseCreate(member, review);
    }
  }

  @Getter
  public static class ReviewRequestUpdate {
    private String title;
    private String content;
  }

  @Getter
  public static class ReviewResponseUpdate {
    private String name;
    private String title;
    private String content;
    private LocalDateTime lastModifiedDate;

    public ReviewResponseUpdate(Review review) {
      this.name = review.getMember().getName();
      this.title = review.getTitle();
      this.content = review.getContent();
      this.lastModifiedDate = review.getModifiedDate();
    }

    public static ReviewResponseUpdate of(final Review review) {
      return new ReviewResponseUpdate(review);
    }
  }

  @Getter
  public static class ReviewResponseDetail {
    private String name;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public ReviewResponseDetail(final Review review) {
      this.name = review.getMember().getName(); // 쿼리가 안 나간다. 왜 안 가는지 이유를 파악하기 -> 즉시 로딩 일 경우 쿼리
      this.title = review.getTitle();
      this.content = review.getContent();
      this.createDate = review.getCreateDate();
      this.lastModifiedDate = review.getModifiedDate();
    }

    public static ReviewResponseDetail of(final Review review) {
      return new ReviewResponseDetail(review);
    }

  }

}
