package jpa.imform.api;

import jpa.imform.domain.Comment;
import jpa.imform.dto.CommentDto;
import jpa.imform.error.CommentNotFoundException;
import jpa.imform.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

  private final CommentRepository commentRepository;

  @GetMapping("/comment/api/v1")
  public List<CommentDto.ListCommentResponse> listV1() {
    List<Comment> comments = commentRepository.findAll();
    List<CommentDto.ListCommentResponse> result = comments.stream()
        .map(o -> new CommentDto.ListCommentResponse(o))
        .collect(Collectors.toList());

    return result;
  }

  @GetMapping("/comment/api/v1/{commentId}")
  public CommentDto.getCommentResponse detail(@PathVariable Long commentId) {
    Comment commend = commentRepository.findById(commentId)
        .orElseThrow(() -> new CommentNotFoundException(commentId));

    return CommentDto.getCommentResponse.of(commend);

  }

  @GetMapping("/comment/api/v2")
  public List<CommentDto.ListCommentResponse> listV2() {
    List<Comment> comments = commentRepository.findAllWithCommentThem();

    List<CommentDto.ListCommentResponse> result = comments.stream()
        .map(o -> new CommentDto.ListCommentResponse(o))
        .collect(Collectors.toList());

    return result;
  }

}
