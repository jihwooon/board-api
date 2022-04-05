package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import jpa.imform.dto.BoardDto;
import jpa.imform.error.BoardNotFoundException;
import jpa.imform.repository.BoardJpaRepository;
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
  private final BoardJpaRepository boardJpaRepository;

  private final MemberService memberService;

  @Override
  public List<BoardDto.ListBoardResponse> getBoards(final Long memberId) {
    Member member = memberService.getMember(memberId);

    return BoardDto.ListBoardResponse.of(boardRepository.findBoardByMember(member));
  }

  @Override
  public List<BoardDto.ListBoardResponse> getBoardsV1(final Long memberId) {
    Member member = memberService.getMember(memberId);

    return BoardDto.ListBoardResponse.of(boardRepository.findAllWithDevelop(member));
  }

  @Override
  public List<BoardDto.ListBoardResponse> getBoardsV2(final Long memberId) {
    Member member = memberService.getMember(memberId);

    return BoardDto.ListBoardResponse.of(boardJpaRepository.findAllByMember(member));
  }

  @Override
  public BoardDto.getBoardResponse getBoardByIdAndMemberId(final Long memberId,
                                                           final Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = getBoard(boardId);

    return BoardDto.getBoardResponse.of(member, board);
  }

  @Override
  public BoardDto.getBoardResponse getBoardByIdAndMemberIdV2(final Long memberId,
                                                             final Long boardId) {
    Member member = memberService.getMemberV2(memberId); // 추후 V2 버전으로 변경할 예정
    Board board = getBoardV2(boardId);

    return BoardDto.getBoardResponse.of(member, board);
  }

  @Override
  public BoardDto.CreateBoardResponse createBoard(final Long id,
                                                  final BoardDto.CreateBoardRequest request) {
    Member member = memberService.getMember(id);
    Board board = Board.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .member(member)
        .build();

    return BoardDto.CreateBoardResponse.of(boardRepository.save(board));
  }

  @Override
  public BoardDto.UpdateBoardResponse updateBoard(final Long memberId,
                                                  final Long boardId,
                                                  final BoardDto.UpdateBoardRequest update) {
    Member member = memberService.getMember(memberId);
    Board board = getBoard(boardId);
    board.changeRequest(member, update);

    return BoardDto.UpdateBoardResponse.of(boardRepository.save(board));
  }

  @Override
  public void remove(final Long id) {
    Board boardId = getBoard(id);
    boardRepository.delete(boardId);
  }

  public Board getBoard(final Long id) {
    return boardRepository.findIdWithDevelop(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
  }

  public Board getBoardV2(final Long id) {
    return boardRepository.findById(id)
        .orElseThrow(() -> new BoardNotFoundException(id));
  }

}
