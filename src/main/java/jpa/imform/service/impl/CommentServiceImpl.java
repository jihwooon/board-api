package jpa.imform.service.impl;

import jpa.imform.domain.Comment;
import jpa.imform.error.CommentNotFoundException;
import jpa.imform.repository.CommentRepository;
import jpa.imform.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  public List<Comment> getComments() {
    return commentRepository.findAll();
  }

  @Override
  public Comment getComment(Long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(id));
  }

  @Override
  public Comment createComment(Comment comment) {
    return commentRepository.save(comment);
  }

  @Override
  public Comment updateComment(Long id, Comment source) {
    Comment comment = getComment(id);
    comment.change(source);
    return comment;
  }

  @Override
  public Comment deleteComment(Long id) {
    Comment commet = getComment(id);
    commentRepository.delete(commet);

    return commet;
  }
}
