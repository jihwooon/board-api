package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.error.InvalidTokenException;
import jpa.imform.error.LoginFailException;
import jpa.imform.repository.JpaRepository.MemberRepository;
import jpa.imform.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("AuthenticationService")
class AuthenticationServiceImplImplTest {
  public static final String SECRET = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0";
  public static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.BGwAfdmwKJCrBws5J7rE55Qn9QWDixTHNV80OiFbAJg";
  public static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.BGwAfdmwKJCrBws5J7rE55Qn9QWDixTHNV80OiFbAJ2";

  private AuthenticationServiceImpl authenticationService;
  private MemberRepository memberRepository = mock(MemberRepository.class);

  @BeforeEach
  void setUp() {
    JwtUtil jwtUtil = new JwtUtil(SECRET);
    authenticationService = new AuthenticationServiceImpl(memberRepository, jwtUtil);

    Member member = Member.builder()
        .email("rfrid1b@squidoo.com")
        .password("8ixYbxleTQ")
        .build();

    given(memberRepository.findByEmail("rfrid1b@squidoo.com"))
        .willReturn(Optional.of(member));

    given(memberRepository.findByEmail("rfrid@squidoo.com"))
        .willThrow(new LoginFailException("rfrid@squidoo.com"));
  }

  @Test
  void login() {
    String accessToken = authenticationService.login("rfrid1b@squidoo.com", "8ixYbxleTQ");
    assertThat(accessToken).isEqualTo(VALID_TOKEN);

//    verify(memberRepository).findByEmail("rfrid1b@squidoo.com");
  }

  @Test
  void loginWithWrongEmail() {
    assertThatThrownBy(() -> authenticationService.login("rfrid@squidoo.com", "8ixYbxleTQ"))
        .isInstanceOf(LoginFailException.class);
  }

  @Test
  void parseTokenWithValidToken() {
    Long memberId = authenticationService.parseToken(VALID_TOKEN);
    assertThat(memberId).isEqualTo(1L);
  }

  @Test
  void parseTokenWithInValidToken() {
    assertThatThrownBy(() -> authenticationService.parseToken(INVALID_TOKEN))
        .isInstanceOf(InvalidTokenException.class);
  }

  @Test
  void parseTokenWithBlankToken() {
    assertThatThrownBy(() -> authenticationService.parseToken(""))
        .isInstanceOf(InvalidTokenException.class);
  }

  @Test
  void parseTokenWithNullToken() {
    assertThatThrownBy(() -> authenticationService.parseToken(null))
        .isInstanceOf(InvalidTokenException.class);
  }

}
