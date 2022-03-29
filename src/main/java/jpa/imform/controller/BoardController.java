package jpa.imform.controller;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import jpa.imform.dto.BoardDto;
import jpa.imform.service.BoardService;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("member/{memberId}/board")
  public List<BoardDto.BoardResponse> list(@PathVariable Long memberId) {
    return boardService.getBoards(memberId);
  }

  @PostMapping("member/{memberId}/board")
  @ResponseStatus(HttpStatus.CREATED)
  public BoardDto.BoardResponse create(@PathVariable Long memberId, @RequestBody @Valid BoardDto.BoardRequest request) {
    return boardService.createBoard(memberId, request);
  }


//  @GetMapping("/board/{id}")
//  public BoardDto.BoardResponse detail(@PathVariable Long id) {
//    return BoardDto.BoardResponse.of(boardService.getBoard(id));
//  }
//
//  @PatchMapping("board/{id}")
//  public BoardDto.BoardResponse update(@PathVariable Long id, @RequestBody @Valid BoardDto.BoardRequest update) {
//    return boardService.updateBoard(id, update);
//  }
//
//  @DeleteMapping("/board/{id}")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  public void remove(@PathVariable Long id) {
//    boardService.deleteBoard(id);
//  }
}
