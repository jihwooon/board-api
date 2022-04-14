package jpa.imform.api;


import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.dto.MemberSimpleDto;
import jpa.imform.repository.JpaRepository.MemberRepository;
import jpa.imform.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("member-count")
  public long count() {
    return memberService.getMemberCount();
  }

  @GetMapping("memberV1-count")
  public long countV1() {
    return memberService.getMemberCountV1();
  }

  @GetMapping("memberV1")
  public List<MemberDto.ListMemberResponse> listV3() {
    return memberService.getMembersV1();
  }

  @GetMapping("memberV2")
  public List<MemberDto.ListMemberResponse> listV4() {
    return memberService.getMembersV2();
  }

  @GetMapping("memberV1/{memberId}")
  public MemberDto.DetailMemberResponse detailV1(@PathVariable final Long memberId) {
    return MemberDto.DetailMemberResponse.of(memberService.getMemberV1(memberId));
  }


}
