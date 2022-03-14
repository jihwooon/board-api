package jpa.imform.controller;

import jpa.imform.domain.Board;
import jpa.imform.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

  private final BoardService boardService;

  @GetMapping
  public List<Board> list() {
    return boardService.getBoards();
  }

  @GetMapping("{id}")
  public Board detail(@PathVariable Long id) {
    return boardService.getBoard(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Board create(@PathVariable Long id, @RequestBody Board board) {
    return boardService.createBoard(board);
  }

  @PatchMapping("{id}")
  public Board update(@PathVariable Long id, @RequestBody Board source) {
    return boardService.updateBoard(id, source);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void destory(@PathVariable Long id) {
    boardService.deleteBoard(id);
  }
}
