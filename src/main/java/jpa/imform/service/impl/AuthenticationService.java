package jpa.imform.service.impl;

import io.jsonwebtoken.Claims;
import jpa.imform.domain.Member;
import jpa.imform.dto.LoginDto;
import jpa.imform.repository.JpaRepository.MemberRepository;
import jpa.imform.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private MemberRepository memberRepository;
  private JwtUtil jwtUtil;

  public AuthenticationService(JwtUtil jwtUtil, MemberRepository memberRepository) {
    this.jwtUtil = jwtUtil;
    this.memberRepository = memberRepository;
  }

  public LoginDto.LoginInResponse login(LoginDto.LoginInRequest request) {
    String accessToken = jwtUtil.encode(1L);
    Member member = Member.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .build();
    return LoginDto.LoginInResponse.of(member, accessToken);
  }

  public Long parseToken(String accessToken) {
    Claims claims = jwtUtil.decode(accessToken);
    return claims.get("memberId", Long.class);
  }
}
