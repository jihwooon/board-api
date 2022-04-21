package jpa.imform.repository.JpaRepository;

import jpa.imform.domain.Member;
import jpa.imform.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  Optional<Review> findByIdAndMember(Long reviewId, Member member);
}
