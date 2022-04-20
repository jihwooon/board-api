package jpa.imform.service.impl;

import io.jsonwebtoken.Claims;
import jpa.imform.domain.Member;
import jpa.imform.error.LoginFailException;
import jpa.imform.repository.JpaRepository.MemberRepository;
import jpa.imform.service.AuthenticationService;
import jpa.imform.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final MemberRepository memberRepository;
  private final JwtUtil jwtUtil;

  public String login(String email, String password) {
    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> new LoginFailException(email));

    if (!member.authenticate(password)) {
      throw new LoginFailException(email);
    }

    return jwtUtil.encode(member.getId());
  }

  public Long parseToken(String accessToken) {
    Claims claims = jwtUtil.decode(accessToken);
    return claims.get("memberId", Long.class);
  }
}
