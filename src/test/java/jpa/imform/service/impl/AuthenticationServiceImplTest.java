package jpa.imform.service.impl;

import jpa.imform.error.InvalidTokenException;
import jpa.imform.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("AuthenticationService")
class AuthenticationServiceImplTest {
  public static final String SECRET = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0";
  public static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.BGwAfdmwKJCrBws5J7rE55Qn9QWDixTHNV80OiFbAJg";
  public static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.BGwAfdmwKJCrBws5J7rE55Qn9QWDixTHNV80OiFbAJ2";

  private AuthenticationService authenticationServiceImpl;

  @BeforeEach
  void setUp() {
    JwtUtil jwtUtil = new JwtUtil(SECRET);
    authenticationServiceImpl = new AuthenticationService(jwtUtil);
  }

  @Test
  void login() {
    String accessToken = authenticationServiceImpl.login();

    assertThat(accessToken).contains(".");
  }

  @Test
  void parseTokenWithValidToken() {
    Long memberId = authenticationServiceImpl.parseToken(VALID_TOKEN);
    assertThat(memberId).isEqualTo(1L);
  }

  @Test
  void parseTokenWithInValidToken() {
    assertThatThrownBy(() -> authenticationServiceImpl.parseToken(INVALID_TOKEN))
        .isInstanceOf(InvalidTokenException.class);
  }

  @Test
  void parseTokenWithBlankToken() {
    assertThatThrownBy(() -> authenticationServiceImpl.parseToken(""))
        .isInstanceOf(InvalidTokenException.class);
  }

  @Test
  void parseTokenWithNullToken() {
    assertThatThrownBy(() -> authenticationServiceImpl.parseToken(null))
        .isInstanceOf(InvalidTokenException.class);
  }

}
//http POST localhost:8083/members name="Rank" password="1234" phone="010" email="test@exmaple" "Authorization: IMFOEM eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.BGwAfdmwKJCrBws5J7rE55Qn9QWDixTHNV80OiFbAJg"
