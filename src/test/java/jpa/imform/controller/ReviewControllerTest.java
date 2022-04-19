package jpa.imform.controller;

import jpa.imform.domain.Review;
import jpa.imform.dto.ReviewDto;
import jpa.imform.service.ReviewService;
import jpa.imform.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
@DisplayName("ReviewControllerTest")
class ReviewControllerTest {
  private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF4I";
  private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF42";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReviewService reviewService;

  @MockBean
  private AuthenticationServiceImpl authenticationServiceImpl;

  @Autowired
  private ReviewController reviewController;

  @BeforeEach
  void setUp() {
    Review review = Review.builder()
        .title("제목")
        .content("test")
        .build();

    given(reviewService.getList()).willReturn(List.of());

    given(reviewService.getCreate(any(ReviewDto.ReviewRequestCreate.class))).willReturn(ReviewDto.ReviewResponseCreate.of(review));

  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/reviews")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void createWithExistedVALID_TOKEN() throws Exception {
    mockMvc.perform(post("/reviews")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\" : \"1234\",\"content\" : \"Dd3aasdadsdd3d\"}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isCreated());
  }

  @Test
  void createWithExistedINVALID_TOKEN() throws Exception {
    mockMvc.perform(post("/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\" : \"1234\",\"content\" : \"Dd3aasdadsdd3d\"}")
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

}
