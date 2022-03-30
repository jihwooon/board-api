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
  public static class ListMemberRequest {
    @NotNull
    private Long id;
  }

  @Data
  public static class ListMemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.BoardResponse> boards;

    public ListMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.BoardResponse.of(member.getBoards());
    }

    public static List<ListMemberResponse> of(final List<Member> member) {
      return member.stream()
          .map(o -> new ListMemberResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class DetailMemberRequest {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

  }

  @Data
  public static class DetailMemberResponse {
    private String name;
    private String birth;
    private String email;

    public DetailMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
    }

    public static DetailMemberResponse of(final Member member) {
      return new DetailMemberResponse(member);
    }
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CreateMemberRequest {

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
  public static class CreateMemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.BoardResponse> boards;

    public CreateMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.BoardResponse.of(member.getBoards());
    }

    public static CreateMemberResponse of(final Member member) {
      return new CreateMemberResponse(member);
    }

    public static List<CreateMemberResponse> of(final List<Member> member) {
      return member.stream()
          .map(o -> new CreateMemberResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UpdateMemberRequest {

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
  public static class UpdateMemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.BoardResponse> boards;

    public UpdateMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.BoardResponse.of(member.getBoards());
    }

    public static UpdateMemberResponse of(final Member member) {
      return new UpdateMemberResponse(member);
    }

    public static List<UpdateMemberResponse> of(final List<Member> member) {
      return member.stream()
          .map(o -> new UpdateMemberResponse(o))
          .collect(Collectors.toList());
    }
  }

}
