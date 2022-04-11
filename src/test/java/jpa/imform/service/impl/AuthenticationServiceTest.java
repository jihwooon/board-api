package jpa.imform.service.impl;

import jpa.imform.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {
  public static final String SECRET = "12345367890123453678901234536789012";

  private AuthenticationService authenticationService;

  @BeforeEach
  void setUp() {
    JwtUtil jwtUtil = new JwtUtil(SECRET);

    authenticationService = new AuthenticationService(jwtUtil);
  }

  @Test
  void login() {
    String accessToken = authenticationService.login();

    assertThat(accessToken).contains(".xxx");
  }
}
