package jpa.imform.error;

public class InvalidTokenException extends RuntimeException {
  public InvalidTokenException(String token) {
    super("Invalid Token : " + token);
  }
}
