package jpa.imform.service;

import jpa.imform.domain.Comment;
import jpa.imform.error.CommentNotFoundException;
import jpa.imform.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  public List<Comment> getComments() {
    return commentRepository.findAll();
  }

  public Comment getCommet(Long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(id));
  }

  public Comment createComment(Comment comment) {
    return commentRepository.save(comment);
  }

  public Comment updateComment(Long id, Comment source) {
    Comment commet = getCommet(id);
    commet.change(source);
    return commet;
  }

  public Comment deleteComment(Long id) {
    Comment commet = getCommet(id);
    commentRepository.delete(commet);

    return commet;
  }
}
