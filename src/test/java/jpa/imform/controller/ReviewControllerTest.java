//TODO : 토큰 유무 검증
// 1. 정상적인 토큰과 값이 들어 갔을 경우
// 2. 잘못 된 토큰과 값이 들어 갔을 경우
// 3. 토큰이 없을 경우
// 4. 빈값이 들어가는 경우
// 5. 잘못 된 토큰과 빈값이 들어 갔을 경우

package jpa.imform.controller;

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

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
@DisplayName("ReviewControllerTest")
class ReviewControllerTest {
  private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF4I";
  private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5U325";
  private static final String CONTENT = "Dasyurus viverrinus";
  private static final String TITLE = "Ovis dalli stonei";

  private static final Long MEMBER_ID = 1L;
  private static final Long REVIEW_ID = 1L;
  private static final Long REVIEW_ID_EXCEPTION = 100L;

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
//    Review review = Review.builder()
//        .title(CONTENT)
//        .content(TITLE)
//        .build();
//    Member member  = Member.builder()
//    .build();
//
//    given(reviewService.getList())
//        .willReturn(List.of());

//    given(reviewService.getDetail(eq(1L), eq(1L)))
//        .willReturn(ReviewDto.ReviewResponseDetail.of(member, review));

//    given(reviewService.CreateReview(eq()))
//
//    given(reviewService.getUpdate(eq(1L),any(ReviewDto.ReviewRequestUpdate.class))).willReturn(ReviewDto.ReviewResponseUpdate.of(review));
//
//    ReviewDto.ReviewRequestUpdate request = mock(ReviewDto.ReviewRequestUpdate.class);
//
//    when(reviewService.getUpdate(eq(1L), eq(100L), any(request.class)))
//
//    given(reviewService.getDelete(1L)).willReturn(review);
//
//    given(reviewService.getDelete(100L)).willThrow(new ReviewNotFoundException("Not Found Review"));
//
//    given(authenticationServiceImpl.parseToken(INVALID_TOKEN)).willThrow(new InvalidTokenException("Wrong Token"));
//
//    given(authenticationServiceImpl.parseToken(VALID_TOKEN)).willReturn(1L);
  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/reviews")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void detailWithValid_Token() throws Exception {
    mockMvc.perform(get("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isOk());
  }

  @Test
  void detailWithInValid_Token() throws Exception {
    mockMvc.perform(get("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void detailWithNotHaveValid_Token() throws Exception {
    mockMvc.perform(get("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void detailWithValid_TokenAndReviewIdException() throws Exception {
    mockMvc.perform(get("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID_EXCEPTION)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isNotFound());
  }

  @Test
  void createWithExistedVALID_TOKEN() throws Exception {
    mockMvc.perform(post("/members/{memberId}/reviews", MEMBER_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\" : \"Dasyurus viverrinus\",\"content\" : \"Ovis dalli stonei\"}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isCreated());
  }

  @Test
  void createWithExistedINVALID_TOKEN() throws Exception {
    mockMvc.perform(post("/members/{memberId}/reviews", MEMBER_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\" : \"Dasyurus viverrinus\",\"content\" : \"Ovis dalli stonei\"}")
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void createWithIsNoTOKEN() throws Exception {
    mockMvc.perform(post("/members/{memberId}/reviews", MEMBER_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\" : \"1234\",\"content\" : \"Dd3aasdadsdd3d\"}"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void createWithNoContent() throws Exception {
    mockMvc.perform(post("/members/{memberId}/reviews", MEMBER_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{}"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void updateWithExistedValid_Token() throws Exception {
    mockMvc.perform(patch("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\" : \"1264\",\"content\" : \"Dd3aasdadsdd3d\"}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isOk());
  }

  @Test
  void updateWithNotExistedValid_Token() throws Exception {
    mockMvc.perform(patch("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\" : \"1264\",\"content\" : \"Dd3aasdadsdd3d\"}")
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void updateWithNotExistedContentAndValid_Token() throws Exception {
    mockMvc.perform(patch("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isOk());
  }

  @Test
  void updateWithNotExistedContentAndInValid_Token() throws Exception {
    mockMvc.perform(patch("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{}")
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void updateWithWrongId() throws Exception {
    mockMvc.perform(patch("/members/{memberId}/reviews/{reviewId}", MEMBER_ID, REVIEW_ID_EXCEPTION)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\" : \"1264\",\"content\" : \"Dd3aasdadsdd3d\"}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isNotFound());
  }

  @Test
  void deleteWithExistedValid_Token() throws Exception {
    mockMvc.perform(delete("/reviews/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteWithNoToken() throws Exception {
    mockMvc.perform(delete("/reviews/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void deleteWithWrongIdAndNoToken() throws Exception {
    mockMvc.perform(delete("/reviews/100")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isUnauthorized());
  }

}
