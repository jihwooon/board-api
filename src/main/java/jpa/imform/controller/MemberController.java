//TODO :
//request 시 @vadata 체크 -> 검증
//회원 가입이 되지 못한 회원은 게시글, 댓글 불가능

package jpa.imform.controller;

import jpa.imform.dto.MemberDto;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("{id}")
  public MemberDto.MemberResponse detail(@PathVariable Long id) {
    return MemberDto.MemberResponse.of(memberService.getMember(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.MemberResponse create(@RequestBody @Valid MemberDto.MemberRequest request) {
    return memberService.createMember(request);
  }

  @PatchMapping("{id}")
  public MemberDto.MemberResponse update(@PathVariable Long id, @RequestBody @Valid MemberDto.MemberRequest request) {
    return memberService.updateMember(id, request);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long id) {
    memberService.delete(id);
  }
}
