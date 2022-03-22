package jpa.imform.controller;

import jpa.imform.domain.Member;
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
  public List<Member> list() {
    return memberService.getMembers();
  }

  @GetMapping("{id}")
  public Member detail(@PathVariable Long id) {
    return memberService.getMember(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Member create(@RequestBody Member member) {
    return memberService.createMember(member);
  }

  @PatchMapping("{id}")
  public Member update(@PathVariable Long id, Member source) {
    return memberService.updateMember(id, source);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Member remove(@PathVariable Long id) {
    return memberService.delete(id);
  }
}
