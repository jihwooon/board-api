package jpa.imform.controller;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.service.MemberService;
import jpa.imform.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationService authenticationService;

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
    @PreAuthorize("isAuthenticated()")
    public MemberDto.CreateMemberResponse create(
        @RequestBody @Valid final MemberDto.CreateMemberRequest create,
        Authentication authentication
    ) {

        return memberService.createMember(create);
    }

    @PatchMapping("members/{memberId}")
    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    public MemberDto.UpdateMemberResponse update(
        @PathVariable final Long memberId,
        @RequestBody @Valid final MemberDto.UpdateMemberRequest update
    ) {

        return memberService.updateMember(memberId, update);
    }

    @DeleteMapping("members/{memberId}")
    @PreAuthorize("isAuthenticated()")
    public Member remove(
        @PathVariable final Long memberId) {

        return memberService.delete(memberId);
    }

}
