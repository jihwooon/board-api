//TODO : 엔티티 클래스 -> DTO 변환
// list DTO 변환 완료
// detail DTO 변환 완료
// create DTO 변화 진행
// update DTO
// delete DTO

package jpa.imform.controller;

import jpa.imform.dto.BoardDto;
import jpa.imform.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
  public BoardDto.BoardResponse create(@RequestBody BoardDto.BoardRequest request) {
    return boardService.createBoard(request);
  }

  @PatchMapping("{id}")
  public BoardDto.BoardResponse update(@PathVariable Long id, @RequestBody BoardDto.BoardRequest update) {
    return boardService.updateBoard(id, update);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long id) {
    boardService.deleteBoard(id);
  }

}
