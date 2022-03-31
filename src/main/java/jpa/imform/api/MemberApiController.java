package jpa.imform.api;


import jpa.imform.domain.Member;
import jpa.imform.dto.BoardDto;
import jpa.imform.dto.MemberDto;
import jpa.imform.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberRepository memberRepository;

  @GetMapping("/member/api/v1")
  public List<MemberDto.ListMemberResponse> listV1() {
    List<Member> members = memberRepository.findAll();
    List<MemberDto.ListMemberResponse> result = members.stream()
        .map(o -> new MemberDto.ListMemberResponse(o))
        .collect(Collectors.toList());

    return result;
  }
}
