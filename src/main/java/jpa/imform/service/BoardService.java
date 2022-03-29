package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;

import java.util.List;

public interface BoardService {

  List<BoardDto.CreateBoardResponse> getBoards(Long id);

  BoardDto.CreateBoardResponse createBoard(Long id, BoardDto.CreateBoardRequest request);

  Board getBoard(Long id);

  BoardDto.BoardResponse updateBoard(Long id, BoardDto.BoardRequest update);

  void deleteBoard(Long id);

  List<Board> getBoardsByMember(Long id);
}
