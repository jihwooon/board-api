package jpa.imform.service;

import jpa.imform.error.BoardNotFoundException;
import jpa.imform.domain.Board;
import jpa.imform.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public List<Board> getBoards() {
   return boardRepository.findAll();
  }

  public Board getBoard(Long id) {
      return boardRepository.findById(id)
          .orElseThrow(() -> new BoardNotFoundException(id));
  }

  public Board createBoard(Board board) {
    return boardRepository.save(board);
  }

  public Board updateBoard(Long id, Board update) {
    Board board = getBoard(id);
    board.change(update);

    return board;
  }

  public Board deleteBoard(Long id) {
    Board board = getBoard(id);
    boardRepository.delete(board);
    return board;
  }
}
