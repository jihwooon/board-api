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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member/{memberId}")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/board")
  public List<BoardDto.CreateBoardResponse> list(@PathVariable Long memberId) {
    return boardService.getBoards(memberId);
  }

  @PostMapping("/board")
  @ResponseStatus(HttpStatus.CREATED)
  public BoardDto.CreateBoardResponse create(@PathVariable Long memberId, @RequestBody @Valid BoardDto.CreateBoardRequest request) {
    return boardService.createBoard(memberId, request);
  }

//  @GetMapping("member/{memberId}/board/{boardId}")
//  public BoardDto.CreateBoardResponse detail(@PathVariable Long memberId, @PathVariable Long boardId) {
//    return BoardDto.CreateBoardResponse.of(boardService.getBoard(memberId));
//  }
//
//  @PatchMapping("board/{id}")
//  public BoardDto.BoardResponse update(@PathVariable Long id, @RequestBody @Valid BoardDto.BoardRequest update) {
//    return boardService.updateBoard(id, update);
//  }
//
  @DeleteMapping("/board/{boardId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long memberId, @PathVariable Long boardId) {
    boardService.deleteBoard(boardId);
  }
}
