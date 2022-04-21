package jpa.imform.service;

import jpa.imform.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

  List<ReviewDto.ReviewResponseList> listReview();

  ReviewDto.ReviewResponseDetail detailReview(Long memberId, Long reviewId);

  ReviewDto.ReviewResponseCreate createReview(Long memberId, ReviewDto.ReviewRequestCreate request);

  ReviewDto.ReviewResponseUpdate updateReview(Long memberId, Long reviewId, ReviewDto.ReviewRequestUpdate request);

  void removeReview(Long userId, Long reviewId);

}
