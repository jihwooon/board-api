package jpa.imform.controller;

import jpa.imform.dto.MemberDto;
import jpa.imform.service.MemberService;
import jpa.imform.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final AuthenticationService authenticationService;

  @GetMapping("member")
  public List<MemberDto.ListMemberResponse> list() {
    return memberService.getMembers();
  }

  @GetMapping("member/{memberId}")
  public MemberDto.DetailMemberResponse detail(@PathVariable final Long memberId) {
    return MemberDto.DetailMemberResponse.of(memberService.getMember(memberId));
  }

  @PostMapping("members")
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.CreateMemberResponse create(
      @RequestHeader("Authorization") String authorization,
      @RequestBody @Valid final MemberDto.CreateMemberRequest create) {

    String accessToken = authorization.substring("Bearer ".length());
    Long memberId = authenticationService.parseToken(accessToken);

    System.out.println("***memberId : " + memberId);

    return memberService.createMember(create);
  }

  @PatchMapping("member/{memberId}")
  public MemberDto.UpdateMemberResponse update(@PathVariable final Long memberId,
                                               @RequestBody @Valid final MemberDto.UpdateMemberRequest update) {
    return memberService.updateMember(memberId, update);
  }

  @DeleteMapping("member/{memberId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable final Long memberId) {
    memberService.delete(memberId);
  }

  @GetMapping("member-count")
  public long count() {
    return memberService.getMemberCount();
  }

  @GetMapping("memberV1-count")
  public long countV1() {
    return memberService.getMemberCountV1();
  }

  //MemberRepository Entity manager
  @GetMapping("memberV1")
  public List<MemberDto.ListMemberResponse> listV1() {
    return memberService.getMembersV1();
  }

  //MemberJpaRepository
  @GetMapping("memberV2")
  public List<MemberDto.ListMemberResponse> listV2() {
    return memberService.getMembersV2();
  }

  //MemberRepository -> getDetail method name query
  @GetMapping("memberV1/{memberId}")
  public MemberDto.DetailMemberResponse detailV1(@PathVariable final Long memberId) {
    return MemberDto.DetailMemberResponse.of(memberService.getMemberV1(memberId));
  }

}
