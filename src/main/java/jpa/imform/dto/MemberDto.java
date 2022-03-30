package jpa.imform.dto;

import jpa.imform.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;


public class MemberDto {

  @Getter
  public static class ListMemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.ListBoardResponse> boards;
    private List<CommentDto.ListCommentResponse> comments;

    public ListMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.ListBoardResponse.of(member.getBoards());
      this.comments = CommentDto.ListCommentResponse.of(member.getComments());
    }

    public static List<ListMemberResponse> of(final List<Member> member) {
      return member.stream()
          .map(o -> new ListMemberResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
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

  @Getter
  @Setter
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

  @Getter
  public static class CreateMemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.CreateBoardResponse> boards;

    public CreateMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.CreateBoardResponse.of(member.getBoards());
    }

    public static CreateMemberResponse of(final Member member) {
      return new CreateMemberResponse(member);
    }
  }

  @Getter
  @Setter
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

  @Getter
  public static class UpdateMemberResponse {
    private String name;
    private String birth;
    private String email;
    private List<BoardDto.CreateBoardResponse> boards;

    public UpdateMemberResponse(Member member) {
      this.name = member.getName();
      this.birth = member.getBirth();
      this.email = member.getEmail();
      this.boards = BoardDto.UpdateBoardResponse.of(member.getBoards());
    }

    public static UpdateMemberResponse of(final Member member) {
      return new UpdateMemberResponse(member);
    }
  }
}
