package jpa.imform.service;

import jpa.imform.domain.Comment;

import java.util.List;

public interface CommentService {

  List<Comment> getComments();

  Comment getCommet(Long id);

  Comment createComment(Comment comment);

  Comment updateComment(Long id, Comment source);

  Comment deleteComment(Long id);
}
