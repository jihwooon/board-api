package jpa.imform.service;

import jpa.imform.domain.Comment;
import jpa.imform.dto.CommentDto;

import java.util.List;

public interface CommentService {

  List<CommentDto.ListCommentResponse> getComments(Long memberId, Long boardId);

  List<CommentDto.ListCommentResponse> getCommentsV2(Long memberId, Long boardId);

  CommentDto.CreateCommentResponse createComment(Long memberId, Long boardId, CommentDto.CreateCommentRequest request);

  CommentDto.UpdateCommentResponse updateComment(Long memberId, Long boardId, Long commentId, CommentDto.UpdateCommentRequest request);

  CommentDto.getCommentResponse getCommentById(Long memberId, Long boardId, Long commentId);

  Comment getComment(Long id);

  Comment deleteComment(Long id);

}
