package jpa.imform.api;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;
import jpa.imform.error.BoardNotFoundException;
import jpa.imform.repository.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board")
@RestController
public class BoardApiController {

  private final BoardRepository boardRepository;

  public BoardApiController(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  @GetMapping("/api")
  public List<BoardDto.BoardResponse> list() {
    List<Board> boards = boardRepository.findAll();
    List<BoardDto.BoardResponse> result = boards.stream()
        .map(o -> new BoardDto.BoardResponse(o))
        .collect(Collectors.toList());

    return result;
  }

  @GetMapping("/api/{id}")
  public BoardDto.BoardResponse detail(@PathVariable("id") Long id) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
    return BoardDto.BoardResponse.of(board);
  }
}
