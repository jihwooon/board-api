package jpa.imform.api;


import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.dto.MemberListDto;
import jpa.imform.dto.MemberSimpleDto;
import jpa.imform.repository.EntityRepository.MemberJpaRepository;
import jpa.imform.repository.JpaRepository.MemberRepository;
import jpa.imform.service.impl.MemberServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberRepository memberRepository;
  private final MemberServiceImpl memberService;

  @GetMapping("/member/api/v1")
  public List<MemberDto.ListMemberResponse> listV1() {
    List<Member> members = memberRepository.findAll();
    List<MemberDto.ListMemberResponse> result = members.stream()
        .map(o -> new MemberDto.ListMemberResponse(o))
        .collect(Collectors.toList());

    return result;
  }

  @GetMapping("/member/api/v2")
  public List<MemberSimpleDto> listV2() {
    return memberService.getSimpleDto();
  }

//  @GetMapping("/member/api/v3")
//  public List<MemberSimpleDto> listV3() {
//    return memberJpaRepository.findListSimpleBoardDtos();
//  }

//  @GetMapping("/member/api/v3")
//  public List<MemberListDto> listV3() {
//    return memberRepository.findListDtobyBoards();
//  }

}
