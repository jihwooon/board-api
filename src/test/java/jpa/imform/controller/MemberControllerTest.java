package jpa.imform.controller;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.error.InvalidTokenException;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.service.MemberService;
import jpa.imform.service.impl.AuthenticationService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberController.class)
@DisplayName("MemberController")
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

    given(memberService.getMember(1L)).willReturn(member);

    given(memberService.getMember(100L)).willThrow(new MemberNotFoundException("Not Found Exception Id"));

    given(memberService.updateMember(eq(1L), any(MemberDto.UpdateMemberRequest.class))).willReturn(MemberDto.UpdateMemberResponse.of(member));

    given(memberService.updateMember(eq(100L), any(MemberDto.UpdateMemberRequest.class))).willThrow(new MemberNotFoundException("Not Found Exception"));

    given(memberService.createMember(any(MemberDto.CreateMemberRequest.class))).willReturn(MemberDto.CreateMemberResponse.of(member));

    given(authenticationService.parseToken(VALID_TOKEN)).willReturn(1L);

    given(authenticationService.parseToken(INVALID_TOKEN)).willThrow(new InvalidTokenException(INVALID_TOKEN));

  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/members")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void detailWithExistedId() throws Exception {
    mockMvc.perform(get("/members/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(memberService).getMember(1L);
  }

  @Test
  void createWithExistedMember() throws Exception {
    mockMvc.perform(post("/members")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"장그\", \"password\": \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@squidoo.com\"}")
        .header("Authorization", "Bearer " + VALID_TOKEN))
        .andExpect(status().isCreated());

    verify(memberService).createMember(any());
  }

  @Test
  void detailWithNotExistedId() throws Exception {
    mockMvc.perform(get("/members/100")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void createWithWrongMember() throws Exception {
    mockMvc.perform(post("/members")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Voyatouch\", \"password\": \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@om\"}")
        .header("Authorization", "Bearer " + INVALID_TOKEN))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void createWithNotAuthMember() throws Exception {
    mockMvc.perform(post("/members")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"장그래\", \"password\": \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@squidoo.com\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createWithEmptyMember() throws Exception {
    mockMvc.perform(post("/members")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateWithExistedId() throws Exception {
    mockMvc.perform(patch("/members/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"장그래\", \"password\" : \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@squidoo.com\"}"))
        .andExpect(status().isOk());
  }

  @Test
  void updateWithNotExistedId() throws Exception {
    mockMvc.perform(patch("/members/100")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Voyatouch\", \"password\" : \"1234\", \"phone\" : \"736-207-6273\", \"email\" : \"rfrid1b@squidoo.com\"}"))
        .andExpect(status().isNotFound());
  }

  @Test
  void deleteWithExistedId() throws Exception {
    mockMvc.perform(delete("/members/1"))
        .andExpect(status().isNoContent());
  }

}
