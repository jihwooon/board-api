package jpa.imform.error;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super("잘못된 경로 입니다. "+ message);
  }
}
