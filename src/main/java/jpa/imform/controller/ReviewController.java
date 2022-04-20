//TODO : Review
package jpa.imform.controller;

import jpa.imform.dto.ReviewDto;
import jpa.imform.service.ReviewService;
import jpa.imform.service.impl.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

  private final ReviewService reviewService;
  private final AuthenticationServiceImpl authenticationServiceImpl;

  public ReviewController(ReviewService reviewService, AuthenticationServiceImpl authenticationServiceImpl) {
    this.reviewService = reviewService;
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  @GetMapping("/reviews")
  public List<ReviewDto.ReviewResponseList> list() {
    return reviewService.getList();
  }

  @GetMapping("/members/{memberId}/reviews/{reviewId}")
  public ReviewDto.ReviewResponseDetail detail(@PathVariable final Long memberId,
                                               @PathVariable final Long reviewId) {
    return reviewService.getDetail(memberId, reviewId);
  }

  @PostMapping("members/{memberId}/reviews")
  @ResponseStatus(HttpStatus.CREATED)
  public ReviewDto.ReviewResponseCreate create(
      @PathVariable final Long memberId,
      @RequestBody final ReviewDto.ReviewRequestCreate request) {
    return reviewService.CreateReview(memberId, request);
  }

  @PatchMapping("/members/{memberId}/reviews/{reviewId}")
  public ReviewDto.ReviewResponseUpdate update(
      @PathVariable final Long memberId,
      @PathVariable final Long reviewId,
      @RequestBody final ReviewDto.ReviewRequestUpdate update) {
    return reviewService.getUpdate(memberId, reviewId, update);
  }

  @DeleteMapping("/reviews/{reviewId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(
      @PathVariable final Long reviewId) {
    reviewService.getRemove(reviewId);
  }

}
