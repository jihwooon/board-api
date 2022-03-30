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

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;

  private final MemberService memberService;

  @Override
    public List<BoardDto.ListBoardResponse> getBoards(Long memberId) {
    Member member = memberService.getMember(memberId);
    return BoardDto.ListBoardResponse.of(boardRepository.findBoardByMember(member));
  }

  @Override
  public BoardDto.CreateBoardResponse createBoard(Long id, BoardDto.CreateBoardRequest request) {
    Member member = memberService.getMember(id);
    Board board = Board.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .member(member)
        .build();
    return BoardDto.CreateBoardResponse.of(boardRepository.save(board));
  }

  @Override
  public BoardDto.UpdateBoardResponse updateBoard(Long memberId, Long boardId, BoardDto.UpdateBoardRequest update) {
    Member member = memberService.getMember(memberId);
    Board board = getBoard(boardId);
    board.changeRequest(member, update);

    return BoardDto.UpdateBoardResponse.of(board);
  }

  @Override
  public BoardDto.getBoardResponse getBoardByIdAndMemberId(Long memberId, Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = getBoard(boardId);

    return BoardDto.getBoardResponse.of(member, board);
  }

  @Override
  public void deleteBoard(Long id) {
    Board board = getBoard(id);
    boardRepository.delete(board);
  }

  @Override
  public List<Board> getBoardsByMember(Long id) {
    Member member = memberService.getMember(id);
    return boardRepository.findBoardByMember(member);
  }

  public Board getBoard(Long id) {
    return boardRepository.findById(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
  }
}
