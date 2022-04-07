package jpa.imform.controller;

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

  //CommentRepository Basic
  @GetMapping("member/{memberId}/board/{boardId}/comment")
  public List<CommentDto.ListCommentResponse> list(@PathVariable final Long memberId,
                                                   @PathVariable final Long boardId) {
    return commentService.getComments(memberId, boardId);
  }

  //CommentRepository method name query
  @GetMapping("memberV1/{memberId}/boardV1/{boardId}/commentV1")
  public List<CommentDto.ListCommentResponse> listV1(@PathVariable final Long memberId,
                                                     @PathVariable final Long boardId) {
    return commentService.getCommentsV1(memberId, boardId);
  }

  //CommentJpaRepository
  @GetMapping("memberV2/{memberId}/boardV2/{boardId}/commentV2")
  public List<CommentDto.ListCommentResponse> listV2(@PathVariable final Long memberId,
                                                     @PathVariable final Long boardId) {
    return commentService.getCommentsV2(memberId, boardId);
  }

  //CommentRepository detail -> Basic
  @GetMapping("member/{memberId}/board/{boardId}/comment/{commentId}")
  public CommentDto.getCommentResponse detail(@PathVariable final Long memberId,
                                              @PathVariable final Long boardId,
                                              @PathVariable final Long commentId) {
    return commentService.getCommentById(memberId, boardId, commentId);
  }

  //CommentRepository detail -> method name query
  @GetMapping("memberV1/{memberId}/boardV1/{boardId}/commentV1/{commentId}")
  public CommentDto.getCommentResponse detailV1(@PathVariable final Long memberId,
                                                @PathVariable final Long boardId,
                                                @PathVariable final Long commentId) {
    return commentService.getCommentByIdV1(memberId, boardId, commentId);
  }

  @PostMapping("member/{memberId}/board/{boardId}/comment")
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDto.CreateCommentResponse create(@PathVariable final Long memberId,
                                                 @PathVariable final Long boardId,
                                                 @RequestBody @Valid final CommentDto.CreateCommentRequest create) {
    return commentService.createComment(memberId, boardId, create);
  }

  @PatchMapping("member/{memberId}/board/{boardId}/comment/{commentId}")
  public CommentDto.UpdateCommentResponse update(@PathVariable final Long memberId,
                                                 @PathVariable final Long boardId,
                                                 @PathVariable final Long commentId,
                                                 @RequestBody @Valid final CommentDto.UpdateCommentRequest update) {
    return commentService.updateComment(memberId, boardId, commentId, update);
  }

  @DeleteMapping("/comment/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable final Long commentId) {
    commentService.deleteComment(commentId);
  }

  @GetMapping("/comment-count")
  public long count() {
    return commentService.getCount();
}

//  @GetMapping("/comment-dto")
//  public List<CommentDto.ListCommentResponse> listDto() {
//    return commentService.getListCommentDto();
//  }
}
