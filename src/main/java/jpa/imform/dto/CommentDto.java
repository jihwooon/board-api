package jpa.imform.dto;

import jpa.imform.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

  @Data
  @AllArgsConstructor
  public static class CommentRequest {

    @NotNull(message = "id는 필수 값입니다.")
    private Long id;
    @NotEmpty()
    private String name;
  }

  @Data
  public static class CommentResponse {
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public CommentResponse(Comment comment) {
      this.content = comment.getContent();
      this.createDate = comment.getCreateDate();
      this.modifiedDate = comment.getModifiedDate();
    }

    public static CommentResponse of(Comment comment) {
      return new CommentResponse(comment);
    }

    public static List<CommentResponse> of(List<Comment> comment) {
      return comment.stream()
          .map(o -> new CommentResponse(o))
          .collect(Collectors.toList());
    }

  }
}
