package jpa.imform.service;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;

import java.util.List;

public interface MemberService {

  List<MemberDto.ListMemberResponse> getMembers();

  Member getMember(Long id);

  MemberDto.CreateMemberResponse createMember(MemberDto.CreateMemberRequest request);

  MemberDto.UpdateMemberResponse updateMember(Long id, MemberDto.UpdateMemberRequest request);

  void delete(Long id);
}
