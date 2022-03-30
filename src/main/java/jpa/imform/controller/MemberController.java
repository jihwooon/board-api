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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping
  public List<MemberDto.MemberResponse> list() {
    return memberService.getMembers();
  }

  @GetMapping("{memberId}")
  public MemberDto.MemberResponse detail(@PathVariable Long memberId) {
    return MemberDto.MemberResponse.of(memberService.getMember(memberId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.MemberResponse create(@RequestBody @Valid MemberDto.MemberRequest request) {
    return memberService.createMember(request);
  }

  @PatchMapping("{memberId}")
  public MemberDto.MemberResponse update(@PathVariable Long memberId, @RequestBody @Valid MemberDto.MemberRequest request) {
    return memberService.updateMember(memberId, request);
  }

  @DeleteMapping("{memberId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long memberId) {
    memberService.delete(memberId);
  }
}
