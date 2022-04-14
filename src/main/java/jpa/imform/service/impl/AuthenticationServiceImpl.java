package jpa.imform.service.impl;

import io.jsonwebtoken.Claims;
import jpa.imform.service.AuthenticationService;
import jpa.imform.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private JwtUtil jwtUtil;

  public AuthenticationServiceImpl(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  public String login() {
    return jwtUtil.encode(1L);
  }

  public Long parseToken(String accessToken) {
    Claims claims = jwtUtil.decode(accessToken);
    return claims.get("memberId", Long.class);
  }
}
