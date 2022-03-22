package jpa.imform.dto;

import jpa.imform.domain.Address;
import jpa.imform.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDto {

  @Data
  @AllArgsConstructor
  public static class MemberRequest {
    private Long id;
    private String name;
  }


  @Data
  public static class MemberResponse {
    private String name;
    private Integer birth;
    private String email;
    private Address address;

    public MemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.address = member.getAddress();
    }

    public static MemberResponse of(final Member member) {
      return new MemberResponse(member);
    }

    public static List<MemberResponse> of(final List<Member> member) {
      return member.stream()
          .map(o -> new MemberResponse(o))
          .collect(Collectors.toList());
    }
  }
}
