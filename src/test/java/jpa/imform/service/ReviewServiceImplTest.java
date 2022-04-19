package jpa.imform.service;

import jpa.imform.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ReviewService")
class ReviewServiceImplTest {

  private ReviewServiceImpl reviewServiceImpl;

  @Test
  void getList(){
    assertThat(reviewServiceImpl.getList()).isEmpty();
  }
}
