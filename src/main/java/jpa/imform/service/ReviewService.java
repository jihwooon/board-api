package jpa.imform.service;

import jpa.imform.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

  List<ReviewDto.ReviewResponseList> getList();

  ReviewDto.ReviewResponseDetail getDetail(Long memberId, Long reviewId);

  ReviewDto.ReviewResponseCreate CreateReview(Long memberId, ReviewDto.ReviewRequestCreate request);

  ReviewDto.ReviewResponseUpdate getUpdate(Long memberId, Long reviewId, ReviewDto.ReviewRequestUpdate request);

  void getRemove(Long userId, Long reviewId);

}
