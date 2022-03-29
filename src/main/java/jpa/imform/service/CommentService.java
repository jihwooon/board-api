package jpa.imform.service;

import jpa.imform.domain.Comment;
import jpa.imform.dto.CommentDto;

import java.util.List;

public interface CommentService {

  List<CommentDto.CommentResponse> getComments(Long memberId, Long boardId);

  Comment getComment(Long id);

  CommentDto.CommentResponse createComment(Long memberId, Long boardId, CommentDto.CommentRequest request);

  Comment updateComment(Long id, Comment source);

  Comment deleteComment(Long id);
}
