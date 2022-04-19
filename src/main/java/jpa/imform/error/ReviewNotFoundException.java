package jpa.imform.error;

public class ReviewNotFoundException extends RuntimeException {
  public ReviewNotFoundException(String message) {
    super("Not Found Exception" + message);
  }
}
