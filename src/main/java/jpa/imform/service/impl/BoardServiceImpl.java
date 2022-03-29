package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import jpa.imform.dto.BoardDto;
import jpa.imform.error.BoardNotFoundException;
import jpa.imform.repository.BoardRepository;
import jpa.imform.service.BoardService;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final MemberService memberService;

  @Override
  public List<BoardDto.BoardResponse> getBoards(Long id) {
    Member member = memberService.getMember(id);
    return BoardDto.BoardResponse.of(boardRepository.findBoardByMember(member));
  }

  @Override
  public BoardDto.BoardResponse createBoard(Long id, BoardDto.BoardRequest request) {
    Member member = memberService.getMember(id);

    Board board = Board.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .member(member)
        .build();
    return BoardDto.BoardResponse.of(boardRepository.save(board));
  }

  @Override
  public List<Board> getBoardsByMember(Long id) {
    Member member = memberService.getMember(id);
    return boardRepository.findBoardByMember(member);
  }

  @Override
  public Board getBoard(Long id) {
    return boardRepository.findById(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
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
