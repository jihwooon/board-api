package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import jpa.imform.dto.CommentDto;
import jpa.imform.error.CommentNotFoundException;
import jpa.imform.repository.CommentJpaRepository;
import jpa.imform.repository.CommentRepository;
import jpa.imform.service.BoardService;
import jpa.imform.service.CommentService;
import jpa.imform.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final CommentJpaRepository commentJpaRepository;

  private final BoardService boardService;
  private final MemberService memberService;

  @Override
  public List<CommentDto.ListCommentResponse> getComments(final Long memberId,
                                                          final Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    return CommentDto.ListCommentResponse.of(commentRepository.findByMemberAndBoard(member, board));
  }

  @Override
  public List<CommentDto.ListCommentResponse> getCommentsV1(final Long memberId,
                                                            final Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    return CommentDto.ListCommentResponse.of(commentRepository.findAllWithDevelop(member, board));
  }

  @Override
  public List<CommentDto.ListCommentResponse> getCommentsV2(final Long memberId,
                                                            final Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    return CommentDto.ListCommentResponse.of(commentJpaRepository.findAllByMemberAndBoard(member, board));
  }

  //Comment
  @Override
  public CommentDto.getCommentResponse getCommentById(final Long memberId,
                                                      final Long boardId,
                                                      final Long commentId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    Comment comment = getComment(commentId);

    return CommentDto.getCommentResponse.of(member, board, comment);
  }

  //CommentV1
  @Override
  public CommentDto.getCommentResponse getCommentByIdV1(final Long memberId,
                                                        final Long boardId,
                                                        final Long commentId) {
    Member member = memberService.getMemberV1(memberId);
    Board board = boardService.getBoardV1(boardId);
    Comment comment = getCommentV1(commentId, member, board);

    return CommentDto.getCommentResponse.of(comment);
  }

  @Override
  public CommentDto.CreateCommentResponse createComment(final Long memberId,
                                                        final Long boardId,
                                                        final CommentDto.CreateCommentRequest request) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    Comment comment = Comment.builder()
        .content(request.getContent())
        .member(member)
        .board(board)
        .build();
    return CommentDto.CreateCommentResponse.of(commentRepository.save(comment));
  }

  @Override
  public CommentDto.UpdateCommentResponse updateComment(final Long memberId,
                                                        final Long boardId,
                                                        final Long commentId,
                                                        final CommentDto.UpdateCommentRequest request) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    Comment comment = getComment(commentId);
    comment.changeRequest(member, board, comment, request);

    return CommentDto.UpdateCommentResponse.of(commentRepository.save(comment));
  }

  @Override
  public Comment deleteComment(final Long id) {
    Comment comment = getComment(id);
    commentRepository.delete(comment);
    return comment;
  }

  public Comment getComment(final Long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(id));
  }

//  @Override
//  public Comment getCommentV1(Long id) {
//    return commentRepository.findIdWithDevelop(id)
//        .orElseThrow(() -> new CommentNotFoundException(id));
//  }

  public Comment getCommentV1(final Long id, final Member member, final Board board) {
    return commentRepository.findIdWithDevelop(id, member , board)
        .orElseThrow(() -> new CommentNotFoundException(id));
  }

  public long getCount() {
    return commentRepository.findCount();
  }

//  @Override
//  public List<CommentDto.ListCommentResponse> getListCommentDto() {
//    return commentRepository.findCommentDto();
//  }
}
