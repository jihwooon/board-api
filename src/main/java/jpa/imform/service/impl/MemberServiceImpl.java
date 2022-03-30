package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.repository.MemberRepository;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  public List<MemberDto.ListMemberResponse> getMembers() {
    return MemberDto.ListMemberResponse.of(memberRepository.findAll());
  }

  public Member getMember(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException("id값을 다시 반환 해주세요"));
  }

  @Override
  public MemberDto.CreateMemberResponse createMember(MemberDto.CreateMemberRequest request) {
    Member member = Member.builder()
        .name(request.getName())
        .password(request.getPassword())
        .birth(request.getBirth())
        .email(request.getEmail())
        .build();
    return MemberDto.CreateMemberResponse.of(memberRepository.save(member));
  }

  @Override
  public MemberDto.UpdateMemberResponse updateMember(Long id, MemberDto.UpdateMemberRequest request) {
    Member member = getMember(id);
    member.changeRequest(request);

    return MemberDto.UpdateMemberResponse.of(member);
  }

  @Override
  public void delete(Long id) {
    Member memberId = getMember(id);
    memberRepository.delete(memberId);
  }
}
