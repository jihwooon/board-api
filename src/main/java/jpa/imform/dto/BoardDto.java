package jpa.imform.dto;

import jpa.imform.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BoardDto {

  @Data
  @AllArgsConstructor
  public static class BoardRequest {
    private Long id;
    private String userId;
  }

  @Data
  public static class BoardResponse {

    private Long id;
    private String userId;
    private String content;
    private String title;

    public BoardResponse(Board board) {
      this.id = board.getId();
      this.userId = board.getUserId();
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

  @Data
  public static class BoardListResponse {
    private Long id;
    private String userId;

    public BoardListResponse(Board board) {
      this.id = id;
      this.userId = userId;
    }

    public static BoardListResponse of(Board board) {
      return new BoardListResponse(board);
    }

    public static List<BoardListResponse> of(List<Board> boards) {
      return boards.stream()
          .map(BoardListResponse::of)
          .collect(Collectors.toList());
    }

  }

}
