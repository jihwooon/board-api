package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;

import java.util.List;

public interface BoardService {

  List<BoardDto.BoardResponse> getBoards();

  BoardDto.BoardResponse getBoard(Long id);
//  Board getBoard(Long id);

  Board createBoard(Board board);

  Board updateBoard(Long id, Board update);

  void deleteBoard(Long id);
}
