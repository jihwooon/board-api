package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.dto.BoardDto;
import jpa.imform.error.BoardNotFoundException;
import jpa.imform.repository.BoardRepository;
import jpa.imform.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;

  @Override
  public List<BoardDto.BoardResponse> getBoards() {
    return BoardDto.BoardResponse.of(boardRepository.findAll());
  }

  @Override
  public BoardDto.BoardResponse getBoard(Long boardId) {
    return boardRepository.findById(boardId)
        .orElseThrow(() -> new BoardNotFoundException(boardId));
  }

  @Override
  public Board createBoard(Board board) {
    return null;
  }

  @Override
  public Board updateBoard(Long id, Board update) {
    Board board = getBoard(id);
    board.change(update);

    return board;
  }

  @Override
  public void deleteBoard(Long id) {
    Board board = getBoard(id);
    boardRepository.delete(board);
  }

//  @Override
//  public Long createBoard(BoardDto.BoardRequest request) {
//    Board board = Board.builder()
//        .id(request.getId())
//        .userId(request.getUserId())
//        .build();
//
//    return createBoard(board).getId();
//  }
}
