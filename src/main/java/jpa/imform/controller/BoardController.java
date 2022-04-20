//TODO : 회원 일 경우 게시판
// list : 회원 인 경우만 볼 수 있는 경우
// detail : 특정 회원만 조회
// create : 회원 일 경우 글 등록 가능
// update : 회원 일 경우 글 수정 가능
// delete : 회원 일 경우 글 삭제 가능

//TODO : 비회원일 경우 게시판 조회 (쇼핑몰 기준)
// list : 회원/비회원 구분 없이 상품 정보
package jpa.imform.controller;

import jpa.imform.dto.BoardDto;
import jpa.imform.service.AuthenticationService;
import jpa.imform.service.MemberBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final MemberBoardService memberBoardService;
  private final AuthenticationService authenticationService;

  @GetMapping("members/{memberId}/boards")
  public List<BoardDto.ListBoardResponse> list(@PathVariable final Long memberId) {

    return memberBoardService.getBoards(memberId);
  }

  @GetMapping("members/{memberId}/boards/{boardId}")
  public BoardDto.getBoardResponse detail(@PathVariable final Long memberId,
                                          @PathVariable final Long boardId) {
    return memberBoardService.getBoardByIdAndMemberId(memberId, boardId);
  }

  @PostMapping("members/{memberId}/boards")
  @ResponseStatus(HttpStatus.CREATED)
  public BoardDto.CreateBoardResponse create(
      @RequestHeader("Authorization") String authorization,
      @PathVariable final Long memberId,
      @RequestBody @Valid final BoardDto.CreateBoardRequest create) {

    String accessToken = authorization.substring("Bearer ".length());
    Long userId = authenticationService.parseToken(accessToken);

    return memberBoardService.createBoard(memberId, create);
  }

  @PatchMapping("members/{memberId}/boards/{boardId}")
  public BoardDto.UpdateBoardResponse update(@PathVariable final Long memberId,
                                             @PathVariable final Long boardId,
                                             @RequestBody @Valid final BoardDto.UpdateBoardRequest update) {
    return memberBoardService.updateBoard(memberId, boardId, update);
  }

  @DeleteMapping("/board/{boardId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable final Long boardId) {
    memberBoardService.remove(boardId);
  }

}
