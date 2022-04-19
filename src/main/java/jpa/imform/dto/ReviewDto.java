package jpa.imform.dto;

import jpa.imform.domain.Member;
import jpa.imform.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewDto {

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ReviewResponseList {

    private String title;

    private Member member;

    private String content;

    private LocalDateTime createDate;

    public ReviewResponseList(Review review) {
      this.title = review.getTitle();
      this.member = review.getMember();
      this.content = review.getContent();
      this.createDate = review.getCreateDate();
    }

    public static List<ReviewResponseList> of(List<Review> reviews) {
      return reviews.stream()
          .map(o -> new ReviewResponseList(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ReviewRequestCreate {
    private String title;
    private String content;
  }

  @Getter
  public static class ReviewResponseCreate {
    private String title;
    private String content;
    private Member member;
    private LocalDateTime createDate;

    public ReviewResponseCreate(Review review) {
      this.title = review.getTitle();
      this.content = review.getContent();
      this.member = review.getMember();
      this.createDate = review.getCreateDate();
    }

    public static ReviewResponseCreate of(final Review review) {
      return new ReviewResponseCreate(review);
    }
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ReviewRequestUpdate {
    private String title;
    private String content;
    private Member member;
  }

  @Getter
  public static class ReviewResponseUpdate {
    private String title;
    private String content;
    private Member member;
    private LocalDateTime lastModifiedDate;

    public ReviewResponseUpdate(Review review) {
      this.title = review.getTitle();
      this.content = review.getContent();
      this.member = review.getMember();
      this.lastModifiedDate = review.getModifiedDate();
    }

    public static ReviewResponseUpdate of(final Review review) {
      return new ReviewResponseUpdate(review);
    }
  }
}
