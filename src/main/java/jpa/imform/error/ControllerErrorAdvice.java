package jpa.imform.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerErrorAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(MemberNotFoundException.class)
  public void handleMemberNotFoundException() {

  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(InvalidTokenException.class)
  public void handleInvalidAccessTokenException() {
  }
}
