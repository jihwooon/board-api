package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;

import java.util.List;

public interface BoardService {

  List<BoardDto.CreateBoardResponse> getBoards(Long memberId);

  BoardDto.CreateBoardResponse createBoard(Long memberId, BoardDto.CreateBoardRequest request);

  BoardDto.CreateBoardResponse updateBoard(Long memberId, Long boardId, BoardDto.CreateBoardRequest update);

  Board getBoard(Long id);

  void deleteBoard(Long id);

  List<Board> getBoardsByMember(Long id);
}
