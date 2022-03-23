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
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  public List<MemberDto.MemberResponse> getMembers() {
    return MemberDto.MemberResponse.of(memberRepository.findAll());
  }

  public Member getMember(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException("Id"));
  }

  @Override
  public MemberDto.MemberResponse createMember(MemberDto.MemberRequest request) {
    Member member = Member.builder()
        .id(request.getId())
        .name(request.getName())
        .build();
    return MemberDto.MemberResponse.of(memberRepository.save(member));
  }

  @Override
  public MemberDto.MemberResponse updateMember(Long id, MemberDto.MemberRequest request) {
    Member member = getMember(id);
    member.changeWith(request);

    return MemberDto.MemberResponse.of(member);
  }

  @Override
  public void delete(Long id) {
    Member memberId = getMember(id);
    memberRepository.delete(memberId);
  }
}