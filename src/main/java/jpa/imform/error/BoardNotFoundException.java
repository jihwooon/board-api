package jpa.imform.error;

public class BoardNotFoundException extends RuntimeException {

  public BoardNotFoundException(Long id) {
    super("Board Not found" + id);
  }

  public BoardNotFoundException(String message) {
    super(message);
  }

  public BoardNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public BoardNotFoundException(Throwable cause) {
    super(cause);
  }

  public BoardNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
