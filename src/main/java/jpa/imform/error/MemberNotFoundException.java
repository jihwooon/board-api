package jpa.imform.error;

public class MemberNotFoundException extends RuntimeException {
  public MemberNotFoundException(String message) {
    super("Not Found member " + message);
  }
}
