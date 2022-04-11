package jpa.imform.service.impl;

import jpa.imform.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {

  private AuthenticationService authenticationService;

  @BeforeEach
  void setUp() {
    JwtUtil jwtUtil = new JwtUtil();

    authenticationService = new AuthenticationService(jwtUtil);
  }

  @Test
  void login() {
    String accessToken = authenticationService.login();

    assertThat(accessToken).contains(".xxx");
  }
}
