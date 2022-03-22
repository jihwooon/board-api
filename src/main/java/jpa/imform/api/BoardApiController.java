package jpa.imform.api;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;
import jpa.imform.repository.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board/api")
@RestController
public class BoardApiController {

  private final BoardRepository boardRepository;

  public BoardApiController(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  @GetMapping
  public List<BoardDto.BoardResponse> list() {
    List<Board> board = boardRepository.findAll();
    List<BoardDto.BoardResponse> result = board.stream()
    .map(o -> new BoardDto.BoardResponse(o))
    .collect(Collectors.toList());

    return result;
  }

}
