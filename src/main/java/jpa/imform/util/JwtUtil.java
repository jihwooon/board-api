package jpa.imform.util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {
  private final Key key;

  public JwtUtil(Key key) {
    this.key = key;
  }

  public String encode(Long id) {
    return Jwts.builder()
        .claim("username", 1L)
        .signWith(key)
        .compact();
  }

}
