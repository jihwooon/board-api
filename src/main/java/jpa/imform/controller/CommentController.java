//TODO :
//1 Comment 단건조회 -> 구현하기
//2. CommentDto 구현하기
package jpa.imform.controller;

import jpa.imform.domain.Comment;
import jpa.imform.dto.CommentDto;
import jpa.imform.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping("member/{memberId}/board/{boardId}/comment")
  public List<CommentDto.ListCommentResponse> list(@PathVariable final Long memberId,
                                                   @PathVariable final Long boardId) {
    return commentService.getComments(memberId, boardId);
  }

  @GetMapping("member/{memberId}/board/{boardId}/comment/{commentId}")
  public CommentDto.getCommentResponse detail(@PathVariable final Long memberId,
                                              @PathVariable final Long boardId,
                                              @PathVariable final Long commentId) {
    return commentService.getCommentById(memberId, boardId, commentId);

  }

  @PostMapping("member/{memberId}/board/{boardId}/comment")
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDto.CreateCommentResponse create(@PathVariable final Long memberId,
                                                 @PathVariable final Long boardId,
                                                 @RequestBody @Valid final CommentDto.CreateCommentRequest request) {
    return commentService.createComment(memberId, boardId, request);
  }

  @PatchMapping("member/{memberId}/board/{boardId}/comment/{commentId}")
  public CommentDto.UpdateCommentResponse update(@PathVariable final Long memberId,
                                                @PathVariable final Long boardId,
                                                @PathVariable final Long commentId,
                                                @RequestBody @Valid final CommentDto.UpdateCommentRequest request) {
    return commentService.updateComment(memberId, boardId, commentId, request);
  }

  @DeleteMapping("/comment/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long commentId) {
    commentService.deleteComment(commentId);
  }
}
