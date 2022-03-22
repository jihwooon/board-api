package jpa.imform.service;

import jpa.imform.domain.Member;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public List<Member> getMembers() {
    return memberRepository.findAll();
  }

  public Member getMember(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException(id));
  }

  public Member createMember(Member member) {
    return memberRepository.save(member);
  }

  public Member updateMember(Long id, Member source) {
    Member member = getMember(id);
    member.change(source);
    return member;
  }

  public Member delete(Long id) {
    Member memberId = getMember(id);
    memberRepository.delete(memberId);
    return memberId;
  }
}
