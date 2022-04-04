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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  @Autowired
  private final CommentJpaRepository commentJpaRepository;

  private final BoardService boardService;
  private final MemberService memberService;

  @Override
  public List<CommentDto.ListCommentResponse> getComments(final Long memberId,
                                                          final Long boardId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    return CommentDto.ListCommentResponse.of(commentJpaRepository.findAllByMemberAndBoard(member, board));
    //commentRepository.findAllWithDevelop(member, board)
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
  public CommentDto.getCommentResponse getCommentById(final Long memberId,
                                                      final Long boardId,
                                                      final Long commentId) {
    Member member = memberService.getMember(memberId);
    Board board = boardService.getBoard(boardId);
    Comment comment = getComment(commentId);

    return CommentDto.getCommentResponse.of(member, board,comment);
  }

  @Override
  public Comment deleteComment(final Long id) {
    Comment comment = getComment(id);
    commentRepository.delete(comment);
    return comment;
  }

  @Override
  public Comment getComment(final Long id) {
    return commentRepository.findIdWithDevelop(id);
//        .orElseThrow(() -> new CommentNotFoundException(id));
  }
}
