package jpa.imform.controller;

import jpa.imform.domain.Review;
import jpa.imform.dto.ReviewDto;
import jpa.imform.service.AuthenticationService;
import jpa.imform.service.ReviewService;
import jpa.imform.service.impl.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ReviewController {

  private final ReviewService reviewService;
  private final AuthenticationServiceImpl authenticationServiceImpl;
  private AuthenticationService authenticationService;

  public ReviewController(ReviewService reviewService, AuthenticationServiceImpl authenticationServiceImpl) {
    this.reviewService = reviewService;
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  @GetMapping("/reviews")
  public List<ReviewDto.ReviewResponseList> list() {
    return reviewService.getList();
  }

  @PostMapping("/reviews")
  public ReviewDto.ReviewResponseCreate create(
      @RequestBody final ReviewDto.ReviewRequestCreate request) {

    return reviewService.getCreate(request);
  }

  @PatchMapping("/reviews/{reviewId}")
  public ReviewDto.ReviewResponseUpdate update(
      @PathVariable final Long reviewId,
      @RequestBody final ReviewDto.ReviewRequestUpdate update
  ) {

    return reviewService.getUpdate(reviewId, update);
  }

  @DeleteMapping("/reviews/{reviewId}")
  public Review delete(
      @PathVariable final Long reviewId
  ) {
    return reviewService.getDelete(reviewId);
  }

}
