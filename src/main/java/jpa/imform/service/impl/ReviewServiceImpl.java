package jpa.imform.service.impl;

import jpa.imform.domain.Review;
import jpa.imform.dto.ReviewDto;
import jpa.imform.error.ReviewNotFoundException;
import jpa.imform.repository.JpaRepository.ReviewRepository;
import jpa.imform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public List<ReviewDto.ReviewResponseList> getList() {
    return ReviewDto.ReviewResponseList.of(reviewRepository.findAll());
  }

  @Override
  public ReviewDto.ReviewResponseCreate getCreate(ReviewDto.ReviewRequestCreate request) {
    Review review = Review.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .build();

    return ReviewDto.ReviewResponseCreate.of(reviewRepository.save(review));
  }

  @Override
  public ReviewDto.ReviewResponseUpdate getUpdate(Long reviewId, ReviewDto.ReviewRequestUpdate request) {
    Review review = getReview(reviewId);
    review.changeWith(request);

    return ReviewDto.ReviewResponseUpdate.of(reviewRepository.save(review));

  }

  @Override
  public Review getDelete(final Long reviewId) {
    Review review = getReview(reviewId);
    reviewRepository.delete(review);

    return review;
  }

  public Review getReview(final Long id) {
    return reviewRepository.findById(id)
        .orElseThrow(() -> new ReviewNotFoundException("return value of id"));
  }
}
