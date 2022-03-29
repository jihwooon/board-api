package jpa.imform.dto;

import jpa.imform.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDto {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class MemberRequest {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 4, max = 1024)
    private String password;

    @NotBlank(message = "생년월일을 입력해주세요")
    private String birth;

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
  }

  @Data
  public static class MemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.BoardResponse> boards;

    public MemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.BoardResponse.of(member.getBoards());
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
