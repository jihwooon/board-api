package jpa.imform.dto;

import jpa.imform.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDto {

  @Data
  @AllArgsConstructor
  public static class CommentRequest {
    private Long id;
    private String name;
  }

  @Data
  public static class CommentResponse {
    private String content;
    private LocalDate saveDate;
    private LocalDate updateDate;

    public CommentResponse(Comment comment) {
      this.content = comment.getContent();
      this.saveDate = comment.getSaveDate();
      this.updateDate = comment.getUpdateDate();
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
