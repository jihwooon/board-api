package jpa.imform.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class AuthenticationService {

  public String login() {
    String secret = "12345367890123453678901234536789012";
    Key key = Keys.hmacShaKeyFor(secret.getBytes());

    return Jwts.builder()
        .claim("memberId", 1L)
        .signWith(key)
        .compact();
  }
}
