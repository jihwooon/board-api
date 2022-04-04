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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  //BoardRepository Interface
  @GetMapping("member/{memberId}/board")
  public List<BoardDto.ListBoardResponse> list(@PathVariable final Long memberId) {
    return boardService.getBoards(memberId);
  }

  //BoardJpaRepository
  @GetMapping("member/{memberId}/boardV2")
  public List<BoardDto.ListBoardResponse> listV2(@PathVariable final Long memberId) {
    return boardService.getBoardsV2(memberId);
  }

  @PostMapping("member/{memberId}/board")
  @ResponseStatus(HttpStatus.CREATED)
  public BoardDto.CreateBoardResponse create(@PathVariable final Long memberId,
                                             @RequestBody @Valid final BoardDto.CreateBoardRequest create) {
    return boardService.createBoard(memberId, create);
  }

  @GetMapping("member/{memberId}/board/{boardId}")
  public BoardDto.getBoardResponse detail(@PathVariable final Long memberId,
                                          @PathVariable final Long boardId) {
    return boardService.getBoardByIdAndMemberId(memberId, boardId);
  }

  @PatchMapping("member/{memberId}/board/{boardId}")
  public BoardDto.UpdateBoardResponse update(@PathVariable final Long memberId,
                                             @PathVariable final Long boardId,
                                             @RequestBody @Valid final BoardDto.UpdateBoardRequest update) {
    return boardService.updateBoard(memberId, boardId, update);
  }

  @DeleteMapping("/board/{boardId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable final Long boardId) {
    boardService.remove(boardId);
  }
}
