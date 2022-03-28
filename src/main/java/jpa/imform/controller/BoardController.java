package jpa.imform.controller;

import jpa.imform.dto.BoardDto;
import jpa.imform.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping
  public List<BoardDto.BoardResponse> list() {
    return boardService.getBoards();
  }

  @GetMapping("{id}")
  public BoardDto.BoardResponse detail(@PathVariable Long id) {
    return BoardDto.BoardResponse.of(boardService.getBoard(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BoardDto.BoardResponse create(@RequestBody @Valid BoardDto.BoardRequest request) {
    return boardService.createBoard(request);
  }

  @PatchMapping("{id}")
  public BoardDto.BoardResponse update(@PathVariable Long id, @RequestBody @Valid BoardDto.BoardRequest update) {
    return boardService.updateBoard(id, update);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long id) {
    boardService.deleteBoard(id);
  }

}
