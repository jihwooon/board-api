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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping("member/{memberId}/board/{boardId}/comment")
  public List<CommentDto.CommentResponse> list(@PathVariable Long memberId, @PathVariable Long boardId) {
    return commentService.getComments(memberId, boardId);
  }

  @PostMapping("member/{memberId}/board/{boardId}/comment")
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDto.CommentResponse create(@PathVariable Long memberId, @PathVariable Long boardId, @RequestBody @Valid CommentDto.CommentRequest request) {
    return commentService.createComment(memberId, boardId, request);
  }

  @GetMapping("/comment/{id}")
  public Comment detail(@PathVariable Long id) {
    return commentService.getComment(id);
  }

  @PatchMapping("/comment/{id}")
  public Comment update(@PathVariable Long id, @RequestBody @Valid Comment comment) {
    return commentService.updateComment(id, comment);
  }

  @DeleteMapping("/comment/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long id) {
    commentService.deleteComment(id);
  }
}
