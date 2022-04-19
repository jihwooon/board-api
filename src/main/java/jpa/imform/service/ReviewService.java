package jpa.imform.service;

import jpa.imform.domain.Review;
import jpa.imform.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

  List<ReviewDto.ReviewResponseList> getList();

  ReviewDto.ReviewResponseCreate getCreate(ReviewDto.ReviewRequestCreate request);

  ReviewDto.ReviewResponseUpdate getUpdate(Long reviewId, ReviewDto.ReviewRequestUpdate request);

  Review getDelete(Long reviewId);
}
