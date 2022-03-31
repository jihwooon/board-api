package jpa.imform.dto;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

  @Getter
  public static class ListBoardResponse {
    private String content;
    private String title;
    private List<CommentDto.ListCommentResponse> comments;

    public ListBoardResponse(Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
      this.comments = CommentDto.ListCommentResponse.of(board.getComments());
    }

    public static List<ListBoardResponse> of(final List<Board> board) {
      return board.stream()
          .map(o -> new ListBoardResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  public static class getBoardResponse {
    private String content;
    private String title;

    public getBoardResponse(final Member member, final Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
    }

    public static getBoardResponse of(final Member member, final Board board) {
      return new getBoardResponse(member, board);
    }
  }

  @Getter
  @Setter
  public static class CreateBoardRequest {
    @NotBlank(message = "내용은 필수 입니다.")
    private String content;

    @NotBlank(message = "제목을 입력 해주세요")
    private String title;
  }

  @Getter
  public static class CreateBoardResponse {
    private String content;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public CreateBoardResponse(Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
      this.createDate = board.getCreateDate();
      this.modifiedDate = board.getModifiedDate();
    }

    public CreateBoardResponse(Member member, Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
      this.createDate = board.getCreateDate();
      this.modifiedDate = board.getModifiedDate();
    }

    public static CreateBoardResponse of(final Board board) {
      return new CreateBoardResponse(board);
    }

    public static List<CreateBoardResponse> of(final List<Board> board) {
      return board.stream()
          .map(o -> new CreateBoardResponse(o))
          .collect(Collectors.toList());
    }

    public static CreateBoardResponse of(final Member member, final Board board) {
      return new CreateBoardResponse(member, board);
    }
  }

  @Getter
  public static class UpdateBoardRequest {
    @NotBlank(message = "내용은 필수 입니다.")
    private String content;

    @NotBlank(message = "제목을 입력 해주세요")
    private String title;
  }

  @Getter
  public static class UpdateBoardResponse {
    private String content;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private Member member;

    public UpdateBoardResponse(Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
      this.createDate = board.getCreateDate();
      this.modifiedDate = board.getModifiedDate();
    }

    public UpdateBoardResponse(Member member, Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
      this.createDate = board.getCreateDate();
      this.modifiedDate = board.getModifiedDate();
    }

    public static UpdateBoardResponse of(final Board board) {
      return new UpdateBoardResponse(board);
    }

    public static List<CreateBoardResponse> of(final List<Board> board) {
      return board.stream()
          .map(o -> new CreateBoardResponse(o))
          .collect(Collectors.toList());
    }

    public static UpdateBoardResponse of(final Member member, final Board board) {
      return new UpdateBoardResponse(member, board);
    }
  }

}
