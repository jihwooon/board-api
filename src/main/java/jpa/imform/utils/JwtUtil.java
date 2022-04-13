package jpa.imform.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jpa.imform.error.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {
  private final Key key;

  public JwtUtil(@Value("${jwt.secret}") String secret) {
    key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String encode(Long memberId) {
    return Jwts.builder()
        .claim("memberId", 1L)
        .signWith(key)
        .compact();
  }

  public Claims decode(String token) {
    if (token == null || token.isEmpty()) {
      throw new InvalidTokenException(token);
    }

    try {
      return Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (SignatureException e) {
      throw new InvalidTokenException(token);
    }
  }
}
