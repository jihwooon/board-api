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
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("member")
  public List<MemberDto.MemberResponse> list() {
    return memberService.getMembers();
  }

  @GetMapping("member/{memberId}")
  public MemberDto.MemberResponse detail(@PathVariable Long memberId) {
    return MemberDto.MemberResponse.of(memberService.getMember(memberId));
  }

  @PostMapping("member")
  @ResponseStatus(HttpStatus.CREATED)
  public MemberDto.MemberResponse create(@RequestBody @Valid MemberDto.MemberRequest request) {
    return memberService.createMember(request);
  }

  @PatchMapping("member/{memberId}")
  public MemberDto.MemberResponse update(@PathVariable Long memberId, @RequestBody @Valid MemberDto.MemberRequest request) {
    return memberService.updateMember(memberId, request);
  }

  @DeleteMapping("member/{memberId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long memberId) {
    memberService.delete(memberId);
  }
}
