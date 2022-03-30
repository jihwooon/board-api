package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.repository.MemberRepository;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  public List<MemberDto.ListMemberResponse> getMembers() {
    return MemberDto.ListMemberResponse.of(memberRepository.findAll());
  }

  public Member getMember(final Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException("return value of id"));
  }

  @Override
  public MemberDto.CreateMemberResponse createMember(final MemberDto.CreateMemberRequest request) {
    Member member = Member.builder()
        .name(request.getName())
        .password(request.getPassword())
        .birth(request.getBirth())
        .email(request.getEmail())
        .build();
    return MemberDto.CreateMemberResponse.of(memberRepository.save(member));
  }

  @Override
  public MemberDto.UpdateMemberResponse updateMember(final Long id, final MemberDto.UpdateMemberRequest request) {
    Member member = getMember(id);
    member.changeRequest(request);

    return MemberDto.UpdateMemberResponse.of(member);
  }

  @Override
  public void delete(final Long id) {
    Member memberId = getMember(id);
    memberRepository.delete(memberId);
  }
}
