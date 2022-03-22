package jpa.imform.error;

public class MemberNotFoundException extends RuntimeException{
  public MemberNotFoundException(Long id) {
    super("Not Found member "+ id);
  }
}
