package jpa.imform.service.impl;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import jpa.imform.dto.CommentDto;
import jpa.imform.error.CommentNotFoundException;
import jpa.imform.repository.BoardRepository;
import jpa.imform.repository.CommentRepository;
import jpa.imform.repository.MemberRepository;
import jpa.imform.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;

  @Override
  public List<CommentDto.CommentResponse> getComments(Long memberId, Long boardId) {
    Member member = memberRepository.findById(memberId).get();
    Board board = boardRepository.findById(boardId).get();
    return CommentDto.CommentResponse.of(commentRepository.findByMemberAndBoard(member, board));
  }

  @Override
  public CommentDto.CommentResponse createComment(Long memberId, Long boardId, CommentDto.CommentRequest request) {
    Optional<Board> board = boardRepository.findById(boardId);
    Optional<Member> member = memberRepository.findById(memberId);
    Comment comment = Comment.builder()
        .content(request.getContent())
        .member(member.get())
        .board(board.get())
        .build();
    return CommentDto.CommentResponse.of(commentRepository.save(comment));
  }

  @Override
  public Comment getComment(Long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(id));
  }

  @Override
  public Comment updateComment(Long id, Comment source) {
    Comment comment = getComment(id);
    comment.change(source);
    return comment;
  }

  @Override
  public Comment deleteComment(Long id) {
    Comment comment = getComment(id);
    commentRepository.delete(comment);
    return comment;
  }
}
