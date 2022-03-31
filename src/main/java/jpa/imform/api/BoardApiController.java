package jpa.imform.api;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;
import jpa.imform.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

  private final BoardRepository boardRepository;

  @GetMapping("/board/api/v1")
  public List<BoardDto.ListBoardResponse> listV1() {
    List<Board> boards = boardRepository.findAll();
    List<BoardDto.ListBoardResponse> result = boards.stream()
        .map(o -> new BoardDto.ListBoardResponse(o))
        .collect(Collectors.toList());

    return result;
  }

}
