package jpa.imform.dto;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BoardRequest {

    @NotBlank(message = "값을 채워 주세요.")
    private String content;

    @NotBlank(message = "값을 채워 주세요.")
    private String title;
  }

  @Getter
  public static class BoardResponse {
    private String content;
    private String title;

    public BoardResponse(Board board) {
      this.content = board.getContent();
      this.title = board.getTitle();
    }

    public static BoardResponse of(final Board board) {
      return new BoardResponse(board);
    }

    public static List<BoardResponse> of(final List<Board> board) {
      return board.stream()
          .map(o -> new BoardResponse(o))
          .collect(Collectors.toList());
    }

  }
}
