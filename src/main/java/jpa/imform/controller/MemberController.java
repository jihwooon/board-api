package jpa.imform.controller;

import jpa.imform.dto.MemberDto;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("member")
  public List<MemberDto.ListMemberResponse> list() {
    return memberService.getMembers();
  }

  @GetMapping("member/{memberId}")
  public MemberDto.DetailMemberResponse detail(@PathVariable Long memberId) {
    return MemberDto.DetailMemberResponse.of(memberService.getMember(memberId));
  }

  @PostMapping("member")
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.CreateMemberResponse create(@RequestBody @Valid MemberDto.CreateMemberRequest request) {
    return memberService.createMember(request);
  }

  @PatchMapping("member/{memberId}")
  public MemberDto.UpdateMemberResponse update(@PathVariable Long memberId, @RequestBody @Valid MemberDto.UpdateMemberRequest request) {
    return memberService.updateMember(memberId, request);
  }

  @DeleteMapping("member/{memberId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long memberId) {
    memberService.delete(memberId);
  }
}
