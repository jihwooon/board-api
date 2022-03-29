package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import jpa.imform.dto.BoardDto;
import jpa.imform.error.BoardNotFoundException;
import jpa.imform.repository.BoardRepository;
import jpa.imform.repository.MemberRepository;
import jpa.imform.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;

  @Override
  public List<Board> getBoardsByMember(Long id) {
    Member member = memberRepository.findById(id).get();
    return boardRepository.findBoardByMember(member);
  }

  @Override
  public List<BoardDto.BoardResponse> getBoards(Long id) {
    Member member = memberRepository.findById(id).get();
    return BoardDto.BoardResponse.of(boardRepository.findBoardByMember(member));
  }

  @Override
  public Board getBoard(Long id) {
    return boardRepository.findById(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
  }

//  @Override
//  public Board createdBoard(Long id, Board board) {
//    Optional<Member> MemberId = memberRepository.findById(id);
//    board.setMember(MemberId.get());
//    return boardRepository.save(board);
//  }

  @Override
  public BoardDto.BoardResponse createBoard(Long id, BoardDto.BoardRequest request) {
    Optional<Member> memberId = memberRepository.findById(id);
    Board board = Board.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .member(memberId.get())
        .build();
    return BoardDto.BoardResponse.of(boardRepository.save(board));
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
