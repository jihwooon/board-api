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
  public Board getBoard(Long id) {
    return boardRepository.findById(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
  }

  @Override
  public BoardDto.BoardResponse createBoard(BoardDto.BoardRequest request) {
    Board board = Board.builder()
        .id(request.getId())
        .title(request.getTitle())
        .content(request.getContent())
        .build();
    return BoardDto.BoardResponse.of(board);
  }

  @Override
  public BoardDto.BoardResponse updateBoard(Long id, BoardDto.BoardRequest update) {
    Board board = getBoard(id);
    board.changeWith(update);

    return BoardDto.BoardResponse.of(board);
  }

  @Override
  public void deleteBoard(Long id) {
    Board board = getBoard(id);
    boardRepository.delete(board);
  }
}
