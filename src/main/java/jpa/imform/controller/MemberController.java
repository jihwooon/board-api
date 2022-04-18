package jpa.imform.controller;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.service.MemberService;
import jpa.imform.service.impl.AuthenticationServiceImpl;
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
  private final AuthenticationServiceImpl authenticationServiceImpl;

  @GetMapping("members")
  public List<MemberDto.ListMemberResponse> list() {
    return memberService.getMembers();
  }

  @GetMapping("members/{memberId}")
  public MemberDto.DetailMemberResponse detail(@PathVariable final Long memberId) {
    return MemberDto.DetailMemberResponse.of(memberService.getMember(memberId));
  }

  @PostMapping("members")
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.CreateMemberResponse create(
      @RequestHeader("Authorization") String authorization,
      @RequestBody @Valid final MemberDto.CreateMemberRequest create) {

    String accessToken = authorization.substring("Bearer ".length());
    Long memberId = authenticationServiceImpl.parseToken(accessToken);

    return memberService.createMember(create);
  }

  @PatchMapping("members/{memberId}")
  public MemberDto.UpdateMemberResponse update(
      @RequestHeader("Authorization") String authorization,
      @PathVariable final Long memberId,
      @RequestBody @Valid final MemberDto.UpdateMemberRequest update) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationServiceImpl.parseToken(accessToken);

    return memberService.updateMember(memberId, update);
  }

  @DeleteMapping("members/{memberId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Member remove(
      @RequestHeader("Authorization") String authorization,
      @PathVariable final Long memberId) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationServiceImpl.parseToken(accessToken);

    return memberService.delete(memberId);
  }

}
