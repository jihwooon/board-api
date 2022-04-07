package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.error.MemberNotFoundException;
import jpa.imform.repository.JpaRepository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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

    given(memberRepository.save(any(Member.class))).will(invocation -> {
      Member source = invocation.getArgument(0);
      return Member.builder()
          .name(source.getName())
          .password(source.getPassword())
          .email(source.getEmail())
          .build();
    });
  }

  @Test
  public void getMembers() {

    List<MemberDto.ListMemberResponse> memberResponses = memberService.getMembers();

    assertThat(memberResponses).hasSize(1);

    verify(memberRepository).findAll();
  }

  @Test
  public void getMemberExistedId() {
    Member member = memberService.getMember(1L);

    assertThat(member).isNotNull();

    assertThat(member.getName()).isEqualTo("jihwooon");

    verify(memberRepository).findById(1L);
  }

  @Test
  public void getMemberNotExistedId() {
    assertThatThrownBy(() -> memberService.getMember(100L))
        .isInstanceOf(MemberNotFoundException.class);
  }

  @Test
  public void createMember() {
    MemberDto.CreateMemberRequest request = MemberDto.CreateMemberRequest.builder()
        .name("안지환")
        .password("1234")
        .phone("010-123-2453")
        .email("jihwooon@gmail.com")
        .build();
    MemberDto.CreateMemberResponse member = memberService.createMember(request);

    assertThat(member.getName()).isEqualTo("안지환");
    assertThat(member.getEmail()).isEqualTo("jihwooon@gmail.com");
    assertThat(member.getPhone()).isEqualTo("1234");

    verify(memberRepository).save(any());
  }
}
