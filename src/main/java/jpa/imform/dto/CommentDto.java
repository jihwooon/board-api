package jpa.imform.dto;

import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CommentRequest {

    @NotEmpty(message = "이름을 기입 해주세요")
    private String name;
    private String content;
  }

  @Data
  public static class CommentResponse {
    private String name;
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
