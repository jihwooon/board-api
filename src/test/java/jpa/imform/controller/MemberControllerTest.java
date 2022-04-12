package jpa.imform.controller;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.error.InvalidTokenException;
import jpa.imform.service.MemberService;
import jpa.imform.service.impl.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {
  private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF4I";

  private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF42";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MemberController memberController;

  @MockBean
  private MemberService memberService;

  @MockBean
  private AuthenticationService authenticationService;

  @BeforeEach
  void setUp() {
    Member member = Member.builder()
        .name("Voyatouch")
        .password("1234")
        .phone("736-207-6273")
        .email("rfrid1b@squidoo.com")
        .build();

    given(memberService.createMember(any(MemberDto.CreateMemberRequest.class))).willReturn(MemberDto.CreateMemberResponse.of(member));

    given(authenticationService.parseToken(VALID_TOKEN)).willReturn(1L);

    given(authenticationService.parseToken(INVALID_TOKEN)).willThrow(new InvalidTokenException(INVALID_TOKEN));
  }

  @Test
  void createWithExistedMember() throws Exception {
    mockMvc.perform(post("/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Voyatouch\", \"password\": \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@squidoo.com\"}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isCreated());
  }

  @Test
  void createWithWrongMember() throws Exception {
    mockMvc.perform(post("/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Voyatouch\", \"password\": \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@om\"}")
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void createWithInValidMember() throws Exception {
    mockMvc.perform(post("/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Voyatouch\", \"password\": \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@om\"}"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void createWithEmptyMember() throws Exception {
    mockMvc.perform(post("/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{}"))
        .andExpect(status().isBadRequest());
  }
}
