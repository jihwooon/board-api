package jpa.imform.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

  public String encode(Long memberId) {
    String secret = "12345367890123453678901234536789012";
    Key key = Keys.hmacShaKeyFor(secret.getBytes());

    return Jwts.builder()
        .claim("memberId", 1L)
        .signWith(key)
        .compact();
  }
}
