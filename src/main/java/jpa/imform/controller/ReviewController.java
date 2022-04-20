//TODO : Review
package jpa.imform.controller;

import jpa.imform.dto.ReviewDto;
import jpa.imform.service.AuthenticationService;
import jpa.imform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;
  private final AuthenticationService authenticationService;

  @GetMapping("/reviews")
  public List<ReviewDto.ReviewResponseList> list() {
    return reviewService.getList();
  }

  @GetMapping("/reviews/{reviewId}")
  public ReviewDto.ReviewResponseDetail detail(
      @RequestHeader("Authorization") String authorization,
      @PathVariable final Long reviewId) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationService.parseToken(accessToken);
    System.out.println("=-=-=-=-=-=-=-=-=userId : " + userId);

    return reviewService.getDetail(userId, reviewId);
  }

  @PostMapping("/reviews")
  @ResponseStatus(HttpStatus.CREATED)
  public ReviewDto.ReviewResponseCreate create(
      @RequestHeader("Authorization") String authorization,
      @RequestBody final ReviewDto.ReviewRequestCreate request) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationService.parseToken(accessToken);
    System.out.println("=-=-=-=-=-=-=-=-=userId : " + userId);

    return reviewService.CreateReview(userId, request);
  }

  @PatchMapping("/review/{reviewId}")
  public ReviewDto.ReviewResponseUpdate update(
      @RequestHeader("Authorization") String authorization,
      @PathVariable final Long reviewId,
      @RequestBody final ReviewDto.ReviewRequestUpdate update) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationService.parseToken(accessToken);
    System.out.println("=-=-=-=-=-=-=-=-=userId : " + userId);

    return reviewService.getUpdate(userId, reviewId, update);
  }

  @DeleteMapping("/review/{reviewId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(
      @RequestHeader("Authorization") String authorization,
      @PathVariable final Long reviewId) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationService.parseToken(accessToken);

    reviewService.getRemove(userId, reviewId);
  }

}
