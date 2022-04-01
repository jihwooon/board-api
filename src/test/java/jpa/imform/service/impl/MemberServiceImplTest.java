package jpa.imform.service.impl;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberDto;
import jpa.imform.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

  @InjectMocks
  private MemberServiceImpl memberService;

  @Mock
  private MemberRepository memberRepository;

  @BeforeEach
  public void setUp() {

    memberRepository = mock(MemberRepository.class);
    memberService = new MemberServiceImpl(memberRepository);
  }

  @Test
  public void getMembers() {

    List<MemberDto.ListMemberResponse> memberResponses = memberService.getMembers();

    given(memberRepository.findAll()).willReturn(List.of());

    assertThat(memberResponses).hasSize(0);

    verify(memberRepository).findAll();
  }

}
