package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.domain.Review;
import jpa.imform.dto.ReviewDto;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.error.ReviewNotFoundException;
import jpa.imform.repository.JpaRepository.MemberRepository;
import jpa.imform.repository.JpaRepository.ReviewRepository;
import jpa.imform.service.MemberService;
import jpa.imform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final MemberService memberService;
  private final MemberRepository memberRepository;

  @Override
  public List<ReviewDto.ReviewResponseList> listReview() {
    return ReviewDto.ReviewResponseList.of(reviewRepository.findAll());

  }

  @Override
  public ReviewDto.ReviewResponseDetail detailReview(final Long memberId, final Long reviewId) {
    Member member = memberService.getMember(memberId);
    Review review = getReviewByIdAndMember(reviewId, member);

    return ReviewDto.ReviewResponseDetail.of(review);
  }

  @Override
  public ReviewDto.ReviewResponseCreate createReview(final Long memberId, final ReviewDto.ReviewRequestCreate request) {
    Member member = memberService.getMember(memberId);
    Review review = Review.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .member(member)
        .build();

    return ReviewDto.ReviewResponseCreate.of(reviewRepository.save(review));
  }

  @Override
  public ReviewDto.ReviewResponseUpdate updateReview(final Long memberId, final Long reviewId, final ReviewDto.ReviewRequestUpdate request) {
    Member member = getMember(memberId);
    Review review = getReviewByIdAndMember(reviewId, member);
    review.changeWith(request);

    return ReviewDto.ReviewResponseUpdate.of(reviewRepository.save(review));

  }

  @Override
  public void removeReview(final Long userId, final Long reviewId) {
    Member member = getMember(userId);
    Review review = getReviewByIdAndMember(reviewId, member);

    reviewRepository.delete(review);
  }

  public Review getReviewByIdAndMember(final Long reviewId, Member member) {
    return reviewRepository.findByIdAndMember(reviewId, member)
        .orElseThrow(() -> new ReviewNotFoundException("Non-Members are not Accessible"));
  }

  public Member getMember(final Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException("Invalid Id are not Accessible"));
  }

}
