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
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentApiController {

  private final CommentRepository commentRepository;
//
//  @GetMapping("/api/v1")
//  public List<CommentDto.CommentResponse> listV1() {
//    List<Comment> comments = commentRepository.findAll();
//    List<CommentDto.CommentResponse> result = comments.stream()
//        .map(o -> new CommentDto.CommentResponse(o))
//        .collect(Collectors.toList());
//
//    return result;
//  }
//
//  @GetMapping("/api/v1/{id}")
//  public CommentDto.CommentResponse detail(@PathVariable("id") Long id) {
//    Comment commend = commentRepository.findById(id)
//        .orElseThrow(() -> new CommentNotFoundException(id));
//
//    return CommentDto.CommentResponse.of(commend);
//
//  }
//
//  @GetMapping("/api/v2")
//  public List<CommentDto.CommentResponse> listV2() {
//    List<Comment> comments = commentRepository.findAllWithCommentThem();
//
//    List<CommentDto.CommentResponse> result = comments.stream()
//        .map(o -> new CommentDto.CommentResponse(o))
//        .collect(Collectors.toList());
//
//    return result;
//  }

}
