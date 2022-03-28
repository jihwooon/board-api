package jpa.imform.dto;

import jpa.imform.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

  @Data
  @AllArgsConstructor
  public static class BoardRequest {
    @NotNull(message = "id 값은 필수 값입니다.")
    private Long id;

    private String content;

    @NotBlank(message = "값을 채워 주세요.")
    private String title;
  }

  @Getter
  public static class BoardResponse {
    private Long id;
    private String content;
    private String title;

    public BoardResponse(Board board) {
      this.id = board.getId();
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
