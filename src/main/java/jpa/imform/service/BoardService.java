package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;

import java.util.List;

public interface BoardService {

  List<BoardDto.BoardResponse> getBoards();

  Board getBoard(Long id);

  Board createBoard(BoardDto.BoardRequest request);

  BoardDto.BoardResponse updateBoard(Long id, BoardDto.BoardRequest update);

  void deleteBoard(Long id);
}
