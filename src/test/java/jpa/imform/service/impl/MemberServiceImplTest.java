package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MemberServiceImplTest {
  private MemberServiceImpl memberService;

  private final MemberRepository memberRepository = mock(MemberRepository.class);

  @BeforeEach
  public void setUp() {
    memberService = new MemberServiceImpl(memberRepository);

    Member member = Member.builder()
        .name("jihwooon")
        .password("1234")
        .email("jihwooon@gmail.com")
        .build();
    given(memberRepository.findAll()).willReturn(List.of(member));
    given(memberRepository.findById(1L)).willReturn(Optional.of(member));

  }

  @Test
  public void getMembers() {

    List<MemberDto.ListMemberResponse> memberResponses = memberService.getMembers();

    assertThat(memberResponses).hasSize(1);

    verify(memberRepository).findAll();
  }

  @Test
  public void getMember() {
    Member member = memberService.getMember(1L);

    assertThat(member).isNotNull();

    assertThat(member.getName()).isEqualTo("jihwooon");

    verify(memberRepository).findById(1L);
  }
}
