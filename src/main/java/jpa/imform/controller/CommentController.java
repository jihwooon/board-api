package jpa.imform.controller;

import jpa.imform.domain.Comment;
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

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping
  public List<Comment> list() {
    return commentService.getComments();
  }

  @GetMapping("{id}")
  public Comment detail(@PathVariable Long id) {
    return commentService.getCommet(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Comment create(@RequestBody Comment comment) {
    return commentService.createComment(comment);
  }

  @PatchMapping("{id}")
  public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
    return commentService.updateComment(id, comment);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long id) {
    commentService.deleteComment(id);
  }

}
