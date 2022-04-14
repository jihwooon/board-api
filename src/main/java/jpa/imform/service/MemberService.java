package jpa.imform.service;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.dto.MemberSimpleDto;

import java.util.List;

public interface MemberService {

  List<MemberDto.ListMemberResponse> getMembers();

  List<MemberDto.ListMemberResponse> getMembersV1();

  List<MemberDto.ListMemberResponse> getMembersV2();

  Member getMember(Long id);

  Member getMemberV1(Long id);

  MemberDto.CreateMemberResponse createMember(MemberDto.CreateMemberRequest request);

  MemberDto.UpdateMemberResponse updateMember(Long id, MemberDto.UpdateMemberRequest request);

  Member delete(Long id);

  long getMemberCount();

  long getMemberCountV1();

  List<MemberSimpleDto> getSimpleDto();

}
