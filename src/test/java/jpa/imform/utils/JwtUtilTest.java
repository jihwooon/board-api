package jpa.imform.utils;

import io.jsonwebtoken.Claims;
import jpa.imform.error.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {
  private static final String SERCET = "12345678901234567890123456789012";
  private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF4I";
  private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MX0.vU91JPmJz_Kx_53C0i1p0i2NKEwTgMDOGtzMtx5UF43";

  private JwtUtil jwtUtil;

  @BeforeEach
  void setUp() {
    jwtUtil = new JwtUtil(SERCET);
  }

  @Test
  void login() {
    String memberId = jwtUtil.encode(1L);
    assertThat(memberId).isEqualTo(VALID_TOKEN);
  }

  @Test
  void decodeWithValidToken() {
    Claims claims = jwtUtil.decode(VALID_TOKEN);
    assertThat(claims.get("memberId", Long.class)).isEqualTo(1L);
  }

  @Test
  void decodeWithInvalidToken() {
    assertThatThrownBy(() -> jwtUtil.decode(INVALID_TOKEN))
        .isInstanceOf(InvalidTokenException.class);
  }

  @Test
  void decodeWithEmptyToken() {
    assertThatThrownBy(() -> jwtUtil.decode(""))
        .isInstanceOf(InvalidTokenException.class);
  }

  @Test
  void decodeWithNullToken() {
    assertThatThrownBy(() -> jwtUtil.decode(null))
        .isInstanceOf(InvalidTokenException.class);
  }
}
