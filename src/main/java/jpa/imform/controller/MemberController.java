package jpa.imform.controller;

import jpa.imform.domain.Member;
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

  @GetMapping("{id}")
  public MemberDto.MemberResponse detail(@PathVariable Long id) {
    return MemberDto.MemberResponse.of(memberService.getMember(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.MemberResponse create(@RequestBody MemberDto.MemberRequest request) {
    return memberService.createMember(request);
  }

  @PatchMapping("{id}")
  public MemberDto.MemberResponse update(@PathVariable Long id, MemberDto.MemberRequest request) {
    return memberService.updateMember(id, request);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long id) {
    memberService.delete(id);
  }
}
