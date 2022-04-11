package jpa.imform.service.impl;

import jpa.imform.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  public String login() {
    JwtUtil jwtUtil = new JwtUtil();
    return jwtUtil.encode(1L);
  }
}
