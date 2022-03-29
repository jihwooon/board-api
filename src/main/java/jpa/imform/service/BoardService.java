package jpa.imform.service;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;

import java.util.List;

public interface BoardService {

  List<BoardDto.BoardResponse> getBoards(Long id);

  Board getBoard(Long id);

//  Board createdBoard(Long id,Board board);

  BoardDto.BoardResponse createBoard(Long id, BoardDto.BoardRequest request);

  BoardDto.BoardResponse updateBoard(Long id, BoardDto.BoardRequest update);

  void deleteBoard(Long id);

  List<Board> getBoardsByMember(Long id);
}
