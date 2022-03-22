package jpa.imform.error;

public class CommentNotFoundException extends RuntimeException{
  public CommentNotFoundException(Long id) {
    super("Comment Not found" + id);
  }
}
