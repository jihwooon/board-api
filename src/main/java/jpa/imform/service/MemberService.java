package jpa.imform.service;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;

import java.util.List;

public interface MemberService {

  List<MemberDto.MemberResponse> getMembers();

  Member getMember(Long id);

  MemberDto.MemberResponse createMember(MemberDto.MemberRequest request);

  MemberDto.MemberResponse updateMember(Long id, MemberDto.MemberRequest request);

  void delete(Long id);
}
