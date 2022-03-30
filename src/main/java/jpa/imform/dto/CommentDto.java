package jpa.imform.dto;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

  @Getter
  public static class ListCommentResponse {
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public ListCommentResponse(Comment comment) {
      this.content = comment.getContent();
      this.createDate = comment.getCreateDate();
      this.modifiedDate = comment.getModifiedDate();
    }

    public static List<ListCommentResponse> of(List<Comment> comment) {
      return comment.stream()
          .map(o -> new ListCommentResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  public static class getCommentResponse {
    private String name;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


    public getCommentResponse(Member member, Board board, Comment comment) {
      this.content = comment.getContent();
      this.createDate = comment.getCreateDate();
      this.modifiedDate = comment.getModifiedDate();
    }

    public static getCommentResponse of(Member member, Board board, Comment comment) {
      return new getCommentResponse(member, board, comment);
    }
  }

  @Getter @Setter
  public static class CreateCommentRequest {

    @NotEmpty(message = "이름을 기입 해주세요")
    private String name;
    @NotEmpty(message = "내용을 기입 해주세요")
    private String content;
  }

  @Getter
  public static class CreateCommentResponse {
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public CreateCommentResponse(Comment comment) {
      this.content = comment.getContent();
      this.createDate = comment.getCreateDate();
      this.modifiedDate = comment.getModifiedDate();
    }

    public static CreateCommentResponse of(Comment comment) {
      return new CreateCommentResponse(comment);
    }

    public static List<CreateCommentResponse> of(List<Comment> comment) {
      return comment.stream()
          .map(o -> new CreateCommentResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter @Setter
  public static class UpdateCommentRequest {

    @NotEmpty(message = "내용을 채워주세요")
    private String content;
  }

  @Getter
  public static class UpdateCommentResponse {
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public UpdateCommentResponse(Comment comment) {
      this.content = comment.getContent();
      this.createDate = comment.getCreateDate();
      this.modifiedDate = comment.getModifiedDate();
    }

    public static UpdateCommentResponse of(Comment comment) {
      return new UpdateCommentResponse(comment);
    }

    public static List<UpdateCommentResponse> of(List<Comment> comment) {
      return comment.stream()
          .map(o -> new UpdateCommentResponse(o))
          .collect(Collectors.toList());
    }
  }

}
