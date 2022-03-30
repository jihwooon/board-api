package jpa.imform.api;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;
import jpa.imform.repository.BoardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardApiController {

  private final BoardRepository boardRepository;

  public BoardApiController(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  @GetMapping("/board/api")
  public List<BoardDto.ListBoardResponse> list() {
    List<Board> boards = boardRepository.findAll();
    List<BoardDto.ListBoardResponse> result = boards.stream()
        .map(o -> new BoardDto.ListBoardResponse(o))
        .collect(Collectors.toList());

    return result;
  }
}
