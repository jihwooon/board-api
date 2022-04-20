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
  public List<ReviewDto.ReviewResponseList> getList() {
    return ReviewDto.ReviewResponseList.of(reviewRepository.findAll());
  }

  @Override
  public ReviewDto.ReviewResponseDetail getDetail(final Long memberId, final Long reviewId) {
    Member member = memberService.getMember(memberId);
    Review review = getReview(reviewId);

    return ReviewDto.ReviewResponseDetail.of(member, review);
  }

  @Override
  public ReviewDto.ReviewResponseCreate CreateReview(final Long memberId, final ReviewDto.ReviewRequestCreate request) {
    Member member = memberService.getMember(memberId);
    Review review = Review.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .member(member)
        .build();

    return ReviewDto.ReviewResponseCreate.of(reviewRepository.save(review));
  }

  @Override
  public ReviewDto.ReviewResponseUpdate getUpdate(final Long memberId, final Long reviewId, final ReviewDto.ReviewRequestUpdate request) {
    Member member = getMember(memberId);
    Review review = getReview(reviewId);
    review.changeWith(member,request);

    return ReviewDto.ReviewResponseUpdate.of(reviewRepository.save(review));

  }

  @Override
  public void getRemove(final Long reviewId) {
    Review review = getReview(reviewId);
    reviewRepository.delete(review);

  }

  public Review getReview(final Long id) {
    return reviewRepository.findById(id)
        .orElseThrow(() -> new ReviewNotFoundException("return value of id"));
  }

  public Member getMember(final Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException("return value of id"));

  }
}
