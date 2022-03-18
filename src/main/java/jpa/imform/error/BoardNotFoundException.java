package jpa.imform.error;

public class BoardNotFoundException extends RuntimeException {

  public BoardNotFoundException(Long id) {
    super("Board Not found" + id);
  }
}
