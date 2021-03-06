package jpa.imform.api;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;
import jpa.imform.dto.BoardSimpleDto;
import jpa.imform.repository.JpaRepository.BoardRepository;
import jpa.imform.service.BoardService;
import jpa.imform.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

  private final BoardRepository boardRepository;
  private final BoardService boardService;
  private final AuthenticationService authenticationService;

  @GetMapping("/board/api/v1")
  public List<BoardDto.ListBoardResponse> listV1() {
    List<Board> boards = boardRepository.findAll();
    List<BoardDto.ListBoardResponse> result = boards.stream()
        .map(o -> new BoardDto.ListBoardResponse(o))
        .collect(Collectors.toList());

    return result;
  }

  @GetMapping("/board/api/v2")
  public List<BoardSimpleDto> listV2() {
    return boardRepository.findBoardDto();
  }

  @GetMapping("memberV1/{memberId}/boardV1")
  public List<BoardDto.ListBoardResponse> listV3(@PathVariable final Long memberId) {
    return boardService.getBoardsV1(memberId);
  }

  @GetMapping("memberV2/{memberId}/boardV2")
  public List<BoardDto.ListBoardResponse> listV4(@PathVariable final Long memberId) {
    return boardService.getBoardsV2(memberId);
  }

  @GetMapping("memberV1/{memberId}/boardV1/{boardId}")
  public BoardDto.getBoardResponse detailV1(@PathVariable final Long memberId,
                                            @PathVariable final Long boardId) {
    return boardService.getBoardByIdAndMemberIdV1(memberId, boardId);
  }

}
